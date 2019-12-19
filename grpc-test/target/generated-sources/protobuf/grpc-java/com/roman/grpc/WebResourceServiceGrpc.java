package com.roman.grpc;

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
    value = "by gRPC proto compiler (version 1.21.0)",
    comments = "Source: webResourceService.proto")
public final class WebResourceServiceGrpc {

  private WebResourceServiceGrpc() {}

  public static final String SERVICE_NAME = "WebResourceService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.roman.grpc.WebResourceServiceOuterClass.WebResourceRequest,
      com.roman.grpc.WebResourceServiceOuterClass.WebResourceResponse> getCheckResourceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CheckResource",
      requestType = com.roman.grpc.WebResourceServiceOuterClass.WebResourceRequest.class,
      responseType = com.roman.grpc.WebResourceServiceOuterClass.WebResourceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.roman.grpc.WebResourceServiceOuterClass.WebResourceRequest,
      com.roman.grpc.WebResourceServiceOuterClass.WebResourceResponse> getCheckResourceMethod() {
    io.grpc.MethodDescriptor<com.roman.grpc.WebResourceServiceOuterClass.WebResourceRequest, com.roman.grpc.WebResourceServiceOuterClass.WebResourceResponse> getCheckResourceMethod;
    if ((getCheckResourceMethod = WebResourceServiceGrpc.getCheckResourceMethod) == null) {
      synchronized (WebResourceServiceGrpc.class) {
        if ((getCheckResourceMethod = WebResourceServiceGrpc.getCheckResourceMethod) == null) {
          WebResourceServiceGrpc.getCheckResourceMethod = getCheckResourceMethod = 
              io.grpc.MethodDescriptor.<com.roman.grpc.WebResourceServiceOuterClass.WebResourceRequest, com.roman.grpc.WebResourceServiceOuterClass.WebResourceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "WebResourceService", "CheckResource"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.roman.grpc.WebResourceServiceOuterClass.WebResourceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.roman.grpc.WebResourceServiceOuterClass.WebResourceResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new WebResourceServiceMethodDescriptorSupplier("CheckResource"))
                  .build();
          }
        }
     }
     return getCheckResourceMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static WebResourceServiceStub newStub(io.grpc.Channel channel) {
    return new WebResourceServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static WebResourceServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new WebResourceServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static WebResourceServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new WebResourceServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class WebResourceServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void checkResource(com.roman.grpc.WebResourceServiceOuterClass.WebResourceRequest request,
        io.grpc.stub.StreamObserver<com.roman.grpc.WebResourceServiceOuterClass.WebResourceResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCheckResourceMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCheckResourceMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.roman.grpc.WebResourceServiceOuterClass.WebResourceRequest,
                com.roman.grpc.WebResourceServiceOuterClass.WebResourceResponse>(
                  this, METHODID_CHECK_RESOURCE)))
          .build();
    }
  }

  /**
   */
  public static final class WebResourceServiceStub extends io.grpc.stub.AbstractStub<WebResourceServiceStub> {
    private WebResourceServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private WebResourceServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected WebResourceServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new WebResourceServiceStub(channel, callOptions);
    }

    /**
     */
    public void checkResource(com.roman.grpc.WebResourceServiceOuterClass.WebResourceRequest request,
        io.grpc.stub.StreamObserver<com.roman.grpc.WebResourceServiceOuterClass.WebResourceResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCheckResourceMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class WebResourceServiceBlockingStub extends io.grpc.stub.AbstractStub<WebResourceServiceBlockingStub> {
    private WebResourceServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private WebResourceServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected WebResourceServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new WebResourceServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.roman.grpc.WebResourceServiceOuterClass.WebResourceResponse checkResource(com.roman.grpc.WebResourceServiceOuterClass.WebResourceRequest request) {
      return blockingUnaryCall(
          getChannel(), getCheckResourceMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class WebResourceServiceFutureStub extends io.grpc.stub.AbstractStub<WebResourceServiceFutureStub> {
    private WebResourceServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private WebResourceServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected WebResourceServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new WebResourceServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.roman.grpc.WebResourceServiceOuterClass.WebResourceResponse> checkResource(
        com.roman.grpc.WebResourceServiceOuterClass.WebResourceRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCheckResourceMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CHECK_RESOURCE = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final WebResourceServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(WebResourceServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CHECK_RESOURCE:
          serviceImpl.checkResource((com.roman.grpc.WebResourceServiceOuterClass.WebResourceRequest) request,
              (io.grpc.stub.StreamObserver<com.roman.grpc.WebResourceServiceOuterClass.WebResourceResponse>) responseObserver);
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

  private static abstract class WebResourceServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    WebResourceServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.roman.grpc.WebResourceServiceOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("WebResourceService");
    }
  }

  private static final class WebResourceServiceFileDescriptorSupplier
      extends WebResourceServiceBaseDescriptorSupplier {
    WebResourceServiceFileDescriptorSupplier() {}
  }

  private static final class WebResourceServiceMethodDescriptorSupplier
      extends WebResourceServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    WebResourceServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (WebResourceServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new WebResourceServiceFileDescriptorSupplier())
              .addMethod(getCheckResourceMethod())
              .build();
        }
      }
    }
    return result;
  }
}
