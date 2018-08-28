package com.tix.net;

import android.text.TextUtils;

import com.tix.net.api.ApiService;
import com.tix.net.func.ApiResultFunc;
import com.tix.net.utils.RxUtil;

import java.lang.reflect.Type;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Retrofit 客户端管理工具
 * 作者：created by ztcao on 2018/8/8 15 : 55
 */
public class RetrofitManager {

    private static  RetrofitManager retrofitManager ;
    private static String baseUrl ; //URL
    private OkHttpClient.Builder okHttpClientBuilder;                 //okhttp请求的客户端
    private Retrofit.Builder     retrofitBuilder;                      //Retrofit请求Builder
    private OkHttpClient okHttpClient ;
    private Retrofit retrofit ;
    private ApiService apiManager ;

    private RetrofitManager(){
        if(TextUtils.isEmpty(baseUrl)){
            throw new IllegalArgumentException("请首先调用init方法") ;
        }
        okHttpClientBuilder = new OkHttpClient.Builder();
        okHttpClient = okHttpClientBuilder.build();
        retrofitBuilder = generateRetrofit(baseUrl) ;
        retrofitBuilder.client(okHttpClient);
        retrofitBuilder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());//增加RxJava2CallAdapterFactory
        retrofit = retrofitBuilder.build() ;
        apiManager = retrofit.create(ApiService.class);
    }

    //可以在Application创建时调用
    public static final void  init(String appBaseUrl){
        baseUrl = appBaseUrl ;
    }

    public static final RetrofitManager getInstance(){
        synchronized (RetrofitManager.class){
            if(retrofitManager == null){
                retrofitManager = new RetrofitManager();
            }
            return retrofitManager ;
        }
    }


    /**
     * 根据当前的请求参数，生成对应的Retrofit
     */
    private Retrofit.Builder generateRetrofit(String baseUrl) {
            final Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
            return retrofitBuilder.baseUrl(baseUrl);
    }

    public   <T> Observable<T> post(String url , Map<String, String> params , Type dataType){
        Observable<ResponseBody> observable =  apiManager.post("nologin/login" ,  params );
        return observable.map(new ApiResultFunc(dataType))
                .compose( RxUtil._io_main()) ;
    }
}
