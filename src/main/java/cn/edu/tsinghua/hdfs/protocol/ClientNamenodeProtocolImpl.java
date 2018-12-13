package cn.edu.tsinghua.hdfs.protocol;

import cn.edu.tsinghua.hdfs.constant.Constant;
import io.grpc.stub.StreamObserver;

import java.io.File;
import java.util.List;

/**
 * @author: An
 * @description: implement services defined in ClientNamenodeProtocol.proto
 */

public class ClientNamenodeProtocolImpl extends ClientNamenodeProtocolGrpc.ClientNamenodeProtocolImplBase {

    @Override
    public void init(ClientNamenodeProtocolProtos.InitRequestProto request, StreamObserver<ClientNamenodeProtocolProtos.InitResponseProto> responseObserver) {
        ClientNamenodeProtocolProtos.InitResponseProto response = ClientNamenodeProtocolProtos.InitResponseProto
                .newBuilder()
                .setPath(ClientNamenodeProtocolProtos.PathProto
                        .newBuilder()
                        .setSrc(System.getProperty("user.dir"))
                        .setHasPermission(true)
                        .build())
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void mkdir(ClientNamenodeProtocolProtos.MkdirRequestProto request, StreamObserver<ClientNamenodeProtocolProtos.MkdirResponseProto> responseObserver) {
        ClientNamenodeProtocolProtos.MkdirResponseProto response = ClientNamenodeProtocolProtos.MkdirResponseProto
                .newBuilder()
                .setPath(ClientNamenodeProtocolProtos.PathProto
                        .newBuilder()
                        .setSrc(System.getProperty("user.dir"))
                        .setHasPermission(true)
                        .build())
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
}
