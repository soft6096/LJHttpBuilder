package com.capsule.baseframe.http.request;

import com.alibaba.fastjson.JSON;
import com.android.volley.NetworkResponse;
import com.android.volley.toolbox.HttpHeaderParser;
import com.capsule.baseframe.http.volley.Parser;

import java.io.IOException;

/**
 * Created by kakalee on 15/7/30.
 */
public class JsonParser<T> implements Parser<T>{

    private Class<T> modelClass;

    public JsonParser(Class<T> modelClass) {
        this.modelClass = modelClass;
    }

    @Override
    public T parse(NetworkResponse response) throws IOException {
        String jsonString =
                new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        return JSON.parseObject(jsonString, modelClass);
    }
}
