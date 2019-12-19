package cache;

import com.roman.grpc.WebResourceServiceOuterClass;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class CacheResponse {

    public static Queue<WebResourceServiceOuterClass.WebResourceResponse> queueResponses = new ConcurrentLinkedQueue<>();
    public static WebResourceServiceOuterClass.WebResourceResponse response;
}
