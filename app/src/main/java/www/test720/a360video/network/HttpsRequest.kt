package www.test720.a360video.network


import java.util.concurrent.TimeUnit

import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLSession

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.fastjson.FastJsonConverterFactory
import www.test720.a360video.application.MyApplication

/**
 * Created by lichuanbei on 2016/10/17.
 *
 * 网络请求框架封装--封装Https,并设置网络请求时公共参数
 */

class HttpsRequest private constructor() {


    /**
     * okhttpClinet通用设置为https访问-使用拦截器设置请求头
     * @return
     */
    protected//        //设置https
    //        SSLSocketFactory sslSocketFactory = new SslContextFactory().getSslSocket().getSocketFactory();
    //        okHttpClient.sslSocketFactory(sslSocketFactory);
    //设置超时
    //
    //错误重连
    //设置是否打印Log//打印请求log日志//根据是否debug模式决定
    val okHttpClinet: OkHttpClient
        get() {
            val okHttpClient = OkHttpClient.Builder()
            okHttpClient.cookieJar(CookieManger(MyApplication.getInstance()))
            okHttpClient.connectTimeout(15, TimeUnit.SECONDS)
            okHttpClient.readTimeout(15, TimeUnit.SECONDS)
            okHttpClient.writeTimeout(15, TimeUnit.SECONDS)
            okHttpClient.retryOnConnectionFailure(true)
            if (L.DEBUG_SYSOUT) {
                val loggingInterceptor = HttpLoggingInterceptor()
                loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                okHttpClient.addInterceptor(loggingInterceptor)
            }
            okHttpClient.hostnameVerifier { hostname, session -> true }
            return okHttpClient.build()
        }


    /**
     * okhttpClinet通用设置为https访问-使用拦截器设置请求头
     * @return
     */
    protected fun getOkHttpClinet(seconds: Long): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.cookieJar(CookieManger(MyApplication.getInstance()))
        //        //设置https
        //        SSLSocketFactory sslSocketFactory = new SslContextFactory().getSslSocket().getSocketFactory();
        //        okHttpClient.sslSocketFactory(sslSocketFactory);
        //设置超时
        okHttpClient.connectTimeout(seconds, TimeUnit.SECONDS)//
        okHttpClient.readTimeout(seconds, TimeUnit.SECONDS)
        okHttpClient.writeTimeout(seconds, TimeUnit.SECONDS)
        //错误重连
        okHttpClient.retryOnConnectionFailure(true)
        //设置是否打印Log//打印请求log日志//根据是否debug模式决定
        if (L.DEBUG_SYSOUT) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            okHttpClient.addInterceptor(loggingInterceptor)
        }
        okHttpClient.hostnameVerifier { hostname, session -> true }
        return okHttpClient.build()
    }

    companion object {
        @Volatile
        private var instance: HttpsRequest? = null

        /**
         * 该类单例模式，获取类实例
         * @return
         */
        private fun getInstance(): HttpsRequest? {
            if (null == instance) {
                synchronized(HttpsRequest::class.java) {
                    if (null == instance) {
                        instance = HttpsRequest()
                    }
                }
            }
            return instance
        }

        /**
         * 封装后的Retrofit,每次请求时调用HttpsRequest.provideClientApi()即可获取Retrofit实例
         * @return
         */
        fun provideClientApi(): ApiService {
            val retrofit = Retrofit.Builder()
                    .baseUrl(Constants.httpHost)
                    .addConverterFactory(FastJsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(HttpsRequest.getInstance()!!.okHttpClinet)
                    .build()
            return retrofit.create(ApiService::class.java)
        }

        /**
         * 封装后的Retrofit,每次请求时调用HttpsRequest.provideClientApi()即可获取Retrofit实例
         * @return
         */
        fun provideClientApi(seconds: Long): ApiService {
            val retrofit = Retrofit.Builder()
                    .baseUrl(Constants.httpHost)
                    .addConverterFactory(FastJsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(HttpsRequest.getInstance()!!.getOkHttpClinet(seconds))
                    .build()
            return retrofit.create(ApiService::class.java)
        }

        fun CarprovideClientApi(): ApiService {
            val retrofit = Retrofit.Builder()
                    .baseUrl("http://v.juhe.cn/usedcar/")
                    .addConverterFactory(FastJsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(HttpsRequest.getInstance()!!.okHttpClinet)
                    .build()
            return retrofit.create(ApiService::class.java)
        }

        fun CarprovideClientNewApi(): ApiService {
            val retrofit = Retrofit.Builder()
                    .baseUrl("https://api-cn.faceplusplus.com/cardpp/v1/")
                    .addConverterFactory(FastJsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(HttpsRequest.getInstance()!!.okHttpClinet)
                    .build()
            return retrofit.create(ApiService::class.java)
        }
    }

}
