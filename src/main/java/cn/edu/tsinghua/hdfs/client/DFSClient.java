package cn.edu.tsinghua.hdfs.client;

import cn.edu.tsinghua.hdfs.constant.Constant;
import cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolGrpc;
import cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos;
import cn.edu.tsinghua.hdfs.protocol.HdfsProtocolProtos;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author: An
 * @description: Client
 */

public class DFSClient {
    private static final Logger logger = Logger.getLogger(Constant.CLIENT_GREETING);

    private static final Scanner scanner = new Scanner(System.in);

    private final ManagedChannel channel;
    private final ClientNamenodeProtocolGrpc.ClientNamenodeProtocolBlockingStub blockingStub;

    public DFSClient(String host, int port) {
        this(ManagedChannelBuilder
                .forAddress(host, port)
                .usePlaintext()
                .build());
    }

    DFSClient(ManagedChannel channel) {
        this.channel = channel;
        blockingStub = ClientNamenodeProtocolGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public String init() {
        ClientNamenodeProtocolProtos.InitRequestProto request = ClientNamenodeProtocolProtos.InitRequestProto
                .newBuilder()
                .build();
        ClientNamenodeProtocolProtos.InitResponseProto response;
        try {
            response = blockingStub.init(request);
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            return null;
        }
        return response.getPath().getSrc();
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
        ClientNamenodeProtocolProtos.CdRequestProto request = ClientNamenodeProtocolProtos.CdRequestProto
                .newBuilder()
                .setPath(HdfsProtocolProtos.PathProto
                        .newBuilder()
                        .setSrc(path)
                        .build())
                .build();
        ClientNamenodeProtocolProtos.CdResponseProto response;
        try {
            response = blockingStub.cd(request);
            if (response.getIsSuccessful() == false) {
                System.out.println(Constant.INVALID_FILENAME_OR_DIRECTORY);
            }
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            return null;
        }
        return response.getPath().getSrc();
    }

    public void ls(String currentPath) {
        ClientNamenodeProtocolProtos.LsRequestProto request = ClientNamenodeProtocolProtos.LsRequestProto
                .newBuilder()
                .setPath(HdfsProtocolProtos.PathProto
                        .newBuilder()
                        .setSrc(currentPath)
                        .setHasPermission(true)
                        .build())
                .build();
        ClientNamenodeProtocolProtos.LsResponseProto response;
        try {
            response = blockingStub.ls(request);
            int count = response.getPathCount();
            String path;
            for (int i = 0; i < count; i++) {
                path = response.getPath(i).getSrc();
                System.out.println(path);
            }
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            return;
        }
        return;
    }

    public void mkdir(String currentPath, String[] paths) {
        ClientNamenodeProtocolProtos.MkdirRequestProto.Builder request = ClientNamenodeProtocolProtos.MkdirRequestProto
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
            blockingStub.mkdir(request.build());
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            return;
        }
        return;
    }

    public Map<String, String> get(String currentPath, String path) {
        ClientNamenodeProtocolProtos.GetRequestProto request = ClientNamenodeProtocolProtos.GetRequestProto
                .newBuilder()
                .setPath(HdfsProtocolProtos.PathProto
                        .newBuilder()
                        .setSrc(currentPath + Constant.DIRECTORY_PREFIX + path)
                        .setHasPermission(true)
                        .build())
                .build();

        Map<String, String> datanodes = new HashMap<String, String> ();

        ClientNamenodeProtocolProtos.GetResponseProto response;
        try {
            response = blockingStub.get(request);
            int count = response.getBlocksCount();
            for (int i = 0; i < count; i++) {
                datanodes.put(response.getBlocks(i).getSrc(), response.getBlocks(i).getId().getIp() + " " + response.getBlocks(i).getId().getSocketPort());
            }
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            return null;
        }
        return datanodes;
    }

    public List<Map<String, String>> put(String currentPath, String path) {
        File file = new File(path);
        ClientNamenodeProtocolProtos.PutRequestProto request = ClientNamenodeProtocolProtos.PutRequestProto
                .newBuilder()
                .setPath(HdfsProtocolProtos.PathProto
                        .newBuilder()
                        .setSrc(currentPath + Constant.DIRECTORY_PREFIX + path)
                        .setHasPermission(true)
                        .build())
                .setLength(file.length())
                .build();

        ClientNamenodeProtocolProtos.PutResponseProto response;
        List<Map<String, String>> datanodes = new ArrayList<Map<String, String>>();
        Map<String, String> datanode;
        try {
            response = blockingStub.put(request);
            int count = response.getBlocksCount();
            for (int i = 0; i < count; i++) {
                datanode = new HashMap<String, String>();
                datanode.put("Ip", response.getBlocks(i).getId().getIp());
                datanode.put("SocketPort", String.valueOf(response.getBlocks(i).getId().getSocketPort()));
                datanode.put("Filename", response.getBlocks(i).getSrc());
                datanode.put("Offset", String.valueOf(response.getBlocks(i).getOffset()));
                datanode.put("Length", String.valueOf(response.getBlocks(i).getLength()));

                datanodes.add(datanode);
            }
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            return null;
        }
        return datanodes;
    }

