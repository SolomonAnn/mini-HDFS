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
  private static volatile io.grpc.MethodDescriptor<cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.CdRequestProto,
      cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.CdResponseProto> getCdMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "cd",
      requestType = cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.CdRequestProto.class,
      responseType = cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.CdResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.CdRequestProto,
      cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.CdResponseProto> getCdMethod() {
    io.grpc.MethodDescriptor<cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.CdRequestProto, cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.CdResponseProto> getCdMethod;
    if ((getCdMethod = ClientNamenodeProtocolGrpc.getCdMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getCdMethod = ClientNamenodeProtocolGrpc.getCdMethod) == null) {
          ClientNamenodeProtocolGrpc.getCdMethod = getCdMethod = 
              io.grpc.MethodDescriptor.<cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.CdRequestProto, cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.CdResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "cn.edu.cn.tsinghua.hdfs.protocol.ClientNamenodeProtocol", "cd"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.CdRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.CdResponseProto.getDefaultInstance()))
                  .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("cd"))
                  .build();
          }
        }
     }
     return getCdMethod;
  }

  private static volatile io.grpc.MethodDescriptor<cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.LsRequestProto,
      cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.LsResponseProto> getLsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ls",
      requestType = cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.LsRequestProto.class,
      responseType = cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.LsResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.LsRequestProto,
      cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.LsResponseProto> getLsMethod() {
    io.grpc.MethodDescriptor<cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.LsRequestProto, cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.LsResponseProto> getLsMethod;
    if ((getLsMethod = ClientNamenodeProtocolGrpc.getLsMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getLsMethod = ClientNamenodeProtocolGrpc.getLsMethod) == null) {
          ClientNamenodeProtocolGrpc.getLsMethod = getLsMethod = 
              io.grpc.MethodDescriptor.<cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.LsRequestProto, cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.LsResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "cn.edu.cn.tsinghua.hdfs.protocol.ClientNamenodeProtocol", "ls"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.LsRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.LsResponseProto.getDefaultInstance()))
                  .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("ls"))
                  .build();
          }
        }
     }
     return getLsMethod;
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

  private static volatile io.grpc.MethodDescriptor<cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.GetRequestProto,
      cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.GetResponseProto> getGetMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "get",
      requestType = cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.GetRequestProto.class,
      responseType = cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.GetResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.GetRequestProto,
      cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.GetResponseProto> getGetMethod() {
    io.grpc.MethodDescriptor<cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.GetRequestProto, cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.GetResponseProto> getGetMethod;
    if ((getGetMethod = ClientNamenodeProtocolGrpc.getGetMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getGetMethod = ClientNamenodeProtocolGrpc.getGetMethod) == null) {
          ClientNamenodeProtocolGrpc.getGetMethod = getGetMethod = 
              io.grpc.MethodDescriptor.<cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.GetRequestProto, cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.GetResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "cn.edu.cn.tsinghua.hdfs.protocol.ClientNamenodeProtocol", "get"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.GetRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.GetResponseProto.getDefaultInstance()))
                  .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("get"))
                  .build();
          }
        }
     }
     return getGetMethod;
  }

  private static volatile io.grpc.MethodDescriptor<cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.PutRequestProto,
      cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.PutResponseProto> getPutMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "put",
      requestType = cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.PutRequestProto.class,
      responseType = cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.PutResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.PutRequestProto,
      cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.PutResponseProto> getPutMethod() {
    io.grpc.MethodDescriptor<cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.PutRequestProto, cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.PutResponseProto> getPutMethod;
    if ((getPutMethod = ClientNamenodeProtocolGrpc.getPutMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getPutMethod = ClientNamenodeProtocolGrpc.getPutMethod) == null) {
          ClientNamenodeProtocolGrpc.getPutMethod = getPutMethod = 
              io.grpc.MethodDescriptor.<cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.PutRequestProto, cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.PutResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "cn.edu.cn.tsinghua.hdfs.protocol.ClientNamenodeProtocol", "put"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.PutRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.PutResponseProto.getDefaultInstance()))
                  .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("put"))
                  .build();
          }
        }
     }
     return getPutMethod;
  }

  private static volatile io.grpc.MethodDescriptor<cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.RmRequestProto,
      cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.RmResponseProto> getRmMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "rm",
      requestType = cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.RmRequestProto.class,
      responseType = cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.RmResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.RmRequestProto,
      cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.RmResponseProto> getRmMethod() {
    io.grpc.MethodDescriptor<cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.RmRequestProto, cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.RmResponseProto> getRmMethod;
    if ((getRmMethod = ClientNamenodeProtocolGrpc.getRmMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getRmMethod = ClientNamenodeProtocolGrpc.getRmMethod) == null) {
          ClientNamenodeProtocolGrpc.getRmMethod = getRmMethod = 
              io.grpc.MethodDescriptor.<cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.RmRequestProto, cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.RmResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "cn.edu.cn.tsinghua.hdfs.protocol.ClientNamenodeProtocol", "rm"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.RmRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.RmResponseProto.getDefaultInstance()))
                  .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("rm"))
                  .build();
          }
        }
     }
     return getRmMethod;
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
    public void cd(cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.CdRequestProto request,
        io.grpc.stub.StreamObserver<cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.CdResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getCdMethod(), responseObserver);
    }

    /**
     */
    public void ls(cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.LsRequestProto request,
        io.grpc.stub.StreamObserver<cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.LsResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getLsMethod(), responseObserver);
    }

    /**
     */
    public void mkdir(cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.MkdirRequestProto request,
        io.grpc.stub.StreamObserver<cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.MkdirResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getMkdirMethod(), responseObserver);
    }

    /**
     */
    public void get(cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.GetRequestProto request,
        io.grpc.stub.StreamObserver<cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.GetResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getGetMethod(), responseObserver);
    }

    /**
     */
    public void put(cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.PutRequestProto request,
        io.grpc.stub.StreamObserver<cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.PutResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getPutMethod(), responseObserver);
    }

    /**
     */
    public void rm(cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.RmRequestProto request,
        io.grpc.stub.StreamObserver<cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.RmResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getRmMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCdMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.CdRequestProto,
                cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.CdResponseProto>(
                  this, METHODID_CD)))
          .addMethod(
            getLsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.LsRequestProto,
                cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.LsResponseProto>(
                  this, METHODID_LS)))
          .addMethod(
            getMkdirMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.MkdirRequestProto,
                cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.MkdirResponseProto>(
                  this, METHODID_MKDIR)))
          .addMethod(
            getGetMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.GetRequestProto,
                cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.GetResponseProto>(
                  this, METHODID_GET)))
          .addMethod(
            getPutMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.PutRequestProto,
                cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.PutResponseProto>(
                  this, METHODID_PUT)))
          .addMethod(
            getRmMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.RmRequestProto,
                cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.RmResponseProto>(
                  this, METHODID_RM)))
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
    public void cd(cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.CdRequestProto request,
        io.grpc.stub.StreamObserver<cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.CdResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCdMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void ls(cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.LsRequestProto request,
        io.grpc.stub.StreamObserver<cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.LsResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void mkdir(cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.MkdirRequestProto request,
        io.grpc.stub.StreamObserver<cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.MkdirResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getMkdirMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void get(cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.GetRequestProto request,
        io.grpc.stub.StreamObserver<cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.GetResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void put(cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.PutRequestProto request,
        io.grpc.stub.StreamObserver<cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.PutResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPutMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void rm(cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.RmRequestProto request,
        io.grpc.stub.StreamObserver<cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.RmResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRmMethod(), getCallOptions()), request, responseObserver);
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
    public cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.CdResponseProto cd(cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.CdRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getCdMethod(), getCallOptions(), request);
    }

    /**
     */
    public cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.LsResponseProto ls(cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.LsRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getLsMethod(), getCallOptions(), request);
    }

    /**
     */
    public cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.MkdirResponseProto mkdir(cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.MkdirRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getMkdirMethod(), getCallOptions(), request);
    }

    /**
     */
    public cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.GetResponseProto get(cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.GetRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getGetMethod(), getCallOptions(), request);
    }

    /**
     */
    public cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.PutResponseProto put(cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.PutRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getPutMethod(), getCallOptions(), request);
    }

    /**
     */
    public cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.RmResponseProto rm(cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.RmRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getRmMethod(), getCallOptions(), request);
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
    public com.google.common.util.concurrent.ListenableFuture<cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.CdResponseProto> cd(
        cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.CdRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getCdMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.LsResponseProto> ls(
        cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.LsRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getLsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.MkdirResponseProto> mkdir(
        cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.MkdirRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getMkdirMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.GetResponseProto> get(
        cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.GetRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getGetMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.PutResponseProto> put(
        cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.PutRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getPutMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.RmResponseProto> rm(
        cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.RmRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getRmMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CD = 0;
  private static final int METHODID_LS = 1;
  private static final int METHODID_MKDIR = 2;
  private static final int METHODID_GET = 3;
  private static final int METHODID_PUT = 4;
  private static final int METHODID_RM = 5;

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
        case METHODID_CD:
          serviceImpl.cd((cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.CdRequestProto) request,
              (io.grpc.stub.StreamObserver<cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.CdResponseProto>) responseObserver);
          break;
        case METHODID_LS:
          serviceImpl.ls((cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.LsRequestProto) request,
              (io.grpc.stub.StreamObserver<cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.LsResponseProto>) responseObserver);
          break;
        case METHODID_MKDIR:
          serviceImpl.mkdir((cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.MkdirRequestProto) request,
              (io.grpc.stub.StreamObserver<cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.MkdirResponseProto>) responseObserver);
          break;
        case METHODID_GET:
          serviceImpl.get((cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.GetRequestProto) request,
              (io.grpc.stub.StreamObserver<cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.GetResponseProto>) responseObserver);
          break;
        case METHODID_PUT:
          serviceImpl.put((cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.PutRequestProto) request,
              (io.grpc.stub.StreamObserver<cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.PutResponseProto>) responseObserver);
          break;
        case METHODID_RM:
          serviceImpl.rm((cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.RmRequestProto) request,
              (io.grpc.stub.StreamObserver<cn.edu.tsinghua.hdfs.protocol.ClientNamenodeProtocolProtos.RmResponseProto>) responseObserver);
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
              .addMethod(getCdMethod())
              .addMethod(getLsMethod())
              .addMethod(getMkdirMethod())
              .addMethod(getGetMethod())
              .addMethod(getPutMethod())
              .addMethod(getRmMethod())
              .build();
        }
      }
    }
    return result;
  }
}
