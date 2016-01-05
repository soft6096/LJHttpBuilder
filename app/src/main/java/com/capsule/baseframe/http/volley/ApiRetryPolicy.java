package com.capsule.baseframe.http.volley;

import com.android.volley.DefaultRetryPolicy;

/**
 * @author 宇宙神帝
 */
public class ApiRetryPolicy extends DefaultRetryPolicy {

  /**
   * wanda timeout 30s
   * 15s connect timeout , 15s transfer timeout
   */
  public static final int DEFAULT_TIMEOUT_MS = 15000;

  /** The default number of retries */
  public static final int DEFAULT_MAX_RETRIES = 1;

  /** The default backoff multiplier */
  public static final float DEFAULT_BACKOFF_MULT = 1f;

  public ApiRetryPolicy(int initialTimeoutMs, int maxNumRetries, float backoffMultiplier) {
    super(initialTimeoutMs, maxNumRetries, backoffMultiplier);
  }

  public ApiRetryPolicy() {
    super(DEFAULT_TIMEOUT_MS, DEFAULT_MAX_RETRIES, DEFAULT_BACKOFF_MULT);
  }
}
