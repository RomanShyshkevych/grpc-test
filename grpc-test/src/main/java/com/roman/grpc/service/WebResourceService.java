package com.roman.grpc.service;

import com.roman.grpc.WebResourceServiceGrpc;
import com.roman.grpc.WebResourceServiceOuterClass;
import io.grpc.stub.StreamObserver;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@GRpcService
public class WebResourceService extends WebResourceServiceGrpc.WebResourceServiceImplBase {

    private static final Logger LOG = LoggerFactory.getLogger(WebResourceService.class);

    private static final String START_URL = "http://www.";
    private static final int TIMEOUT = 2000;

    @Override
    public void checkResource(WebResourceServiceOuterClass.WebResourceRequest request,
                              StreamObserver<WebResourceServiceOuterClass.WebResourceResponse> responseObserver) {
        final WebResourceServiceOuterClass.WebResourceResponse.Builder replyBuilder =
                WebResourceServiceOuterClass.WebResourceResponse.newBuilder();

        LOG.info(String.format("============> checkResource for %s", request.getResource()));

        try(CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(String.format("%s%s", START_URL, request.getResource()));
            httpGet.setConfig(RequestConfig.DEFAULT.custom().setConnectTimeout(TIMEOUT).build());
            long start = System.currentTimeMillis();
            CloseableHttpResponse response = client.execute(httpGet);
            long res = System.currentTimeMillis() - start;
            replyBuilder
                    .setResource(request.getResource())
                    .setResponseCode(response.getStatusLine().getStatusCode())
                    .setTimestamp(res);
        } catch (IOException e) {
//            responseObserver.onError(e);
            replyBuilder
                    .setResource(request.getResource())
                    .setResponseCode(-1)
                    .setTimestamp(1);
        }


        responseObserver.onNext(replyBuilder.build());
        responseObserver.onCompleted();
    }

}
