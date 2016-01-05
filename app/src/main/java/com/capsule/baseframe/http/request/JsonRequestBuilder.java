package com.capsule.baseframe.http.request;

import com.capsule.baseframe.http.config.ApiContextManager;
import com.capsule.baseframe.http.volley.ApiContext;

/**
 * Created by kakalee on 15/7/30.
 */
public class JsonRequestBuilder<T> extends BaseRequestBuilder<T> {

    public JsonRequestBuilder() {

    }

    @Override
    protected ApiContext getApiContext() {
        return ApiContextManager.getInstance().getDefaultApiContext();
    }
}
