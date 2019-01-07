package cn.edu.tsinghua.hdfs.protocol;

import cn.edu.tsinghua.hdfs.constant.Constant;
import io.grpc.stub.StreamObserver;
import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.util.*;

/**
 * @author: An
 * @description: implement services defined in ClientNamenodeProtocol.proto
 */

public class ClientNamenodeProtocolImpl extends ClientNamenodeProtocolGrpc.ClientNamenodeProtocolImplBase {

    @Override
    public void cd(ClientNamenodeProtocolProtos.CdRequestProto request, StreamObserver<ClientNamenodeProtocolProtos.CdResponseProto> responseObserver) {
        String basePath = Constant.NAMENODE_PATH;
        String path = request.getPath().getSrc();
        File file = new File(path);
        Boolean isSuccessful = true;
        if (!file.exists()) {
            path = StringUtils.substringBeforeLast(path, Constant.DIRECTORY_PREFIX);
            isSuccessful = false;
        }
        if (path.compareTo(basePath) < 0) {
            path = basePath;
            isSuccessful = false;
        }

        ClientNamenodeProtocolProtos.CdResponseProto response = ClientNamenodeProtocolProtos.CdResponseProto
                .newBuilder()
                .setPath(HdfsProtocolProtos.PathProto
                        .newBuilder()
                        .setSrc(path)
                        .setHasPermission(true)
                        .build())
                .setIsSuccessful(isSuccessful)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void ls(ClientNamenodeProtocolProtos.LsRequestProto request, StreamObserver<ClientNamenodeProtocolProtos.LsResponseProto> responseObserver) {
        String path = request.getPath().getSrc();
        File dirs = new File(path);

        ClientNamenodeProtocolProtos.LsResponseProto.Builder response = ClientNamenodeProtocolProtos.LsResponseProto
                .newBuilder();

        for (String dir : dirs.list()) {
            response.addPath(HdfsProtocolProtos.PathProto
                    .newBuilder()
                    .setSrc(dir)
                    .setHasPermission(true)
                    .build());
        }

        try {
            File filesList = new File(Constant.DATANODE_PATH + Constant.DIRECTORY_PREFIX + Constant.FILESLIST);
            if (!filesList.exists()) {
                filesList.createNewFile();
            }
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filesList));
            String filename;

            while ((filename = bufferedReader.readLine()) != null) {
                response.addPath(HdfsProtocolProtos.PathProto
                        .newBuilder()
                        .setSrc(filename)
                        .setHasPermission(true)
                        .build());
            }

            bufferedReader.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

    @Override
    public void mkdir(ClientNamenodeProtocolProtos.MkdirRequestProto request, StreamObserver<ClientNamenodeProtocolProtos.MkdirResponseProto> responseObserver) {
        ClientNamenodeProtocolProtos.MkdirResponseProto response = ClientNamenodeProtocolProtos.MkdirResponseProto
                .newBuilder()
                .build();

        int count = request.getPathCount();
        File file;
        String path;
        for (int i = 0; i < count; i++) {
            path = request.getPath(i).getSrc();
            file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
        }

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void get(ClientNamenodeProtocolProtos.GetRequestProto request, StreamObserver<ClientNamenodeProtocolProtos.GetResponseProto> responseObserver) {
        ClientNamenodeProtocolProtos.GetResponseProto.Builder response = ClientNamenodeProtocolProtos.GetResponseProto
                .newBuilder();

        BufferedReader bufferedReader;

        String strLine;

        String filename = StringUtils.substringBefore(request.getSrc(), ".");

        // (filename, ip + socketPort)
        Map<String, String> infos = new LinkedHashMap<String, String>();
        Iterator<Map.Entry<String, String>> entries;
        Map.Entry<String, String> entry;
        List<String> pickedBlocks = new ArrayList<String>();

        try {
            File fsImage = new File(Constant.DATANODE_PATH + Constant.DIRECTORY_PREFIX + Constant.FSIMAGE);
            if (!fsImage.exists()) {
                fsImage.createNewFile();
            }

            bufferedReader = new BufferedReader(new FileReader(fsImage));

            while ((strLine = bufferedReader.readLine()) != null) {
                infos.put(strLine.split(Constant.BLOCK_SPACE)[1], strLine.split(Constant.BLOCK_SPACE)[0].split(" ")[0] + " " + strLine.split(Constant.BLOCK_SPACE)[0].split(" ")[2]);
            }

            entries = infos.entrySet().iterator();

            // pick blocks
            while (entries.hasNext()) {
                entry = entries.next();
                if (!StringUtils.substringBefore(entry.getKey(), "_").equals(filename)) {
                    continue;
                }
                if (pickedBlocks.contains(entry.getKey())) {
                    continue;
                }

                response.addBlocks(HdfsProtocolProtos.BlockProto
                        .newBuilder()
                        .setId(HdfsProtocolProtos.IdProto
                                .newBuilder()
                                .setIp(entry.getValue().split(" ")[0])
                                .setSocketPort(Integer.valueOf(entry.getValue().split(" ")[1]))
                                .build())
                        .setSrc(entry.getKey())
                        .build());

                pickedBlocks.add(entry.getKey());
            }

            bufferedReader.close();
        } catch (Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

    @Override
    public void put(ClientNamenodeProtocolProtos.PutRequestProto request, StreamObserver<ClientNamenodeProtocolProtos.PutResponseProto> responseObserver) {
        ClientNamenodeProtocolProtos.PutResponseProto.Builder response = ClientNamenodeProtocolProtos.PutResponseProto
                .newBuilder();

        // active datanodes (ip + rpcPort + socketPort)
        List<String> datanodes = new LinkedList<String>();
        // memories used by each datanode (datanode, memory)
        Map<String, Long> memories = new HashMap<String, Long>();

        BufferedReader bufferedReader;
        BufferedWriter bufferedWriter;

        String strLine;

        String[] info;
        String filename;

        String tmpDatanode;
        long tmpMemory;
        long baseMemory;

        Map<String, Long> pickedSlaves;
        String currDatanode;

        Iterator<Map.Entry<String, Long>> entries;
        Map.Entry<String, Long> entry;

        // get datanodes
        try {
            File slavesList = new File(Constant.NAMENODE_PATH + Constant.DIRECTORY_PREFIX + Constant.SLAVESLIST);
            if (!slavesList.exists()) {
                slavesList.createNewFile();
            }
            bufferedReader = new BufferedReader(new FileReader(slavesList));

            // read from SlavesList
            while ((strLine = bufferedReader.readLine()) != null) {
                datanodes.add(strLine);
            }

            // initial memories
            for (String datanode : datanodes) {
                memories.put(datanode, 0L);
            }

            bufferedReader.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        // the number of slaves
        int slavesCount = datanodes.size();
        // the number of blocks
        int blockCount = (int)(Math.ceil((float)request.getLength() / (float)Constant.BLOCK_SIZE));
        // times of replication
        int replicationCount = Math.min(Constant.REPLICATION_FACTOR, slavesCount);

        try {
            File fsImage = new File(Constant.NAMENODE_PATH + Constant.DIRECTORY_PREFIX + Constant.FSIMAGE);
            if (!fsImage.exists()) {
                fsImage.createNewFile();
            }

            bufferedReader = new BufferedReader(new FileReader(fsImage));

            while ((strLine = bufferedReader.readLine()) != null) {
                // calculate memories used by each datanode
                info = strLine.split(Constant.BLOCK_SPACE);
                tmpMemory = memories.get(info[0]) + Long.valueOf(info[3]);
                memories.put(info[0], tmpMemory);
            }

            bufferedReader.close();

            bufferedWriter = new BufferedWriter(new FileWriter(fsImage, true));

            for (int i = 0; i < blockCount; i++) {
                baseMemory = Long.MAX_VALUE;
                pickedSlaves = new HashMap<String, Long>();
                currDatanode = null;
                entries = memories.entrySet().iterator();

                // pick 'replicationCount' datanodes
                while (entries.hasNext()) {
                    entry = entries.next();
                    tmpDatanode = entry.getKey();
                    tmpMemory = entry.getValue();
                    if (pickedSlaves.size() < replicationCount) {
                        pickedSlaves.put(tmpDatanode, tmpMemory);
                        if ((tmpMemory < baseMemory && baseMemory == Long.MAX_VALUE) || tmpMemory >= baseMemory) {
                            baseMemory = tmpMemory;
                            currDatanode = tmpDatanode;
                        }
                        continue;
                    }
                    if (tmpMemory < baseMemory) {
                        pickedSlaves.remove(currDatanode);
                        pickedSlaves.put(tmpDatanode, tmpMemory);
                        baseMemory = tmpMemory;
                        currDatanode = tmpDatanode;
                    }
                }

                entries = pickedSlaves.entrySet().iterator();

                // write info and add response
                while (entries.hasNext()) {
                    entry = entries.next();
                    tmpDatanode = entry.getKey();
                    info = tmpDatanode.split(" ");

                    if (i == blockCount - 1) {
                        tmpMemory = entry.getValue() + request.getLength() - (blockCount - 1) * Constant.BLOCK_SIZE;
                    } else {
                        tmpMemory = entry.getValue() + Constant.BLOCK_SIZE;
                    }
                    memories.put(tmpDatanode, tmpMemory);

                    // prev filename + block index + socket port + suffix
                    filename = StringUtils.substringBeforeLast(request.getSrc(), ".")
                            + "_" + i + "_" + info[2] + "."
                            + StringUtils.substringAfterLast(request.getSrc(), ".");

                    bufferedWriter.write(tmpDatanode + Constant.BLOCK_SPACE
                            + filename + Constant.BLOCK_SPACE
                            + i * Constant.BLOCK_SIZE + Constant.BLOCK_SPACE
                            + (tmpMemory - entry.getValue())
                            + "\r\n");

                    response.addBlocks(HdfsProtocolProtos.BlockProto
                            .newBuilder()
                            .setId(HdfsProtocolProtos.IdProto
                                    .newBuilder()
                                    .setIp(info[0])
                                    .setSocketPort(Integer.valueOf(info[2]))
                                    .build())
                            .setSrc(filename)
                            .setOffset(i * Constant.BLOCK_SIZE)
                            .setLength(tmpMemory - entry.getValue())
                            .build());
                }
            }

            bufferedWriter.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            try {
                File filesList = new File(Constant.NAMENODE_PATH + Constant.DIRECTORY_PREFIX + Constant.FILESLIST);
                if (!filesList.exists()) {
                    filesList.createNewFile();
                }

                bufferedWriter = new BufferedWriter(new FileWriter(filesList, true));
                bufferedWriter.write(request.getSrc());
                bufferedWriter.close();
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }
        }

        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

    @Override
    public void rm(ClientNamenodeProtocolProtos.RmRequestProto request, StreamObserver<ClientNamenodeProtocolProtos.RmResponseProto> responseObserver) {
        ClientNamenodeProtocolProtos.RmResponseProto.Builder response = ClientNamenodeProtocolProtos.RmResponseProto
                .newBuilder();
        int count = request.getPathCount();
        File file;
        String path;
        boolean isSuccessful = true;
        for (int i = 0; i < count; i++) {
            path = request.getPath(i).getSrc();
            file = new File(path);
            if (!file.exists() || !file.isFile()) {
                isSuccessful = false;
            } else {
                file.delete();
            }
        }
        response.setIsSuccessful(isSuccessful);
        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }
}
