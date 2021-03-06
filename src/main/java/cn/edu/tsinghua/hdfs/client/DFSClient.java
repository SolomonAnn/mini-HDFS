package cn.edu.tsinghua.hdfs.client;

import cn.edu.tsinghua.hdfs.constant.Constant;
import cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolGrpc;
import cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos;
import cn.edu.tsinghua.hdfs.protocol.HdfsProtocolProtos;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.TimeUnit;


/**
 * @author: An
 * @description: Client
 */

public class DFSClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(DFSClient.class);

    private static final Scanner SCANNER = new Scanner(System.in);

    private final ManagedChannel managedChannel;
    private final ClientNamenodeProtocolGrpc.ClientNamenodeProtocolBlockingStub
        clientNamenodeProtocolBlockingStub;

    public DFSClient(String ip, int rpcPort) {
        this.managedChannel = ManagedChannelBuilder
                .forAddress(ip, rpcPort)
                .usePlaintext()
                .build();
        this.clientNamenodeProtocolBlockingStub =
            ClientNamenodeProtocolGrpc.newBlockingStub(managedChannel);
    }

    public void shutdown() throws InterruptedException {
        managedChannel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public String cd(String currentPath, String path) {
        if (path.contains(" ")) {
            System.out.println(Constant.INVALID_FILENAME_OR_DIRECTORY);
            return currentPath;
        }
        if (path.equals("..")) {
            path = StringUtils.substringBeforeLast(currentPath, Constant.DIRECTORY_PREFIX);
        } else {
            path = path.replaceAll(Constant.ILLEGAL_DIRECTORY, "_");
            path = currentPath + Constant.DIRECTORY_PREFIX + path;
        }
        ClientNamenodeProtocolProtos.CdRequestProto request =
            ClientNamenodeProtocolProtos.CdRequestProto
                .newBuilder()
                .setPath(HdfsProtocolProtos.PathProto
                        .newBuilder()
                        .setSrc(path)
                        .build())
                .build();
        ClientNamenodeProtocolProtos.CdResponseProto response;
        try {
            response = clientNamenodeProtocolBlockingStub.cd(request);
            if (response.getIsSuccessful() == false) {
                System.out.println(Constant.INVALID_FILENAME_OR_DIRECTORY);
            }
        } catch (StatusRuntimeException e) {
            LOGGER.warn("RPC failed: {0}", e.getStatus());
            return null;
        }
        return response.getPath().getSrc();
    }

    public void ls(String currentPath) {
        ClientNamenodeProtocolProtos.LsRequestProto request =
            ClientNamenodeProtocolProtos.LsRequestProto
                .newBuilder()
                .setPath(HdfsProtocolProtos.PathProto
                        .newBuilder()
                        .setSrc(currentPath)
                        .setHasPermission(true)
                        .build())
                .build();
        ClientNamenodeProtocolProtos.LsResponseProto response;
        try {
            response = clientNamenodeProtocolBlockingStub.ls(request);
            int count = response.getPathCount();
            String path;
            for (int i = 0; i < count; i++) {
                path = response.getPath(i).getSrc();
                System.out.println(path);
            }
        } catch (StatusRuntimeException e) {
            LOGGER.warn("RPC failed: {0}", e.getStatus());
            return;
        }
        return;
    }

    public void mkdir(String currentPath, String[] paths) {
        ClientNamenodeProtocolProtos.MkdirRequestProto.Builder request =
            ClientNamenodeProtocolProtos.MkdirRequestProto
                .newBuilder();
        for (String path : paths) {
            if (path.contains(" ")) {
                System.out.println(Constant.INVALID_FILENAME_OR_DIRECTORY);
                return;
            }
            path = path.replaceAll(Constant.ILLEGAL_DIRECTORY, "_");
            request.addPath(HdfsProtocolProtos.PathProto
                    .newBuilder()
                    .setSrc(currentPath + Constant.DIRECTORY_PREFIX + path)
                    .setHasPermission(true)
                    .build());
        }
        try {
            clientNamenodeProtocolBlockingStub.mkdir(request.build());
        } catch (StatusRuntimeException e) {
            LOGGER.warn("RPC failed: {0}", e.getStatus());
            return;
        }
        return;
    }

    public Map<String, String> get(String filename) {
        ClientNamenodeProtocolProtos.GetRequestProto request =
            ClientNamenodeProtocolProtos.GetRequestProto
                .newBuilder()
                .setSrc(filename)
                .build();

        Map<String, String> datanodes = new LinkedHashMap<String, String>();

        ClientNamenodeProtocolProtos.GetResponseProto response;
        try {
            response = clientNamenodeProtocolBlockingStub.get(request);
            int count = response.getBlocksCount();
            for (int i = 0; i < count; i++) {
                datanodes.put(response.getBlocks(i).getSrc(),
                    response.getBlocks(i).getId().getIp() + " "
                        + response.getBlocks(i).getId().getSocketPort());
            }
        } catch (StatusRuntimeException e) {
            LOGGER.warn("RPC failed: {0}", e.getStatus());
            return null;
        }
        return datanodes;
    }

    public List<Map<String, String>> put(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println(Constant.INVALID_FILENAME_OR_DIRECTORY);
            return null;
        }
        ClientNamenodeProtocolProtos.PutRequestProto request =
            ClientNamenodeProtocolProtos.PutRequestProto
                .newBuilder()
                .setSrc(filename)
                .setLength(file.length())
                .build();

        ClientNamenodeProtocolProtos.PutResponseProto response;
        List<Map<String, String>> datanodes = new ArrayList<Map<String, String>>();
        Map<String, String> datanode;
        try {
            response = clientNamenodeProtocolBlockingStub.put(request);
            int count = response.getBlocksCount();
            for (int i = 0; i < count; i++) {
                datanode = new HashMap<String, String>();
                datanode.put("Ip", response.getBlocks(i).getId().getIp());
                datanode.put("SocketPort",
                    String.valueOf(response.getBlocks(i).getId().getSocketPort()));
                datanode.put("Filename", response.getBlocks(i).getSrc());
                datanode.put("Offset", String.valueOf(response.getBlocks(i).getOffset()));
                datanode.put("Length", String.valueOf(response.getBlocks(i).getLength()));

                datanodes.add(datanode);
            }
        } catch (StatusRuntimeException e) {
            LOGGER.warn("RPC failed: {0}", e.getStatus());
            return null;
        }
        return datanodes;
    }

    public void rm(String currentPath, String[] paths) {
        ClientNamenodeProtocolProtos.RmRequestProto.Builder request =
            ClientNamenodeProtocolProtos.RmRequestProto
                .newBuilder();
        for (String path : paths) {
            if (path.contains(" ")) {
                System.out.println(Constant.INVALID_FILENAME_OR_DIRECTORY);
                return;
            }
            path = path.replaceAll(Constant.ILLEGAL_DIRECTORY, "_");
            request.addPath(HdfsProtocolProtos.PathProto
                    .newBuilder()
                    .setSrc(currentPath + Constant.DIRECTORY_PREFIX + path)
                    .setHasPermission(true)
                    .build());
        }
        try {
            clientNamenodeProtocolBlockingStub.rm(request.build());
        } catch (StatusRuntimeException e) {
            LOGGER.warn("RPC failed: {0}", e.getStatus());
            return;
        }
        return;
    }

    public static void main(String[] args) throws Exception {
        Constant.NAMENODE_IP = args[0];
        Constant.NAMENODE_PATH = args[1];
        Constant.DATANODE_IP = args[2];
        Constant.DATANODE_PATH = args[3];
        Constant.RPC_PORT = Integer.valueOf(args[4]);
        Constant.SOCKET_PORT = Integer.valueOf(args[5]);

        DFSClient client = new DFSClient(Constant.NAMENODE_IP, Constant.RPC_PORT);

        String command;
        String[] pieces;
        String currentPath;
        List<Map<String, String>> datanodes;
        Map<String, String> slaves;

        currentPath = Constant.NAMENODE_PATH;
        System.out.println(currentPath + Constant.CMD_SUFFIX);
        try {
            while(SCANNER.hasNextLine()) {
                command = SCANNER.nextLine();
                if (command.startsWith(Constant.CMD_IDENTIFIER)) {
                    command = command.substring(Constant.CMD_IDENTIFIER.length() + 1);
                    pieces = command.split(" ");
                    if (pieces[0].equals(Constant.CD)) {
                        command = command.substring(Constant.CD.length() + 1);
                        currentPath = client.cd(currentPath, command);
                    } else if (command.equals(Constant.LS)) {
                        client.ls(currentPath);
                    } else if (pieces[0].equals(Constant.MKDIR)) {
                        command = command.substring(Constant.MKDIR.length() + 1);
                        client.mkdir(currentPath, command.split(" "));
                    } else if (pieces[0].equals(Constant.GET)) {
                        command = command.substring(Constant.GET.length() + 1);
                        slaves = client.get(command);

                        DownloadClient downloadClient = new DownloadClient();
                        downloadClient.setVariables(slaves, command);
                        Thread threadDownloadClient = new Thread(downloadClient);
                        threadDownloadClient.start();

                    } else if (pieces[0].equals(Constant.PUT)) {
                        command = command.substring(Constant.PUT.length() + 1);
                        datanodes = client.put(command);

                        UploadClient uploadClient = new UploadClient();
                        uploadClient.setVariables(datanodes, command);
                        Thread threadUploadClient = new Thread(uploadClient);
                        threadUploadClient.start();

                    } else if (pieces[0].equals(Constant.RM)) {
                        command = command.substring(Constant.RM.length() + 1);
                        client.rm(currentPath, command.split(" "));
                    }
                    System.out.println(currentPath + Constant.CMD_SUFFIX);
                } else {
                    System.out.println(Constant.INVALID_COMMAND);
                    System.out.println(currentPath + Constant.CMD_SUFFIX);
                }
            }
        } finally {
            client.shutdown();
        }
    }
}

