package cn.edu.tsinghua.hdfs.client;

import cn.edu.tsinghua.hdfs.constant.Constant;
import cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolGrpc;
import cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.util.Scanner;
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

    public String mkdir(String currentPath, String[] paths) {
        ClientNamenodeProtocolProtos.MkdirRequestProto.Builder request = ClientNamenodeProtocolProtos.MkdirRequestProto
                .newBuilder();
        for (String path : paths) {
            request.addPath(ClientNamenodeProtocolProtos.PathProto
                    .newBuilder()
                    .setSrc(currentPath + Constant.DIRECTORY_PREFIX + path)
                    .setHasPermission(true)
                    .build());
        }
        ClientNamenodeProtocolProtos.MkdirResponseProto response;
        try {
            response = blockingStub.mkdir(request.build());
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            return null;
        }
        return response.getPath().getSrc();
    }

    public static void main(String[] args) throws Exception {
        DFSClient client = new DFSClient(Constant.HOST, Constant.PORT);

        String command;
        String[] pieces;
        String currentPath;

        currentPath = client.init();
        System.out.println(currentPath + Constant.CMD_SUFFIX);
        try {
            while(scanner.hasNextLine()) {
                command = scanner.nextLine();
                if (command.startsWith(Constant.CMD_IDENTIFIER)) {
                    command = command.substring(Constant.CMD_IDENTIFIER.length() + 1);
                    pieces = command.split(" ");
                    if (pieces[0].equals(Constant.MKDIR)) {
                        command = command.substring(Constant.MKDIR.length() + 1);
                        pieces = command.split(" ");
                        currentPath = client.mkdir(currentPath, pieces);
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
