package com.capsule.baseframe.http.request;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.capsule.baseframe.config.GlobalConfig;
import com.capsule.baseframe.http.callback.DataCallback;
import com.capsule.baseframe.http.callback.DataFuture;
import com.capsule.baseframe.http.volley.ApiContext;
import com.capsule.baseframe.http.volley.ApiRequest;
import com.capsule.baseframe.http.volley.Parser;
import com.capsule.baseframe.log.Log;
import com.capsule.baseframe.util.MainThreadPostUtils;

/**
 * Created by kakalee on 15/7/30.
 */
public abstract class BaseRequestBuilder<T> implements VolleyRequestBuilder<T> {

    private static final String TAG = "VOLLEY_ERROR_TAG";
    private int mMethod = Request.Method.GET;
    private DataCallback<T> dataCallback;

    private boolean needCache = false;
    private long cacheTime = 0;
    private boolean needInvalidateCache = false;

    private String url;
    private Parser<T> parser;

    private Params params;

    private Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(final VolleyError error) {
            if (GlobalConfig.isDebug() && error != null && error.networkResponse != null) {
                Log.d(TAG, "error status code is " + error.networkResponse.statusCode
                        + ", url is " + url + " , error message is " + error.getMessage());
            }
            MainThreadPostUtils.toast(R.string.network_error);
            if (dataCallback != null) {
                MainThreadPostUtils.post(new Runnable() {
                    @Override
                    public void run() {
                        dataCallback.onErrorResponse(error);
                    }
                });
            }
        }
    };

    private Response.Listener<T> responseListener = new Response.Listener<T>() {
        @Override
        public void onResponse(final T response) {
            if (dataCallback != null) {
                MainThreadPostUtils.post(new Runnable() {
                    @Override
                    public void run() {
                        dataCallback.onResponse(response);
                    }
                });
            }
        }
    };

    protected BaseRequestBuilder() {}


    public BaseRequestBuilder setMethod(int method) {
        this.mMethod = method;
        return this;
    }

    public BaseRequestBuilder setDataCallback(DataCallback<T> dataCallback) {
        this.dataCallback = dataCallback;
        return this;
    }

    public BaseRequestBuilder setNeedCache(boolean needCache) {
        this.needCache = needCache;
        return this;
    }

    public BaseRequestBuilder setCacheTime(long cacheTime) {
        this.cacheTime = cacheTime;
        return this;
    }

    public BaseRequestBuilder setInvalidateCache(boolean needInvalidateCache) {
        this.needInvalidateCache = needInvalidateCache;
        return this;
    }

    public BaseRequestBuilder setUrl(String url) {
        this.url = url;
        return this;
    }

    public BaseRequestBuilder setParser(Parser<T> parser) {
        this.parser = parser;
        return this;
    }

    public BaseRequestBuilder setParams(Params params) {
        this.params = params;
        return this;
    }

    protected abstract ApiContext getApiContext();

    @Override
    public ApiRequest<T> build() {
        ApiRequest<T> request;

        if(params == null) {
            params = new Params();
        }

        if (dataCallback != null) {
            request = new ApiRequest<T>(mMethod, url, params.getMap(), getApiContext(),
                    parser, responseListener, errorListener);
        } else {
            DataFuture<T> dataFuture = new DataFuture<>();
            request = new ApiRequest<T>(mMethod, url, params.getMap(), getApiContext(),
                    parser, dataFuture);
        }

        /**
         * 设置是否需要缓存。默认为不使用缓存
         */
        request.setShouldCache(needCache);

        /**
         * 当设置了自定义 cacheTime 时设置，否则会使用 apiContext 中配置的默认缓存时间。
         * 注意：只有底层使用了 Fake Cache Key 才会有效
         */
        if (cacheTime != 0) {
            request.setCacheTime(cacheTime);
        }

        /**
         * 设置是否需要清理 当前 url 的缓存.
         * 一般用于强制刷新使用
         */
        if (needInvalidateCache) {
            request.invalidateCache();
        }

        return request;
    }
}
