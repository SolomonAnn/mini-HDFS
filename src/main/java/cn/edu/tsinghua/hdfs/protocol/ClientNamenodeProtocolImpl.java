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

    // active datanodes
    private List<String> datanodes = new LinkedList<String>();

    // memories used by each datanode
    private Map<String, Long> memories = new HashMap<String, Long>();

    @Override
    public void init(ClientNamenodeProtocolProtos.InitRequestProto request, StreamObserver<ClientNamenodeProtocolProtos.InitResponseProto> responseObserver) {
        ClientNamenodeProtocolProtos.InitResponseProto response = ClientNamenodeProtocolProtos.InitResponseProto
                .newBuilder()
                .setPath(HdfsProtocolProtos.PathProto
                        .newBuilder()
                        .setSrc(System.getProperty("user.dir"))
                        .setHasPermission(true)
                        .build())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void cd(ClientNamenodeProtocolProtos.CdRequestProto request, StreamObserver<ClientNamenodeProtocolProtos.CdResponseProto> responseObserver) {
        String basePath = System.getProperty("user.dir");
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

        FileReader fileReader;
        BufferedReader bufferedReader;
        String value;
        Map<String, String> infos = new HashMap<String, String>();
        Iterator<Map.Entry<String, String>> entries;
        Map.Entry<String, String> entry;
        boolean isLast = false;

        try {
            File fsImage = new File(Constant.FSIMAGE);
            if (!fsImage.exists()) {
                fsImage.createNewFile();
            }

            fileReader = new FileReader(fsImage);
            bufferedReader = new BufferedReader(fileReader);

            while ((value = bufferedReader.readLine()) != null) {
                infos.put(value.split(Constant.BLOCK_SPACE)[1], value.split(Constant.BLOCK_SPACE)[0].split(" ")[0] + " " + value.split(Constant.BLOCK_SPACE)[0].split(" ")[2]);
            }

            entries = infos.entrySet().iterator();

            while (entries.hasNext()) {
                entry = entries.next();
                if (StringUtils.substringAfter(entry.getKey(),"_").startsWith("0_")) {
                    if (!isLast) {
                        isLast = true;
                    } else {
                        break;
                    }
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
            }

            bufferedReader.close();
            fileReader.close();
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

        FileReader fileReader;
        BufferedReader bufferedReader;
        FileWriter fileWriter;
        BufferedWriter bufferedWriter;

        String value;
        String[] info;
        String path;

        String tmpDatanode;
        long tmpMemory;
        long baseMemory;

        Map<String, Long> pickedSlaves;
        String currDatanode;

        Iterator<Map.Entry<String, Long>> entries;
        Map.Entry<String, Long> entry;

        // get datanodes
        try {
            File slaveList = new File(Constant.SLAVESLIST);
            if (!slaveList.exists()) {
                slaveList.createNewFile();
            }

            fileReader = new FileReader(slaveList);
            bufferedReader = new BufferedReader(fileReader);

            while ((value = bufferedReader.readLine()) != null) {
                datanodes.add(value);
            }

            // initial memories
            for (String datanode : datanodes) {
                memories.put(datanode, 0L);
            }

            bufferedReader.close();
            fileReader.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        // the number of datanodes
        int slavesCount = datanodes.size();
        // the number of blocks
        int blockCount = (int)(Math.ceil((float)request.getLength() / (float)Constant.BLOCK_SIZE));
        // times of replication
        int replicationCount = Math.min(Constant.REPLICATION_FACTOR, slavesCount);

        try {
            File fsImage = new File(Constant.FSIMAGE);
            if (!fsImage.exists()) {
                fsImage.createNewFile();
            }

            fileReader = new FileReader(fsImage);
            bufferedReader = new BufferedReader(fileReader);

            while ((value = bufferedReader.readLine()) != null) {
                // calculate memories used by each datanode
                info = value.split(Constant.BLOCK_SPACE);
                tmpMemory = memories.get(info[0]) + Long.valueOf(info[3]);
                memories.put(info[0], tmpMemory);
            }

            bufferedReader.close();
            fileReader.close();

            fileWriter = new FileWriter(fsImage, true);

            for (int i = 0; i < blockCount; i++) {
                baseMemory = Long.MAX_VALUE;
                pickedSlaves = new HashMap<String, Long>();
                currDatanode = null;
                entries = memories.entrySet().iterator();

                // pick top replicationCount datanodes
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

                    path = StringUtils.substringBeforeLast(request.getPath().getSrc(), ".")
                            + "_" + i + "_" + info[2] + "."
                            + StringUtils.substringAfterLast(request.getPath().getSrc(), ".");

                    fileWriter.write(tmpDatanode + Constant.BLOCK_SPACE
                            + path + Constant.BLOCK_SPACE
                            + i * Constant.BLOCK_SIZE + Constant.BLOCK_SPACE
                            + (tmpMemory - entry.getValue())
                            + "\r\n");

                    response.addBlocks(HdfsProtocolProtos.BlockProto
                            .newBuilder()
                            .setId(HdfsProtocolProtos.IdProto
                                    .newBuilder()
                                    .setIp(info[0])
                                    .setRpcPort(Integer.valueOf(info[1]))
                                    .setSocketPort(Integer.valueOf(info[2]))
                                    .build())
                            .setSrc(path)
                            .setOffset(i * Constant.BLOCK_SIZE)
                            .setLength(tmpMemory - entry.getValue())
                            .build());
                }
            }

            fileWriter.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
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
