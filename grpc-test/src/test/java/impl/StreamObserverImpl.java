package impl;

import cache.CacheResponse;
import com.roman.grpc.WebResourceServiceOuterClass;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static cache.CacheResponse.queueResponses;
import static cache.CacheResponse.response;

public class StreamObserverImpl implements StreamObserver<WebResourceServiceOuterClass.WebResourceResponse> {
    @Override
    public void onNext(WebResourceServiceOuterClass.WebResourceResponse webResourceResponse) {
        queueResponses.add(webResourceResponse);
        response = webResourceResponse;
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println(throwable.getMessage());
    }

    @Override
    public void onCompleted() {
    }
}
