package com.capsule.baseframe.http.volley.util;

import android.annotation.SuppressLint;
import android.net.http.AndroidHttpClient;
import android.os.Build;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HttpClientStack;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.HurlStack;
import com.capsule.baseframe.http.volley.HttpClientWrapper;

import java.io.File;

/**
 * @author 宇宙神帝
 */
public class VolleyUtils {

  @SuppressLint("NewApi")
  public static RequestQueue newRequestQueue(File cacheDir, String userAgent,
      int cacheSize, int threadPoolSize) {
    // init file cache
    Cache diskCache = new DiskBasedCache(cacheDir, cacheSize);
    // init network
    HttpStack stack;
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
      stack = new HurlStack();
    } else {
      // Prior to Gingerbread, HttpUrlConnection was unreliable.
      // See: http://android-developers.blogspot.com/2011/09/androids-http-clients.html
      stack = new HttpClientStack(
          HttpClientWrapper.newInstance(AndroidHttpClient.newInstance(userAgent)));
    }
    Network network = new BasicNetwork(stack);
    // build RequestQueue and start it
    RequestQueue queue = new RequestQueue(diskCache, network, threadPoolSize);
    queue.start();
    return queue;
  }
}
