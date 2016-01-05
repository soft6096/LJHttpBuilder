package com.capsule.baseframe.http.callback;

import com.android.volley.VolleyError;

/**
 * Created by kakalee on 15/7/29.
 */
public interface DataCallback<T> {

    void onResponse(T data);

    void onErrorResponse(VolleyError error);
}
