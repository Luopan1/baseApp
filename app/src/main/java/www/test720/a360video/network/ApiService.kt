package www.test720.a360video.network


import com.alibaba.fastjson.JSONObject

import retrofit2.http.GET
import retrofit2.http.Url
import rx.Observable

/**
 * Created by jie on 2016/12/8.
 */

interface ApiService {

    /**
     * 首页
     *
     * @param
     * @return
     */

    @GET("/wxarticle/chapters/json")
    fun getVideo(): Observable<JSONObject>


}
