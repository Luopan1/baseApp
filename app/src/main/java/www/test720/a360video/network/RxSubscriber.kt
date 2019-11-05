package www.test720.a360video.network

import android.accounts.NetworkErrorException
import android.app.Activity
import android.os.Handler
import android.os.Message
import android.util.Log
import android.widget.Toast

import com.alibaba.fastjson.JSONException
import com.alibaba.fastjson.JSONObject

import java.net.ConnectException
import java.net.SocketTimeoutException
import java.text.ParseException

import retrofit2.adapter.rxjava.HttpException
import rx.Subscriber
import www.test720.a360video.application.MyApplication

/**
 * Created by lichuanbei on 2016/10/26.
 * 封装Subscriber,使用RxJava+Retrofit封装库
 * 参考博文：http://blog.csdn.net/jdsjlzx/article/details/51882661
 * http://blog.csdn.net/u012551350/article/details/51445357
 */

abstract class RxSubscriber<T> : Subscriber<T>() {
    protected val TAG = this.javaClass.simpleName

    var isShowBaseError = true

    private var isOnCompleted = false
    internal var handler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            if (!isOnCompleted) {//2s后还未获取数据则等待
                val activity = AppManager.appManager.currentActivity
                if (activity != null) {
                    LoadingDialog.showDialog(activity!!)
                }
            }
        }
    }

    /**
     * 复写该方法，不使用super.onStart()视为不使用进度框
     */
    override fun onStart() {
        super.onStart()

        object : Thread() {
            override fun run() {
                super.run()
                try {
                    sleep(0)
                    handler.sendEmptyMessage(0)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

            }
        }.start()

    }

    override fun onCompleted() {
        isOnCompleted = true
        LoadingDialog.closeDialog()
    }

    override fun onError(e: Throwable) {
        e.printStackTrace()
        val ex: ApiException
        if (!NetworkUtil.isConnected(MyApplication.getInstance())) {
            ex = ApiException(e, ApiConstants.NETWORD_ERROR)
            ex.msg = "网络不可用!请检查网络设置"
            onErrorBase(ex)
            return
        }
        if (e is NetworkErrorException) {
            //            NetworkErrorException networkErrorException = (NetworkErrorException) e;
            ex = ApiException(e, ApiConstants.HTTP_ERROR)
            ex.msg = "网络错误!请检查网络是否正常"  //均视为网络错误
            onErrorBase(ex)
            return
        }
        if (e is ConnectException) {
            ex = ApiException(e, ApiConstants.HTTP_ERROR)
            ex.msg = "服务器连接超时!"
            L.i(TAG, e.cause.toString() + "")

            onErrorBase(ex)
            return
        }

        if (e is SocketTimeoutException) {
            ex = ApiException(e, ApiConstants.HTTP_ERROR)
            ex.msg = "服务器连接超时!"
            L.i(TAG, e.cause.toString() + "")

            onErrorBase(ex)
            return
        }


        //        if (e instanceof SocketTimeoutException) {
        //            ConnectException connectException = (ConnectException) e;
        //            ex = new ApiException(e, ApiConstants.HTTP_ERROR);
        //            ex.msg = "服务器连接超时!";
        //            L.i(TAG, connectException.getCause() + "");
        //            onErrorBase(ex);
        //            return;
        //        }


        if (e is HttpException) {
            ex = ApiException(e, ApiConstants.HTTP_ERROR)
            val code = e.code()
            when (code) {
                0, UNAUTHORIZED, FORBIDDEN, NOT_FOUND, REQUEST_TIMEOUT, GATEWAY_TIMEOUT, INTERNAL_SERVER_ERROR, BAD_GATEWAY, SERVICE_UNAVAILABLE -> ex.msg = "网络错误，连接服务器失败"  //均视为网络错误
                else -> ex.msg = "网络错误，连接服务器失败"
            }
            onErrorBase(ex)
        } else if (e is ServerException) {    //服务器返回的错误
            ex = ApiException(e, ApiConstants.PARSE_ERROR)
            ex.msg = e.msg
            onErrorBase(ex)
        } else if (e is JSONException || e is ParseException) {
            ex = ApiException(e, ApiConstants.PARSE_ERROR)
            ex.msg = "数据解析错误，请与我们联系"            //均视为解析错误
            onErrorBase(ex)

        } else {
            ex = ApiException(e, ApiConstants.UNKNOWN)
            ex.msg = ""          //未知错误
            onErrorBase(ex)
        }
    }

    override fun onNext(t: T) {
        Log.e("请求返回原始数据", "=" + t.toString())
        if (t is JSONObject) {
            Log.e("====", " 是JSON")
        }
        _onNext(t)


    }

    fun onErrorBase(ex: ApiException) {
        val code = ex.code
        val msg = ex.msg
        L.i(TAG, "元数据错误：code:$code\nmsg$msg")
        if (isShowBaseError) {
            if (msg != "")
                Toast.makeText(MyApplication.getInstance(), msg, Toast.LENGTH_SHORT).show()
        }
        if (msg != null) {
            _onError(code, msg)
        }
    }

    abstract fun _onNext(t: T)


    fun _onError(code: Int, msg: String) {
        isOnCompleted = true
        LoadingDialog.closeDialog()
    }

    companion object {
        //对应HTTP的状态码
        private val UNAUTHORIZED = 401
        private val FORBIDDEN = 403
        private val NOT_FOUND = 404
        private val REQUEST_TIMEOUT = 408
        private val INTERNAL_SERVER_ERROR = 500
        private val BAD_GATEWAY = 502
        private val SERVICE_UNAVAILABLE = 503
        private val GATEWAY_TIMEOUT = 504
    }
}