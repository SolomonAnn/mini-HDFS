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
    comments = "Source: ClientNamenodeProtocol.proto")
public final class ClientNamenodeProtocolGrpc {

  private ClientNamenodeProtocolGrpc() {}

  public static final String SERVICE_NAME = "cn.edu.cn.tsinghua.hdfs.protocol.ClientNamenodeProtocol";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.InitRequestProto,
      cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.InitResponseProto> getInitMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "init",
      requestType = cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.InitRequestProto.class,
      responseType = cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.InitResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.InitRequestProto,
      cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.InitResponseProto> getInitMethod() {
    io.grpc.MethodDescriptor<cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.InitRequestProto, cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.InitResponseProto> getInitMethod;
    if ((getInitMethod = ClientNamenodeProtocolGrpc.getInitMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getInitMethod = ClientNamenodeProtocolGrpc.getInitMethod) == null) {
          ClientNamenodeProtocolGrpc.getInitMethod = getInitMethod = 
              io.grpc.MethodDescriptor.<cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.InitRequestProto, cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.InitResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "cn.edu.cn.tsinghua.hdfs.protocol.ClientNamenodeProtocol", "init"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.InitRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.InitResponseProto.getDefaultInstance()))
                  .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("init"))
                  .build();
          }
        }
     }
     return getInitMethod;
  }

  private static volatile io.grpc.MethodDescriptor<cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.MkdirRequestProto,
      cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.MkdirResponseProto> getMkdirMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "mkdir",
      requestType = cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.MkdirRequestProto.class,
      responseType = cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.MkdirResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.MkdirRequestProto,
      cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.MkdirResponseProto> getMkdirMethod() {
    io.grpc.MethodDescriptor<cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.MkdirRequestProto, cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.MkdirResponseProto> getMkdirMethod;
    if ((getMkdirMethod = ClientNamenodeProtocolGrpc.getMkdirMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getMkdirMethod = ClientNamenodeProtocolGrpc.getMkdirMethod) == null) {
          ClientNamenodeProtocolGrpc.getMkdirMethod = getMkdirMethod = 
              io.grpc.MethodDescriptor.<cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.MkdirRequestProto, cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.MkdirResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "cn.edu.cn.tsinghua.hdfs.protocol.ClientNamenodeProtocol", "mkdir"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.MkdirRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.MkdirResponseProto.getDefaultInstance()))
                  .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("mkdir"))
                  .build();
          }
        }
     }
     return getMkdirMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ClientNamenodeProtocolStub newStub(io.grpc.Channel channel) {
    return new ClientNamenodeProtocolStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ClientNamenodeProtocolBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new ClientNamenodeProtocolBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ClientNamenodeProtocolFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new ClientNamenodeProtocolFutureStub(channel);
  }

  /**
   */
  public static abstract class ClientNamenodeProtocolImplBase implements io.grpc.BindableService {

    /**
     */
    public void init(cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.InitRequestProto request,
        io.grpc.stub.StreamObserver<cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.InitResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getInitMethod(), responseObserver);
    }

    /**
     */
    public void mkdir(cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.MkdirRequestProto request,
        io.grpc.stub.StreamObserver<cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.MkdirResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getMkdirMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getInitMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.InitRequestProto,
                cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.InitResponseProto>(
                  this, METHODID_INIT)))
          .addMethod(
            getMkdirMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.MkdirRequestProto,
                cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.MkdirResponseProto>(
                  this, METHODID_MKDIR)))
          .build();
    }
  }

  /**
   */
  public static final class ClientNamenodeProtocolStub extends io.grpc.stub.AbstractStub<ClientNamenodeProtocolStub> {
    private ClientNamenodeProtocolStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ClientNamenodeProtocolStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ClientNamenodeProtocolStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ClientNamenodeProtocolStub(channel, callOptions);
    }

    /**
     */
    public void init(cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.InitRequestProto request,
        io.grpc.stub.StreamObserver<cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.InitResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getInitMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void mkdir(cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.MkdirRequestProto request,
        io.grpc.stub.StreamObserver<cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.MkdirResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getMkdirMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class ClientNamenodeProtocolBlockingStub extends io.grpc.stub.AbstractStub<ClientNamenodeProtocolBlockingStub> {
    private ClientNamenodeProtocolBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ClientNamenodeProtocolBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ClientNamenodeProtocolBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ClientNamenodeProtocolBlockingStub(channel, callOptions);
    }

    /**
     */
    public cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.InitResponseProto init(cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.InitRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getInitMethod(), getCallOptions(), request);
    }

    /**
     */
    public cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.MkdirResponseProto mkdir(cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.MkdirRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getMkdirMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class ClientNamenodeProtocolFutureStub extends io.grpc.stub.AbstractStub<ClientNamenodeProtocolFutureStub> {
    private ClientNamenodeProtocolFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ClientNamenodeProtocolFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ClientNamenodeProtocolFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ClientNamenodeProtocolFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.InitResponseProto> init(
        cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.InitRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getInitMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.MkdirResponseProto> mkdir(
        cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.MkdirRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getMkdirMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_INIT = 0;
  private static final int METHODID_MKDIR = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ClientNamenodeProtocolImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ClientNamenodeProtocolImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_INIT:
          serviceImpl.init((cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.InitRequestProto) request,
              (io.grpc.stub.StreamObserver<cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.InitResponseProto>) responseObserver);
          break;
        case METHODID_MKDIR:
          serviceImpl.mkdir((cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.MkdirRequestProto) request,
              (io.grpc.stub.StreamObserver<cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.MkdirResponseProto>) responseObserver);
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

  private static abstract class ClientNamenodeProtocolBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ClientNamenodeProtocolBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ClientNamenodeProtocol");
    }
  }

  private static final class ClientNamenodeProtocolFileDescriptorSupplier
      extends ClientNamenodeProtocolBaseDescriptorSupplier {
    ClientNamenodeProtocolFileDescriptorSupplier() {}
  }

  private static final class ClientNamenodeProtocolMethodDescriptorSupplier
      extends ClientNamenodeProtocolBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ClientNamenodeProtocolMethodDescriptorSupplier(String methodName) {
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
      synchronized (ClientNamenodeProtocolGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ClientNamenodeProtocolFileDescriptorSupplier())
              .addMethod(getInitMethod())
              .addMethod(getMkdirMethod())
              .build();
        }
      }
    }
    return result;
  }
}
