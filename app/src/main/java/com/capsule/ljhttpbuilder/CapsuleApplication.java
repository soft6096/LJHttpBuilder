package com.capsule.ljhttpbuilder;

import android.app.Application;

import com.capsule.baseframe.config.GlobalConfig;

/**
 * Created by kakalee on 15/7/31.
 */
public class CapsuleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        GlobalConfig.setAppContext(this);
        GlobalConfig.setDebug(BuildConfig.DEBUG);
        GlobalConfig.setAppRootDir(GlobalConfig.ROOT_PATH);
    }
}