class DownloadClient implements Runnable {

    private Map<String, String> slaves;

    private String filename;

    public void setVariables (Map<String, String> slaves, String filename) {
        this.slaves = slaves;
        this.filename = filename;
    }

    public void run() {
        try {
            Iterator<Map.Entry<String, String>> entries;
            Map.Entry<String, String> entry;

            entries = slaves.entrySet().iterator();

            while (entries.hasNext()) {
                entry = entries.next();

                String src = entry.getKey();
                String ip = entry.getValue().split(" ")[0];
                int socketPort = Integer.valueOf(entry.getValue().split(" ")[1]);

                File file = new File(filename);
                if (!file.exists()) {
                    file.createNewFile();
                }

                Socket socket = new Socket(ip, socketPort);

                DataOutputStream dataOutputStream = new DataOutputStream(
                    new BufferedOutputStream(socket.getOutputStream()));
                DataInputStream dataInputStream = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));
                DataOutputStream fileDataOutputStream = new DataOutputStream(
                    new BufferedOutputStream(new FileOutputStream(file, true)));

                // send 'get'
                dataOutputStream.writeUTF(Constant.GET);
                dataOutputStream.flush();

                // send filename
                dataOutputStream.writeUTF(src);
                dataOutputStream.flush();

                // receive file content
                byte[] bytes = new byte[Constant.BYTES_SIZE];
                int length;

                while ((length = dataInputStream.read(bytes)) != -1) {
                    fileDataOutputStream.write(bytes, 0, length);
                    fileDataOutputStream.flush();
                }

                dataOutputStream.close();
                fileDataOutputStream.close();
                dataInputStream.close();
                socket.close();
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}

class UploadClient implements Runnable {