    public void rm(String currentPath, String[] paths) {
        ClientNamenodeProtocolProtos.RmRequestProto.Builder request = ClientNamenodeProtocolProtos.RmRequestProto
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
            blockingStub.rm(request.build());
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            return;
        }
        return;
    }

    public static void main(String[] args) throws Exception {
        DFSClient client = new DFSClient(Constant.IP, Constant.PORT);

        String command;
        String[] pieces;
        String currentPath;
        List<Map<String, String>> datanodes;
        Map<String, String> slaves;

        currentPath = client.init();
        System.out.println(currentPath + Constant.CMD_SUFFIX);
        try {
            while(scanner.hasNextLine()) {
                command = scanner.nextLine();
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
                        slaves = client.get(currentPath, command);

                        DownloadClient downloadClient = new DownloadClient();
                        downloadClient.setVariables(slaves);
                        Thread threadDownloadClient = new Thread(downloadClient);
                        threadDownloadClient.start();

                    }
                    else if (pieces[0].equals(Constant.PUT)) {
                        command = command.substring(Constant.PUT.length() + 1);
                        datanodes = client.put(currentPath, command);

                        UploadClient uploadClient = new UploadClient();
                        uploadClient.setVariables(datanodes, currentPath, command);
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

class UploadClient implements Runnable {

    private List<Map<String, String>> datanodes;

    private String path;

    public void setVariables (List<Map<String, String>> datanodes, String currentPath, String path) {
        this.datanodes = datanodes;
        this.path = currentPath + Constant.DIRECTORY_PREFIX + path;
    }

    public void run() {
        try {
            for (Map<String, String> datanode : datanodes) {
                Socket socket = new Socket(datanode.get("Ip"), Integer.valueOf(datanode.get("SocketPort")));

                // send filename
                OutputStream outputStream = socket.getOutputStream();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                String filename = datanode.get("Filename");

                bufferedWriter.write(filename + "\n");
                bufferedWriter.flush();

//                bufferedWriter.close();
//                outputStreamWriter.close();

                // send file content
                long offset = Long.valueOf(datanode.get("Offset"));
                long length = Long.valueOf(datanode.get("Length"));
                long currOffset = 0L;
                int tmpLength;
                File file = new File(path);
                FileInputStream fileInputStream = new FileInputStream(file);
//                byte[] bytes = new byte[Constant.BYTES_SIZE];
                byte[] bytes = new byte[(int)length];
                byte[] tmpBytes;
                if (offset != 0) {
                    tmpBytes = new byte[(int)offset];
                    fileInputStream.read(tmpBytes);
                }

                fileInputStream.read(bytes);
                outputStream.write(bytes);

//                while ((tmpLength = fileInputStream.read(bytes)) != -1) {
//                    if (currOffset + tmpLength < offset) {
//                        currOffset += tmpLength;
//                    } else if (currOffset < offset) {
//                        outputStream.write(bytes, (int)(offset - currOffset), Math.min((int)(currOffset + tmpLength - offset), tmpLength));
//                        currOffset += tmpLength;
//                    } else if (currOffset + tmpLength < offset + length) {
//                        outputStream.write(bytes, 0, tmpLength);
//                        currOffset += tmpLength;
//                    } else if (currOffset < offset + length) {
//                        outputStream.write(bytes, 0, Math.min((int)(currOffset + tmpLength - offset - length), tmpLength));
//                        currOffset += tmpLength;
//                    } else {
//                        break;
//                    }
//                }

//                fileInputStream.close();
//                outputStream.close();
//                socket.close();
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}

class DownloadClient implements Runnable {

    private Map<String, String> slaves;

    public void setVariables (Map<String, String> slaves) {
        this.slaves = slaves;
    }

    public void run() {
        try {
            Iterator<Map.Entry<String, String>> entries;
            Map.Entry<String, String> entry;

            entries = slaves.entrySet().iterator();

            String path = "TEST.txt";
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }

            while (entries.hasNext()) {
                entry = entries.next();

                String filename = entry.getKey();
                String ip = entry.getValue().split(" ")[0];
                int socketPort = Integer.valueOf(entry.getValue().split(" ")[1]);

                Socket socket = new Socket(ip, socketPort);

                // send filename
                OutputStream outputStream = socket.getOutputStream();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

                bufferedWriter.write("*" + filename + "\n");
                bufferedWriter.flush();

//                bufferedWriter.close();
//                outputStreamWriter.close();

                // receive file content
                FileOutputStream fileOutputStream = new FileOutputStream(file, true);
//                byte[] bytes = new byte[Constant.BYTES_SIZE];
                byte[] bytes = new byte[(int)Constant.BLOCK_SIZE];
                InputStream inputStream = socket.getInputStream();
                int length = inputStream.read(bytes);
                fileOutputStream.write(bytes, 0, length);

//                while ((tmpLength = fileInputStream.read(bytes)) != -1) {
//                    if (currOffset + tmpLength < offset) {
//                        currOffset += tmpLength;
//                    } else if (currOffset < offset) {
//                        outputStream.write(bytes, (int)(offset - currOffset), Math.min((int)(currOffset + tmpLength - offset), tmpLength));
//                        currOffset += tmpLength;
//                    } else if (currOffset + tmpLength < offset + length) {
//                        outputStream.write(bytes, 0, tmpLength);
//                        currOffset += tmpLength;
//                    } else if (currOffset < offset + length) {
//                        outputStream.write(bytes, 0, Math.min((int)(currOffset + tmpLength - offset - length), tmpLength));
//                        currOffset += tmpLength;
//                    } else {
//                        break;
//                    }
//                }

//                fileInputStream.close();
//                outputStream.close();
//                socket.close();
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}
