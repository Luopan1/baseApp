package www.test720.a360video;


import com.alibaba.fastjson.JSONObject;
import com.lzy.okgo.model.HttpParams;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import rx.Subscriber;
import rx.schedulers.Schedulers;
import www.test720.a360video.base.BaseToolbarActivity;
import www.test720.a360video.network.Constants;
import www.test720.a360video.uitls.HttpUtils;

public class MainActivity extends BaseToolbarActivity {


    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        HttpUtils httpUtils=new HttpUtils();
        try {
            httpUtils.getData(Constants.httpHost,new HttpParams(),1).observeOn(Schedulers.io()).subscribe(new Subscriber<String>() {
                @Override
                public void onStart() {
                    showLoadingDialog();
                }

                @Override
                public void onCompleted() {
                    cancleLoadingDialog();
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(String s) {

                }
            });
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initBase() {

    }
}