    private List<Map<String, String>> datanodes;

    private String filename;

    public void setVariables (List<Map<String, String>> datanodes, String filename) {
        this.datanodes = datanodes;
        this.filename = filename;
    }

    public void run() {
        try {
            for (Map<String, String> datanode : datanodes) {
                Socket socket = new Socket(datanode.get("Ip"), Integer.valueOf(datanode.get("SocketPort")));

                File file = new File(filename);
                DataOutputStream dataOutputStream = new DataOutputStream(
                    new BufferedOutputStream(socket.getOutputStream()));
                DataInputStream fileDataInputStream = new DataInputStream(
                    new BufferedInputStream(new FileInputStream(file)));

                // send 'put'
                dataOutputStream.writeUTF(Constant.PUT);
                dataOutputStream.flush();

                // send filename
                String src = datanode.get("Filename");
                dataOutputStream.writeUTF(src);
                dataOutputStream.flush();

                // send file content
                long offset = Long.valueOf(datanode.get("Offset"));
                long length = Long.valueOf(datanode.get("Length"));
                long currOffset = 0L;
                int tmpLength;
                byte[] bytes = new byte[Constant.BYTES_SIZE];

                while ((tmpLength = fileDataInputStream.read(bytes)) != -1) {
                    if (currOffset + tmpLength < offset) {
                    } else if (currOffset < offset) {
                        dataOutputStream.write(bytes, (int)(offset - currOffset),
                            Math.min((int)(currOffset + tmpLength - offset), tmpLength));
                    } else if (currOffset + tmpLength < offset + length) {
                        dataOutputStream.write(bytes, 0, tmpLength);
                    } else if (currOffset < offset + length) {
                        dataOutputStream.write(bytes, 0,
                            Math.min((int)(offset + length - currOffset), tmpLength));
                    } else {
                        break;
                    }
                    currOffset += tmpLength;
                    dataOutputStream.flush();
                }

                dataOutputStream.close();
                fileDataInputStream.close();
                socket.close();
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}
