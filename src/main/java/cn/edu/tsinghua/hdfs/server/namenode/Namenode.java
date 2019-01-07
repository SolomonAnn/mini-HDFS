package cn.edu.tsinghua.hdfs.server.namenode;

import cn.edu.tsinghua.hdfs.constant.Constant;
import cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolImpl;
import cn.edu.tsinghua.hdfs.protocol.DatanodeNamenodeProtocolImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

/**
 * @author: An
 * @description: Namenode
 */

public class Namenode {
    private Server server;

    private void start() throws IOException {
        System.out.println(Constant.NAMENODE_GREETING);

        server = ServerBuilder
                .forPort(Constant.RPC_PORT)
                .addService(new ClientNamenodeProtocolImpl())
                .addService(new DatanodeNamenodeProtocolImpl())
                .build()
                .start();

        System.out.println(Constant.NAMENODE_START);

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.err.println(Constant.NAMENODE_ERROR);
                Namenode.this.stop();
                System.err.println(Constant.NAMENODE_SHUTDOWN);
            }
        });
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Constant.NAMENODE_IP = args[0];
        Constant.NAMENODE_PATH = args[1];
        Constant.DATANODE_IP = args[2];
        Constant.DATANODE_PATH = args[3];
        Constant.RPC_PORT = Integer.valueOf(args[4]);
        Constant.SOCKET_PORT = Integer.valueOf(args[5]);

        final Namenode namenode = new Namenode();
        namenode.start();
        namenode.blockUntilShutdown();
    }
}
