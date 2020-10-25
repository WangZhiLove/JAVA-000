package com.wangzhi.okhttp;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;

public class OKHttpClient {
    public static void main(String[] args) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request.Builder builder = new Request.Builder().url("http://localhost:8801");
        Request build = builder.build();
        Response execute = okHttpClient.newCall(build).execute();
        if (execute.isSuccessful()) {
            // 读取到的内容是空，和socket缓冲池有关
            System.out.println(execute.body());
        }

    }
}
