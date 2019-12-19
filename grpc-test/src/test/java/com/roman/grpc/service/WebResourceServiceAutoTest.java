package com.roman.grpc.service;

import args.WebResourcesArgs;
import cache.CacheResponse;
import com.roman.grpc.CoreApplication;
import com.roman.grpc.WebResourceServiceGrpc;
import com.roman.grpc.WebResourceServiceOuterClass;
import impl.StreamObserverImpl;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static cache.CacheResponse.queueResponses;
import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class WebResourceServiceAutoTest {

    private static ManagedChannel channel;

    private StreamObserverImpl streamObserver = new StreamObserverImpl();

    private ExecutorService es = Executors.newFixedThreadPool(3);

    @BeforeClass
    public static void startServer() {
        CoreApplication.main(new String[0]);
        channel = ManagedChannelBuilder.forAddress("localhost", 8888)
                .usePlaintext()
                .build();
    }

    @Test
    @Parameters(source = WebResourcesArgs.class, method = "webResources")
    @TestCaseName("method={0} [{index}] --> This cases check web resources(1 Thread - blocking)")
    public void checkResourceSingleBlockingCallTest(String resourceExp, int expCode) {
        WebResourceServiceGrpc.WebResourceServiceBlockingStub stub
                = WebResourceServiceGrpc.newBlockingStub(channel);

        WebResourceServiceOuterClass.WebResourceResponse res = stub.checkResource(WebResourceServiceOuterClass.WebResourceRequest.newBuilder()
                .setResource(resourceExp)
                .build());

        if (res == null) {
            fail();
        }

        int codeAct = res.getResponseCode();
        final String resourceAct = res.getResource();
        long timestampAct = res.getTimestamp();

        assertTrue("Time delay should be more than 0", timestampAct  > 0L);
        assertEquals(resourceExp, resourceAct);
        assertEquals(expCode, codeAct);
    }

    @Test
    @Parameters(source = WebResourcesArgs.class, method = "webResources")
    @TestCaseName("method={0} [{index}] --> This cases check web resources(1 Thread - non blocking)")
    public void checkResourceSingleUnBlockingCallTest(String resourceExp, int expCode) throws InterruptedException {

        WebResourceServiceGrpc.WebResourceServiceStub stub
                = WebResourceServiceGrpc.newStub(channel);

        stub.checkResource(WebResourceServiceOuterClass.WebResourceRequest.newBuilder()
                .setResource(resourceExp)
                .build(), streamObserver);

        CacheResponse.response = null;
        Thread.sleep(2000);
        WebResourceServiceOuterClass.WebResourceResponse res = CacheResponse.response;
        if (res == null) {
            fail();
        }

        int codeAct = res.getResponseCode();
        String resourceAct = res.getResource();
        long timestampAct = res.getTimestamp();

        assertTrue("Time delay should be more than 0", timestampAct > 0L);
        assertEquals(resourceExp, resourceAct);
        assertEquals(expCode, codeAct);
    }



    @Test
    @TestCaseName("method={0} [{index}] --> This cases check web resources(Multi Thread - blocking)")
    public void checkResourceMultiBlockingCallTest() throws InterruptedException {
        WebResourceServiceGrpc.WebResourceServiceBlockingStub stub
                = WebResourceServiceGrpc.newBlockingStub(channel);
        List<String> resources = new ArrayList<>(Arrays.asList("google.com", "olx.ua", "rabota.ua", "work.ua", "youtube.com"));

        for (String resourse: resources) {
            es.submit(() -> {
                WebResourceServiceOuterClass.WebResourceResponse res = stub.checkResource(WebResourceServiceOuterClass.WebResourceRequest.newBuilder()
                    .setResource(resourse)
                    .build());
                CacheResponse.queueResponses.add(res);
            });
        }
        es.shutdown();
        es.awaitTermination(5, TimeUnit.SECONDS);

        while (!resources.isEmpty()) {

            WebResourceServiceOuterClass.WebResourceResponse res = queueResponses.poll();

            if (res != null) {
                int codeAct = res.getResponseCode();
                String resourceAct = res.getResource();
                long timestampAct = res.getTimestamp();

                assertTrue("Time delay should be more than 0", timestampAct > 0L);
                assertTrue(resources.remove(resourceAct));
                assertEquals(200, codeAct);
            }
        }
    }

    @Test
    @TestCaseName("method={0} [{index}] --> This cases check web resources(Multi Thread - non blocking)")
    public void checkResourceMultiNonBlockingCallTest() throws InterruptedException {
        WebResourceServiceGrpc.WebResourceServiceStub stub
                = WebResourceServiceGrpc.newStub(channel);
        List<String> resources = new ArrayList<>(Arrays.asList("google.com", "olx.ua", "rabota.ua", "work.ua", "youtube.com"));

        for (String resourse: resources) {
            es.submit(() -> {
                stub.checkResource(WebResourceServiceOuterClass.WebResourceRequest.newBuilder()
                        .setResource(resourse)
                        .build(), streamObserver);
            });
        }
        es.shutdown();
        es.awaitTermination(5, TimeUnit.SECONDS);

        while (!resources.isEmpty()) {

            WebResourceServiceOuterClass.WebResourceResponse res = queueResponses.poll();

            if (res != null) {
                int codeAct = res.getResponseCode();
                String resourceAct = res.getResource();
                long timestampAct = res.getTimestamp();

                assertTrue("Time delay should be more than 0", timestampAct > 0L);
                assertTrue(resources.remove(resourceAct));
                assertEquals(200, codeAct);
            }
        }
    }
}