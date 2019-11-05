package www.test720.a360video.network

import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by lichuanbei on 2016/10/26.
 * 处理Rx线程:参看博文：http://www.jianshu.com/p/f3f0eccbcd6f
 */

object RxSchedulersHelper {

    fun <T> io_main(): Observable.Transformer<T, T> {
        return Observable.Transformer { tObservable ->
            tObservable
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }
}
