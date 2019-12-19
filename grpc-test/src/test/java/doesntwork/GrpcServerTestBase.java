package doesntwork;

import com.roman.grpc.WebResourceServiceGrpc;
import com.roman.grpc.WebResourceServiceOuterClass;
import com.roman.grpc.service.WebResourceService;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.inprocess.InProcessChannelBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lognet.springboot.grpc.GRpcServerRunner;
import org.lognet.springboot.grpc.autoconfigure.GRpcServerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

public class GrpcServerTestBase {

    @Autowired(required = false)
    @Qualifier("grpcServerRunner")
    protected GRpcServerRunner grpcServerRunner;

    @Autowired(required = false)
    @Qualifier("grpcInprocessServerRunner")
    protected GRpcServerRunner grpcInprocessServerRunner;


    protected ManagedChannel channel;
    protected ManagedChannel inProcChannel;

    @Autowired
    protected ApplicationContext context;

    @Autowired
    protected GRpcServerProperties gRpcServerProperties;

    @Before
    public final void setupChannels() {
        gRpcServerProperties.setEnabled(false);
        gRpcServerProperties.setInProcessServerName("myTestServer");
        if(gRpcServerProperties.isEnabled()) {
            channel = onChannelBuild(ManagedChannelBuilder.forAddress("localhost",getPort() )
                    .usePlaintext(true)
            ).build();
        }
        if(StringUtils.hasText(gRpcServerProperties.getInProcessServerName())){
            inProcChannel = onChannelBuild(
                    InProcessChannelBuilder.forName(gRpcServerProperties.getInProcessServerName())
                            .usePlaintext(true)
            ).build();

        }
    }
    protected int getPort(){
        return  gRpcServerProperties.getPort();
    }

    protected ManagedChannelBuilder<?>  onChannelBuild(ManagedChannelBuilder<?> channelBuilder){
        return  channelBuilder;
    }

    protected InProcessChannelBuilder onChannelBuild(InProcessChannelBuilder channelBuilder){
        return  channelBuilder;
    }

    @After
    public final void shutdownChannels() {
        Optional.ofNullable(channel).ifPresent(ManagedChannel::shutdownNow);
        Optional.ofNullable(inProcChannel).ifPresent(ManagedChannel::shutdownNow);
    }

    @Test
    final public void simpleGreeting() throws ExecutionException, InterruptedException {

        final WebResourceServiceGrpc.WebResourceServiceFutureStub futureStub = WebResourceServiceGrpc.newFutureStub(Optional.ofNullable(channel).orElse(inProcChannel));
        final WebResourceServiceOuterClass.WebResourceRequest request = WebResourceServiceOuterClass.WebResourceRequest.newBuilder()
                .setResource("google.com")
                .build();
        int reply = futureStub.checkResource(request).get().getResponseCode();
        final String resource = futureStub.checkResource(request).get().getResource();
        long timestamp = futureStub.checkResource(request).get().getTimestamp();

        assertTrue("Replay should not be null", timestamp  > 0L);
        assertEquals("google.com", resource);
        assertEquals(200, reply);

    }
}