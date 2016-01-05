package com.capsule.baseframe.http.callback;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.RequestFuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by kakalee on 15/7/29.
 */
public class DataFuture<T> {

    private static final  String TAG = "VOLLEY_ERROR_TAG";

    private Request<T> request;

    private RequestFuture<T> requestFuture;

    public DataFuture() {
        requestFuture = RequestFuture.newFuture();
    }

    public void setRequest(Request<T> request) {
        this.request = request;
        requestFuture.setRequest(request);
    }

    public synchronized boolean cancel(boolean mayInterruptIfRunning) {
        return requestFuture.cancel(mayInterruptIfRunning);
    }

    public RequestFuture<T> getRequestFuture() {
        return requestFuture;
    }

    public T get() {
        T result;
        try {
            result = requestFuture.get();
        } catch (InterruptedException e) {
            Log.d(TAG, "InterruptedException , url is " + request.getUrl());
            return null;
        } catch (ExecutionException e) {
            Log.d(TAG, "ExecutionException , url is " + request.getUrl());
            return null;
        }
        return result;
    }

    public T get(long timeout) {
        T result;
        try {
            result = requestFuture.get(timeout, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            Log.d(TAG, "InterruptedException , url is " + request.getUrl());
            return null;
        } catch (TimeoutException e) {
            Log.d(TAG, "TimeoutException , url is " + request.getUrl());
            return null;
        } catch (ExecutionException e) {
            Log.d(TAG, "ExecutionException , url is " + request.getUrl());
            return null;
        }
        return result;
    }

    public boolean isCancelled() {
        return requestFuture.isCancelled();
    }

    public synchronized boolean isDone() {
        return requestFuture.isDone();
    }
}
