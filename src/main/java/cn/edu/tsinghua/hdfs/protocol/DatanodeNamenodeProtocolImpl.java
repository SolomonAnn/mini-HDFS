package cn.edu.tsinghua.hdfs.protocol;

import cn.edu.tsinghua.hdfs.constant.Constant;
import io.grpc.stub.StreamObserver;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.List;

/**
 * @author: An
 * @description: implement services defined in DatanodeNamenodeProtocol.proto
 */

public class DatanodeNamenodeProtocolImpl extends DatanodeNamenodeProtocolGrpc.DatanodeNamenodeProtocolImplBase{

    private List<String> datanodes = new LinkedList<String>();

    @Override
    public void register(DatanodeNamenodeProtocolProtos.RegisterRequestProto request, StreamObserver<DatanodeNamenodeProtocolProtos.RegisterResponseProto> responseObserver) {
        boolean isSuccessful = true;
        String ip = request.getId().getIp();
        int rpcPort = request.getId().getRpcPort();
        int socketPort = request.getId().getSocketPort();
        String[] info;
        for (String value : datanodes) {
            info = value.split(" ");
            // one ip <--> many ports
            if (info[1].equals(String.valueOf(rpcPort)) && info[2].equals(String.valueOf(socketPort))) {
                isSuccessful = false;
                break;
            }
        }
        if (isSuccessful) {
            datanodes.add(ip + " " + rpcPort + " " + socketPort);
        }

        try {
            File slaveList = new File(Constant.NAMENODE_PATH + Constant.DIRECTORY_PREFIX + Constant.SLAVESLIST);
            if (!slaveList.exists()) {
                slaveList.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(slaveList);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (String value : datanodes) {
                bufferedWriter.write(value + "\r\n");
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        DatanodeNamenodeProtocolProtos.RegisterResponseProto response = DatanodeNamenodeProtocolProtos.RegisterResponseProto
                .newBuilder()
                .setIsSuccessful(isSuccessful)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void heartbeat(DatanodeNamenodeProtocolProtos.HeartbeatRequestProto request, StreamObserver<DatanodeNamenodeProtocolProtos.HeartbeatResponseProto> responseObserver) {
        boolean isSuccessful = false;
        String ip = request.getId().getIp();
        int rpcPort = request.getId().getRpcPort();
        int socketPort = request.getId().getSocketPort();
        boolean hasRegistered = request.getHasRegistered();
        String[] info;
        for (String value : datanodes) {
            info = value.split(" ");
            // one ip <--> many ports
            if (hasRegistered && info[0].equals(ip) && info[1].equals(String.valueOf(rpcPort)) && info[2].equals(String.valueOf(socketPort))) {
                isSuccessful = true;
                break;
            }
        }
        if (isSuccessful) {
//            datanodes.add(ip + " " + rpcPort + " " + socketPort);
        }
        DatanodeNamenodeProtocolProtos.HeartbeatResponseProto response = DatanodeNamenodeProtocolProtos.HeartbeatResponseProto
                .newBuilder()
                .setIsSuccessful(isSuccessful)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
