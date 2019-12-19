package args;

import static junitparams.JUnitParamsRunner.$;

public class WebResourcesArgs {
    public static Object[] webResources() {
        return $(
                $("google.com", 200),
                $("olx.ua", 200),
                $("rabota.ua", 200),
                $("work.ua", 200),
                $("youtube.com", 200),
                $("facebook.com", 200),
                $("lingualeo.com", 200),
                $("englishdom.com", 200),
                $("fluentu.com", 200),
                $("alison.com", 200),
                $("mooec.com", 200),
                $("futurelearn.com", 200),
                $("edx.org", 200),
                $("grpc.io", 200),
                $("openlearning.com", 200),
                $("canvas.net", 200),
                $("coursera.org", 200),
                $("stackoverflow.com", 200),
                $("xzxzxzxzxz", -1),
                $("vk.com", -1)
                );
    }
}
