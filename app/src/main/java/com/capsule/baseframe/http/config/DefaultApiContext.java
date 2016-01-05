package com.capsule.baseframe.http.config;

import android.annotation.SuppressLint;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.capsule.baseframe.config.GlobalConfig;
import com.capsule.baseframe.http.volley.ApiContext;
import com.capsule.baseframe.http.volley.util.VolleyUtils;

import java.io.File;
import java.util.Map;

/**
 * Created by kakalee on 15/7/30.
 */
public class DefaultApiContext implements ApiContext {

    private static final String API_CACHE_DIR = "volley-api";
    private static final String API_USER_AGENT = "capsule";

    private static final String CACHE_PATH = GlobalConfig.ROOT_PATH + File.separator + "cache";

    private static final int API_THREAD_POOL_SIZE = 4;
    private static final int API_CACHE_SIZE = 20 * 1024 * 1024; // 20M
    private static final int API_CACHE_FAKE_TIME = 30 * 60 * 1000; // 30 min

    private final Context context;
    private RequestQueue requestQueue;

    public DefaultApiContext(Context context) {
        this.context = context;
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = createRequestQueue();
        }
        return requestQueue;
    }

    @Override
    public Map<String, String> getHeaders() {
        return null;
    }

    @SuppressLint("NewApi")
    private RequestQueue createRequestQueue() {
        return VolleyUtils.newRequestQueue(new File(getFileCacheDir(), API_CACHE_DIR),
                API_USER_AGENT, API_CACHE_SIZE, API_THREAD_POOL_SIZE);
    }


    @Override
    public String getCacheKeyPostfix() {
        return null;
    }

    @Override
    public long getCacheTime() {
        return API_CACHE_FAKE_TIME;
    }

    private String getFileCacheDir() {
        return CACHE_PATH;
    }
}
