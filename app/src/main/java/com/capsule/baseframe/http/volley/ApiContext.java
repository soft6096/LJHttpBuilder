package com.capsule.baseframe.http.volley;

import android.content.Context;

import com.android.volley.RequestQueue;

import java.util.Map;

/**
 * Created by kakalee on 15/7/29.
 */
public interface ApiContext {

    Context getContext();

    RequestQueue getRequestQueue();

    Map<String, String> getHeaders();

    String getCacheKeyPostfix();

    long getCacheTime();
}
