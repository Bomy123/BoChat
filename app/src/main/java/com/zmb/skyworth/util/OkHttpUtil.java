package com.zmb.skyworth.util;

import android.support.annotation.NonNull;

import com.zmb.skyworth.config.Constant;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by zhangmingbao on 16-10-22.
 */

public class OkHttpUtil {
private     final static OkHttpClient client = new OkHttpClient();
private final static String TAG = "OkHttpUtil";
    public static Response getResponse(@NonNull String url) {
        final Request request = new Request.Builder().get().url(url).build();
        return getResponse(request);
    }

    public static Response getResponse(@NonNull Request request) {
        Response response = null;
        try {
            response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                return response;
            } else {
                throw new IOException("get response form " + request.url() + "error:" + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public static String getResponseBodyString(@NonNull String url) throws IOException {
        return getResponse(url).body().string();

    }

    public static String getResponseBodyString(@NonNull Request request) throws IOException {
        return getResponse(request).body().string();
    }
public static String postJsonRequest(String json , String url) throws IOException {
    final MediaType JSON = MediaType.parse("application/json; charset=utf8");
    RequestBody requestBody = RequestBody.create(JSON,json);
    System.out.println("url:"+url);
    Request request = new Request.Builder().url(url).post(requestBody).build();
    Response response = client.newCall(request).execute();
    if(response.isSuccessful())
        return response.body().string();
    else
        throw new IOException(url+"?info="+json);
    }
}


