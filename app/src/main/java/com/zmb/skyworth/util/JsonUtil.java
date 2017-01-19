package com.zmb.skyworth.util;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by zhangmingbao on 16-11-4.
 */

public class JsonUtil {
    Gson gson = null;
    public String getJson(List<?> list)
    {
        gson = new Gson();
        String jsonstr =  gson.toJson(list);
        System.out.println(jsonstr);
        return jsonstr;
    }
}
