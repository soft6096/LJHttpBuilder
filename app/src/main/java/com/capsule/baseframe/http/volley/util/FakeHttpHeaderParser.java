package com.capsule.baseframe.http.volley.util;

import android.text.TextUtils;

import com.android.volley.Cache;
import com.android.volley.NetworkResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 宇宙神帝
 */
public class FakeHttpHeaderParser {

  private static final String FAKE_TIME_KEY = "Fake-Key";

  /**
   * Extracts a {@link Cache.Entry} from a
   * {@link NetworkResponse}.
   * 
   * modify by liuxu34@wanda.cn
   * because of duplicate key (Such as : set-cookie ) we change it from String to List<String>
   *
   * @param response The network response to parse headers from
   * @return a cache entry for the given response, or null if the response is not cacheable.
   */
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


    /**
     * 因为这个是假的 header 所以不能保证我们填入的 System.currentTimeMillis() 和 服务器端返回的 date 一致。
     * 所以如果填入 date 和 etag 。当缓存过期时， etag 将填入 If-None-Match 、 date 将填入 If-Modified-Since。
     * 假设客户端的时间比服务端快得话，则服务端将会一致 304 SC_NOT_MODIFIED 。客户端将继续缓存这份过期的数据，则会
     * 出现一致缓存的情况。
     */
    entry.serverDate = 0;
    entry.etag = null;

    return entry;
  }
}
