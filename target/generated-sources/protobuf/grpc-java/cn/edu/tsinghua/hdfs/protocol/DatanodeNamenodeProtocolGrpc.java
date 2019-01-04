package cn.edu.tsinghua.hdfs.protocol;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.17.1)",
    comments = "Source: DatanodeNamenodeProtocol.proto")
public final class DatanodeNamenodeProtocolGrpc {

  private DatanodeNamenodeProtocolGrpc() {}

  public static final String SERVICE_NAME = "cn.edu.cn.tsinghua.hdfs.protocol.DatanodeNamenodeProtocol";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<cn.edu.tsinghua.hdfs.protocol.DatanodeNamenodeProtocolProtos.RegisterRequestProto,
      cn.edu.tsinghua.hdfs.protocol.DatanodeNamenodeProtocolProtos.RegisterResponseProto> getRegisterMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "register",
      requestType = cn.edu.tsinghua.hdfs.protocol.DatanodeNamenodeProtocolProtos.RegisterRequestProto.class,
      responseType = cn.edu.tsinghua.hdfs.protocol.DatanodeNamenodeProtocolProtos.RegisterResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cn.edu.tsinghua.hdfs.protocol.DatanodeNamenodeProtocolProtos.RegisterRequestProto,
      cn.edu.tsinghua.hdfs.protocol.DatanodeNamenodeProtocolProtos.RegisterResponseProto> getRegisterMethod() {
    io.grpc.MethodDescriptor<cn.edu.tsinghua.hdfs.protocol.DatanodeNamenodeProtocolProtos.RegisterRequestProto, cn.edu.tsinghua.hdfs.protocol.DatanodeNamenodeProtocolProtos.RegisterResponseProto> getRegisterMethod;
    if ((getRegisterMethod = DatanodeNamenodeProtocolGrpc.getRegisterMethod) == null) {
      synchronized (DatanodeNamenodeProtocolGrpc.class) {
        if ((getRegisterMethod = DatanodeNamenodeProtocolGrpc.getRegisterMethod) == null) {
          DatanodeNamenodeProtocolGrpc.getRegisterMethod = getRegisterMethod = 
              io.grpc.MethodDescriptor.<cn.edu.tsinghua.hdfs.protocol.DatanodeNamenodeProtocolProtos.RegisterRequestProto, cn.edu.tsinghua.hdfs.protocol.DatanodeNamenodeProtocolProtos.RegisterResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "cn.edu.cn.tsinghua.hdfs.protocol.DatanodeNamenodeProtocol", "register"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cn.edu.tsinghua.hdfs.protocol.DatanodeNamenodeProtocolProtos.RegisterRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cn.edu.tsinghua.hdfs.protocol.DatanodeNamenodeProtocolProtos.RegisterResponseProto.getDefaultInstance()))
                  .setSchemaDescriptor(new DatanodeNamenodeProtocolMethodDescriptorSupplier("register"))
                  .build();
          }
        }
     }
     return getRegisterMethod;
  }

  private static volatile io.grpc.MethodDescriptor<cn.edu.tsinghua.hdfs.protocol.DatanodeNamenodeProtocolProtos.HeartbeatRequestProto,
      cn.edu.tsinghua.hdfs.protocol.DatanodeNamenodeProtocolProtos.HeartbeatResponseProto> getHeartbeatMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "heartbeat",
      requestType = cn.edu.tsinghua.hdfs.protocol.DatanodeNamenodeProtocolProtos.HeartbeatRequestProto.class,
      responseType = cn.edu.tsinghua.hdfs.protocol.DatanodeNamenodeProtocolProtos.HeartbeatResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cn.edu.tsinghua.hdfs.protocol.DatanodeNamenodeProtocolProtos.HeartbeatRequestProto,
      cn.edu.tsinghua.hdfs.protocol.DatanodeNamenodeProtocolProtos.HeartbeatResponseProto> getHeartbeatMethod() {
    io.grpc.MethodDescriptor<cn.edu.tsinghua.hdfs.protocol.DatanodeNamenodeProtocolProtos.HeartbeatRequestProto, cn.edu.tsinghua.hdfs.protocol.DatanodeNamenodeProtocolProtos.HeartbeatResponseProto> getHeartbeatMethod;
    if ((getHeartbeatMethod = DatanodeNamenodeProtocolGrpc.getHeartbeatMethod) == null) {
      synchronized (DatanodeNamenodeProtocolGrpc.class) {
        if ((getHeartbeatMethod = DatanodeNamenodeProtocolGrpc.getHeartbeatMethod) == null) {
          DatanodeNamenodeProtocolGrpc.getHeartbeatMethod = getHeartbeatMethod = 
              io.grpc.MethodDescriptor.<cn.edu.tsinghua.hdfs.protocol.DatanodeNamenodeProtocolProtos.HeartbeatRequestProto, cn.edu.tsinghua.hdfs.protocol.DatanodeNamenodeProtocolProtos.HeartbeatResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "cn.edu.cn.tsinghua.hdfs.protocol.DatanodeNamenodeProtocol", "heartbeat"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cn.edu.tsinghua.hdfs.protocol.DatanodeNamenodeProtocolProtos.HeartbeatRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cn.edu.tsinghua.hdfs.protocol.DatanodeNamenodeProtocolProtos.HeartbeatResponseProto.getDefaultInstance()))
                  .setSchemaDescriptor(new DatanodeNamenodeProtocolMethodDescriptorSupplier("heartbeat"))
                  .build();
          }
        }
     }
     return getHeartbeatMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static DatanodeNamenodeProtocolStub newStub(io.grpc.Channel channel) {
    return new DatanodeNamenodeProtocolStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static DatanodeNamenodeProtocolBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new DatanodeNamenodeProtocolBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static DatanodeNamenodeProtocolFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new DatanodeNamenodeProtocolFutureStub(channel);
  }

  /**
   */
  public static abstract class DatanodeNamenodeProtocolImplBase implements io.grpc.BindableService {

    /**
     */
    public void register(cn.edu.tsinghua.hdfs.protocol.DatanodeNamenodeProtocolProtos.RegisterRequestProto request,
        io.grpc.stub.StreamObserver<cn.edu.tsinghua.hdfs.protocol.DatanodeNamenodeProtocolProtos.RegisterResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getRegisterMethod(), responseObserver);
    }

    /**
     */
    public void heartbeat(cn.edu.tsinghua.hdfs.protocol.DatanodeNamenodeProtocolProtos.HeartbeatRequestProto request,
        io.grpc.stub.StreamObserver<cn.edu.tsinghua.hdfs.protocol.DatanodeNamenodeProtocolProtos.HeartbeatResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getHeartbeatMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getRegisterMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                cn.edu.tsinghua.hdfs.protocol.DatanodeNamenodeProtocolProtos.RegisterRequestProto,
                cn.edu.tsinghua.hdfs.protocol.DatanodeNamenodeProtocolProtos.RegisterResponseProto>(
                  this, METHODID_REGISTER)))
          .addMethod(
            getHeartbeatMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                cn.edu.tsinghua.hdfs.protocol.DatanodeNamenodeProtocolProtos.HeartbeatRequestProto,
                cn.edu.tsinghua.hdfs.protocol.DatanodeNamenodeProtocolProtos.HeartbeatResponseProto>(
                  this, METHODID_HEARTBEAT)))
          .build();
    }
  }

  /**
   */
  public static final class DatanodeNamenodeProtocolStub extends io.grpc.stub.AbstractStub<DatanodeNamenodeProtocolStub> {
    private DatanodeNamenodeProtocolStub(io.grpc.Channel channel) {
      super(channel);
    }

    private DatanodeNamenodeProtocolStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DatanodeNamenodeProtocolStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new DatanodeNamenodeProtocolStub(channel, callOptions);
    }

    /**
     */
    public void register(cn.edu.tsinghua.hdfs.protocol.DatanodeNamenodeProtocolProtos.RegisterRequestProto request,
        io.grpc.stub.StreamObserver<cn.edu.tsinghua.hdfs.protocol.DatanodeNamenodeProtocolProtos.RegisterResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRegisterMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void heartbeat(cn.edu.tsinghua.hdfs.protocol.DatanodeNamenodeProtocolProtos.HeartbeatRequestProto request,
        io.grpc.stub.StreamObserver<cn.edu.tsinghua.hdfs.protocol.DatanodeNamenodeProtocolProtos.HeartbeatResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getHeartbeatMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class DatanodeNamenodeProtocolBlockingStub extends io.grpc.stub.AbstractStub<DatanodeNamenodeProtocolBlockingStub> {
    private DatanodeNamenodeProtocolBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private DatanodeNamenodeProtocolBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DatanodeNamenodeProtocolBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new DatanodeNamenodeProtocolBlockingStub(channel, callOptions);
    }

    /**
     */
    public cn.edu.tsinghua.hdfs.protocol.DatanodeNamenodeProtocolProtos.RegisterResponseProto register(cn.edu.tsinghua.hdfs.protocol.DatanodeNamenodeProtocolProtos.RegisterRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getRegisterMethod(), getCallOptions(), request);
    }

    /**
     */
    public cn.edu.tsinghua.hdfs.protocol.DatanodeNamenodeProtocolProtos.HeartbeatResponseProto heartbeat(cn.edu.tsinghua.hdfs.protocol.DatanodeNamenodeProtocolProtos.HeartbeatRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getHeartbeatMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class DatanodeNamenodeProtocolFutureStub extends io.grpc.stub.AbstractStub<DatanodeNamenodeProtocolFutureStub> {
    private DatanodeNamenodeProtocolFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private DatanodeNamenodeProtocolFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DatanodeNamenodeProtocolFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new DatanodeNamenodeProtocolFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<cn.edu.tsinghua.hdfs.protocol.DatanodeNamenodeProtocolProtos.RegisterResponseProto> register(
        cn.edu.tsinghua.hdfs.protocol.DatanodeNamenodeProtocolProtos.RegisterRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getRegisterMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<cn.edu.tsinghua.hdfs.protocol.DatanodeNamenodeProtocolProtos.HeartbeatResponseProto> heartbeat(
        cn.edu.tsinghua.hdfs.protocol.DatanodeNamenodeProtocolProtos.HeartbeatRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getHeartbeatMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_REGISTER = 0;
  private static final int METHODID_HEARTBEAT = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final DatanodeNamenodeProtocolImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(DatanodeNamenodeProtocolImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_REGISTER:
          serviceImpl.register((cn.edu.tsinghua.hdfs.protocol.DatanodeNamenodeProtocolProtos.RegisterRequestProto) request,
              (io.grpc.stub.StreamObserver<cn.edu.tsinghua.hdfs.protocol.DatanodeNamenodeProtocolProtos.RegisterResponseProto>) responseObserver);
          break;
        case METHODID_HEARTBEAT:
          serviceImpl.heartbeat((cn.edu.tsinghua.hdfs.protocol.DatanodeNamenodeProtocolProtos.HeartbeatRequestProto) request,
              (io.grpc.stub.StreamObserver<cn.edu.tsinghua.hdfs.protocol.DatanodeNamenodeProtocolProtos.HeartbeatResponseProto>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class DatanodeNamenodeProtocolBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    DatanodeNamenodeProtocolBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return cn.edu.tsinghua.hdfs.protocol.DatanodeNamenodeProtocolProtos.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("DatanodeNamenodeProtocol");
    }
  }

  private static final class DatanodeNamenodeProtocolFileDescriptorSupplier
      extends DatanodeNamenodeProtocolBaseDescriptorSupplier {
    DatanodeNamenodeProtocolFileDescriptorSupplier() {}
  }

  private static final class DatanodeNamenodeProtocolMethodDescriptorSupplier
      extends DatanodeNamenodeProtocolBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    DatanodeNamenodeProtocolMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (DatanodeNamenodeProtocolGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new DatanodeNamenodeProtocolFileDescriptorSupplier())
              .addMethod(getRegisterMethod())
              .addMethod(getHeartbeatMethod())
              .build();
        }
      }
    }
    return result;
  }
}
