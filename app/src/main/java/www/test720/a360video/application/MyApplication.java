package www.test720.a360video.application;

import android.app.Application;

/**
 * @author LuoPan on 2018/3/8 11:19.
 */

public class MyApplication extends Application {
    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
    }


    public static MyApplication getInstance() {
        return instance;
    }
}
