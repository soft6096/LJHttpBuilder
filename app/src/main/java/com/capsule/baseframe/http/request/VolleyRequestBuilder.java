package com.capsule.baseframe.http.request;

import com.capsule.baseframe.http.volley.ApiRequest;

/**
 * Created by kakalee on 15/7/30.
 */
public interface VolleyRequestBuilder<T> {


  /**
   * Build a volley request
   * 
   * @return ApiRequest. Returns ApiRequest never be null
   */
  ApiRequest<T> build();

}
