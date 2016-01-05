package com.capsule.baseframe.http.volley;

import com.android.volley.NetworkResponse;

import java.io.IOException;

/**
 * Created by kakalee on 15/7/29.
 */
public interface Parser<T> {

    T parse(NetworkResponse response) throws IOException;
}
