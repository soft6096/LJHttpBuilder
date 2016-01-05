package com.capsule.baseframe.http.volley.util;

import android.text.TextUtils;

import com.android.volley.Cache;
import com.android.volley.NetworkResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kakalee
 */
public class FakeHttpHeaderParser {

  private static final String FAKE_TIME_KEY = "Fake-Key";

  public static Cache.Entry parseCacheHeaders(NetworkResponse response, long cacheTime) {
    long now = System.currentTimeMillis();

    Cache.Entry entry = new Cache.Entry();
    Map<String, String> headers = response.headers;
    if (headers == null) {
      headers = new HashMap<>();
    }

    String fakeValue = headers.get(FAKE_TIME_KEY);

    if (TextUtils.isEmpty(fakeValue)) {
      headers.put(FAKE_TIME_KEY, String.valueOf(now));
    }

    entry.softTtl = now + cacheTime;
    entry.ttl = entry.softTtl;

    entry.data = response.data;
    entry.responseHeaders = headers;

    entry.serverDate = 0;
    entry.etag = null;

    return entry;
  }
}
