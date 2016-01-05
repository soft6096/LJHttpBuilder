package com.capsule.baseframe.http.volley;

import android.net.Uri;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.capsule.baseframe.http.callback.DataFuture;
import com.capsule.baseframe.http.config.ApiConfig;
import com.capsule.baseframe.http.volley.util.FakeHttpHeaderParser;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kakalee on 15/7/29.
 */
public class ApiRequest<T> extends Request<T>{

    private ApiContext apiContext;

    private Response.Listener<T> listener;

    private Map<String, String> params;
    private Map<String, String> extraHeaders;
    private DataFuture<T> mDataFuture;

    private Parser<T> parser;

    private long fakeCacheTime = 0;

    public ApiRequest(int method, String url,
                      Map<String, String> params,
                      ApiContext apiContext,
                      Parser parser,
                      Response.Listener<T> listener,
                      Response.ErrorListener errorListener) {
        super(method, buildUrl(url, params, method), errorListener);
        this.params = params;
        this.apiContext = apiContext;
        this.parser = parser;
        this.listener = listener;

        setShouldCache(!ApiConfig.skipAllCaches());
        setRetryPolicy(new ApiRetryPolicy());
        fakeCacheTime = apiContext.getCacheTime();
    }


    public ApiRequest(int method, String url,
                      Map<String, String> params,
                      ApiContext apiContext,
                      Parser parser,
                      DataFuture<T> dataFuture) {
        this(method, url, params, apiContext, parser, dataFuture.getRequestFuture(), dataFuture.getRequestFuture());
        this.mDataFuture = dataFuture;
        dataFuture.setRequest(this);
    }

    private static String buildUrl(String url, Map<String, String> params, int method) {
        if (params == null || params.isEmpty() || method != Method.GET) {
            return url;
        }
        Uri.Builder uriBuilder = Uri.parse(url).buildUpon();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            uriBuilder.appendQueryParameter(entry.getKey(), entry.getValue());
        }
        return uriBuilder.toString();
    }

    public final void submit() {
        apiContext.getRequestQueue().add(this);
    }

    public final DataFuture<T> submitSync() {
        apiContext.getRequestQueue().add(this);
        if (mDataFuture != null) {
            mDataFuture.setRequest(this);
            return mDataFuture;
        }
        return null;
    }

    public void setCacheTime(long cacheTime) {
        fakeCacheTime = cacheTime;
    }

    public final void invalidateCache() {
        if (apiContext.getRequestQueue().getCache() != null) {
            apiContext.getRequestQueue().getCache().remove(getCacheKey());
        }
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            T result = parser.parse(response);
            if (result == null) {
                return null;
            }
            Log.i("Log","request url:" + getUrl() + " ;respone string:" + result.toString());
            return Response.success(result, parseCache(response));
        } catch (Throwable e) {
            e.printStackTrace();
            return Response.error(new ParseError(response));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        if (listener == null) {
            return;
        }
        else {
            listener.onResponse(response);
        }
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = new HashMap<>();
        Map<String, String> contextHeaders = apiContext.getHeaders();
        if (contextHeaders != null) {
            headers.putAll(contextHeaders);
        }
        if (extraHeaders != null) {
            headers.putAll(extraHeaders);
        }
        return headers;
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

    @Override
    public String getCacheKey() {
        String postfix = apiContext.getCacheKeyPostfix();
        return super.getCacheKey() + (postfix == null ? "" : postfix);
    }

    /**
     * Parse cache from response.
     * The default strategy use volley's <b>HttpHeaderParser</b>, sub-class enable override it.
     *
     * @param response
     * @return
     */
    protected Cache.Entry parseCache(NetworkResponse response) {
        /**
         * FakeHttpHeaderParser 解决服务端返回 header 不规范的问题
         */
        return FakeHttpHeaderParser.parseCacheHeaders(response, fakeCacheTime);
    }
}
