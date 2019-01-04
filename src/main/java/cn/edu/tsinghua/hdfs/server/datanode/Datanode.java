package cn.edu.tsinghua.hdfs.server.datanode;

import cn.edu.tsinghua.hdfs.constant.Constant;
import cn.edu.tsinghua.hdfs.protocol.DatanodeNamenodeProtocolGrpc;
import cn.edu.tsinghua.hdfs.protocol.DatanodeNamenodeProtocolProtos;
import cn.edu.tsinghua.hdfs.protocol.HdfsProtocolProtos;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author: An
 * @description: Datanode
 */

public class Datanode {
    private static final Logger logger = Logger.getLogger(Constant.CLIENT_GREETING);

    private final ManagedChannel channel;
    private final DatanodeNamenodeProtocolGrpc.DatanodeNamenodeProtocolBlockingStub blockingStub;

    public String ip;
    public int rpcPort;
    public int socketPort;

    private boolean hasRegistered;

    public Datanode(String ip, int rpcPort, int socketRpc) {
        this.channel = ManagedChannelBuilder
                .forAddress(ip, rpcPort)
                .usePlaintext()
                .build();
        this.blockingStub = DatanodeNamenodeProtocolGrpc.newBlockingStub(channel);
        this.ip = ip;
        this.rpcPort = rpcPort;
        this.socketPort = socketRpc;
    }

    public void register() {
        DatanodeNamenodeProtocolProtos.RegisterRequestProto request = DatanodeNamenodeProtocolProtos.RegisterRequestProto
                .newBuilder()
                .setId(HdfsProtocolProtos.IdProto
                        .newBuilder()
                        .setIp(ip)
                        .setRpcPort(rpcPort)
                        .setSocketPort(socketPort)
                        .build())
                .build();
        DatanodeNamenodeProtocolProtos.RegisterResponseProto response;
        try {
            response = blockingStub.register(request);
            hasRegistered = response.getIsSuccessful();
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            return;
        }
        return;
    }

    public boolean heartbeat() {
        DatanodeNamenodeProtocolProtos.HeartbeatRequestProto request = DatanodeNamenodeProtocolProtos.HeartbeatRequestProto
                .newBuilder()
                .setId(HdfsProtocolProtos.IdProto
                        .newBuilder()
                        .setIp(ip)
                        .setRpcPort(rpcPort)
                        .setSocketPort(socketPort)
                        .build())
                .setHasRegistered(hasRegistered)
                .build();
        DatanodeNamenodeProtocolProtos.HeartbeatResponseProto response;
        try {
            response = blockingStub.heartbeat(request);
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            return false;
        }
        return response.getIsSuccessful();
    }

    public static void main(String[] args) throws Exception {
        String ip = Constant.IP;
        int rpcPort = Constant.PORT;
        int socketPort = 1113;
        Datanode datanode = new Datanode(ip, rpcPort, socketPort);

        try {
            datanode.register();

            Heartbeat heartbeat = new Heartbeat();
            heartbeat.setDatanode(datanode);
            Thread threadHeartbeat = new Thread(heartbeat);
            threadHeartbeat.start();

            Server server = new Server();
            server.setDatanode(datanode);
            Thread threadServer = new Thread(server);
            threadServer.start();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}

class Heartbeat implements Runnable {

    private Datanode datanode;

    public void setDatanode(Datanode datanode) {
        this.datanode = datanode;
    }

    public void run() {
        while (true) {
            try {
                datanode.heartbeat();
                Thread.sleep(Constant.HEARTBEAT);
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }
        }
    }
}

class Server implements Runnable {

    private Datanode datanode;

    public void setDatanode(Datanode datanode) {
        this.datanode = datanode;
    }

    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.setReuseAddress(true);
            serverSocket.bind(new InetSocketAddress(datanode.socketPort));

            while (true) {
                Socket socket = serverSocket.accept();

                ServerHandler ServerHandler = new ServerHandler();
                ServerHandler.setVariables(datanode, socket);
                Thread threadUploadServerHandler = new Thread(ServerHandler);
                threadUploadServerHandler.start();

//                socket.close();
            }

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}

class ServerHandler implements Runnable {

    private Datanode datanode;

    private Socket socket;

    public void setVariables(Datanode datanode, Socket socket) {
        this.datanode = datanode;
        this.socket = socket;
    }

    public void run() {
        try {
            // receive filename
            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String filename = bufferedReader.readLine();

            if (filename.startsWith("*")) {
                filename = filename.substring(1);
                File file = new File(filename);
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] bytes = new byte[(int)Constant.BLOCK_SIZE];
                int length = fileInputStream.read(bytes);
                OutputStream outputStream = socket.getOutputStream();
                outputStream.write(bytes, 0, length);

            } else {
                // receive file content
                File file = new File(filename);
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                byte[] bytes = new byte[(int)Constant.BLOCK_SIZE];

                int length = inputStream.read(bytes);

                fileOutputStream.write(bytes, 0, length);

//            byte[] bytes = new byte[Constant.BYTES_SIZE];
//            int length;
//
//            while ((length = inputStream.read(bytes)) != -1) {
//                fileOutputStream.write(bytes, 0, length);
//            }
//            fileOutputStream.close();

//            bufferedReader.close();
//            inputStreamReader.close();
//            inputStream.close();
            }


        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}
