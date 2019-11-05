package www.test720.a360video


import android.util.Log
import com.alibaba.fastjson.JSONObject
import com.lzy.okgo.model.HttpParams

import java.io.UnsupportedEncodingException
import java.net.URLEncoder

import rx.Subscriber
import rx.Subscription
import rx.schedulers.Schedulers
import www.test720.a360video.base.BaseToolbarActivity
import www.test720.a360video.network.Constants
import www.test720.a360video.network.RxSchedulersHelper
import www.test720.a360video.network.RxSubscriber
import www.test720.a360video.uitls.HttpUtils

class MainActivity : BaseToolbarActivity() {


    override fun getContentView(): Int {
        return R.layout.activity_main
    }

    override fun initData() {
        apiService.getVideo().compose(RxSchedulersHelper.io_main()).subscribe(object : RxSubscriber<JSONObject>() {
            override fun _onNext(t: JSONObject) {
                Log.e("1111", t.toString())
            }

            override fun onStart() {
                super.onStart()
            }

        })
    }

    override fun setListener() {

    }

    override fun initBase() {

    }
}
