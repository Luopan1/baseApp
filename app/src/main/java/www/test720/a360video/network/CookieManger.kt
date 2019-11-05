package www.test720.a360video.network

import android.content.Context
import android.util.Log

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import www.test720.a360video.network.CookieManger.Companion.cookieStore

/**
 * Created by jie on 2017/3/15.
 */

class CookieManger(context: Context) : CookieJar {

    init {
        if (cookieStore == null) {
            cookieStore = PersistentCookieStore(context)
        }

    }


    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        if (cookies != null && cookies.size > 0) {
            for (item in cookies) {
                Log.e("======", "====$item")
                cookieStore!!.add(url, item)
            }
        }
    }

    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        return cookieStore!![url]
    }

    companion object {

        var cookieStore: PersistentCookieStore? = null
    }

}
