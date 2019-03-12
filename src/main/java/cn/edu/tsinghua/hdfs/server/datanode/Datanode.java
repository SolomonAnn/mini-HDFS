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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: An
 * @description: Datanode
 */

public class Datanode {
    private static final Logger LOGGER = LoggerFactory.getLogger(Datanode.class);

    private final ManagedChannel managedChannel;
    private final DatanodeNamenodeProtocolGrpc.DatanodeNamenodeProtocolBlockingStub
        datanodeNamenodeProtocolBlockingStub;

    public String ip;
    public int rpcPort;
    public int socketPort;

    private boolean hasRegistered;

    public Datanode(String ip, int rpcPort, int socketRpc) {
        this.managedChannel = ManagedChannelBuilder
                .forAddress(ip, rpcPort)
                .usePlaintext()
                .build();
        this.datanodeNamenodeProtocolBlockingStub =
            DatanodeNamenodeProtocolGrpc.newBlockingStub(managedChannel);
        this.ip = ip;
        this.rpcPort = rpcPort;
        this.socketPort = socketRpc;
    }

    public void register() {
        DatanodeNamenodeProtocolProtos.RegisterRequestProto request =
            DatanodeNamenodeProtocolProtos.RegisterRequestProto
                .newBuilder()
                .setId(HdfsProtocolProtos.IdProto
                        .newBuilder()
                        .setIp(Constant.DATANODE_IP)
                        .setRpcPort(rpcPort)
                        .setSocketPort(socketPort)
                        .build())
                .build();
        DatanodeNamenodeProtocolProtos.RegisterResponseProto response;
        try {
            response = datanodeNamenodeProtocolBlockingStub.register(request);
            hasRegistered = response.getIsSuccessful();
        } catch (StatusRuntimeException e) {
            LOGGER.warn("RPC failed: {0}", e.getStatus());
            return;
        }
        return;
    }

    public boolean heartbeat() {
        DatanodeNamenodeProtocolProtos.HeartbeatRequestProto request =
            DatanodeNamenodeProtocolProtos.HeartbeatRequestProto
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
            response = datanodeNamenodeProtocolBlockingStub.heartbeat(request);
        } catch (StatusRuntimeException e) {
            LOGGER.warn("RPC failed: {0}", e.getStatus());
            return false;
        }
        return response.getIsSuccessful();
    }

    public static void main(String[] args) throws Exception {
        Constant.NAMENODE_IP = args[0];
        Constant.NAMENODE_PATH = args[1];
        Constant.DATANODE_IP = args[2];
        Constant.DATANODE_PATH = args[3];
        Constant.RPC_PORT = Integer.valueOf(args[4]);
        Constant.SOCKET_PORT = Integer.valueOf(args[5]);

        Datanode datanode = new Datanode(Constant.NAMENODE_IP,
            Constant.RPC_PORT,
            Constant.SOCKET_PORT);

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
            DataInputStream dataInputStream = new DataInputStream(
                new BufferedInputStream(socket.getInputStream()));
            DataOutputStream dataOutputStream = new DataOutputStream(
                new BufferedOutputStream(socket.getOutputStream()));

            String op = dataInputStream.readUTF();
            String filename = dataInputStream.readUTF();

            if (op.equals(Constant.PUT)) {
                // receive file content
                File file = new File(Constant.DATANODE_PATH
                    + Constant.DIRECTORY_PREFIX
                    + filename);
                if (!file.exists()) {
                    file.createNewFile();
                }

                DataOutputStream fileDataOutputStream = new DataOutputStream(
                    new BufferedOutputStream(new FileOutputStream(file)));

                byte[] bytes = new byte[Constant.BYTES_SIZE];
                int length;

                while ((length = dataInputStream.read(bytes)) != -1) {
                    fileDataOutputStream.write(bytes, 0, length);
                    fileDataOutputStream.flush();
                }

                fileDataOutputStream.close();
                dataInputStream.close();
            } else if (op.equals(Constant.GET)){
                // send file content
                File file = new File(Constant.DATANODE_PATH
                    + Constant.DIRECTORY_PREFIX
                    + filename);
                if (!file.exists()) {
                    file.createNewFile();
                }
                DataInputStream fileDataInputStream = new DataInputStream(
                    new BufferedInputStream(new FileInputStream(file)));

                byte[] bytes = new byte[Constant.BYTES_SIZE];
                int length;

                while ((length = fileDataInputStream.read(bytes)) != -1) {
                    dataOutputStream.write(bytes, 0, length);
                    dataOutputStream.flush();
                }

                fileDataInputStream.close();
                dataOutputStream.close();
            }

            socket.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}
