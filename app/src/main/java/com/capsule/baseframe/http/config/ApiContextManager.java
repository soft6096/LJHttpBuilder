package com.capsule.baseframe.http.config;

import com.capsule.baseframe.config.GlobalConfig;

/**
 * Created by kakalee on 15/7/30.
 */
public class ApiContextManager {

    private static ApiContextManager sInstance;

    private DefaultApiContext defaultApiContext;

    private ApiContextManager() {}

    public static ApiContextManager getInstance() {
        if(sInstance == null) {
            synchronized (ApiContextManager.class) {
                if(sInstance == null) {
                    sInstance = new ApiContextManager();
                }
            }
        }

        return sInstance;
    }

    public DefaultApiContext getDefaultApiContext() {
        if (defaultApiContext == null) {
            defaultApiContext = new DefaultApiContext(GlobalConfig.getAppContext());
        }
        return defaultApiContext;
    }
}
