package www.test720.a360video.network

import android.app.Activity

import java.lang.ref.WeakReference

/**
 * Created by jie on 2016/12/28.
 */

class AppManager private constructor() {

    private var sCurrentActivityWeakRef: WeakReference<Activity>? = null

    var currentActivity: Activity? get() {
            var currentActivity: Activity? = null
            if (sCurrentActivityWeakRef != null) {
                currentActivity = sCurrentActivityWeakRef!!.get()
            }
            return currentActivity
        }

        set(activity) {
            sCurrentActivityWeakRef = WeakReference<Activity>(activity)
        }




    companion object {
        val appManager by lazy {
            AppManager()
        }
    }

}
