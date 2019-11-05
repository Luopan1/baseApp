package www.test720.a360video.application;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import www.test720.a360video.network.AppManager;

/**
 * @author LuoPan on 2018/3/8 11:19.
 */

public class MyApplication extends Application {
    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        ActivitStats();
    }


    public static MyApplication getInstance() {
        return instance;
    }

    //监听当前Activit状态
    private void ActivitStats() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {
               AppManager.Companion.getAppManager().setCurrentActivity(activity);
            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }
}
