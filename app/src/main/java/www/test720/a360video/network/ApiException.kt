package www.test720.a360video.network

/**
 * Created by lichuanbei on 2016/10/26.
 *
 * 自定义异常类：参考博文：http://blog.csdn.net/jdsjlzx/article/details/51882661
 */

class ApiException(throwable: Throwable, var code: Int) : Exception(throwable) {
    var msg: String? = null
}