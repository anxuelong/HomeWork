package com.example.instantmessaging.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.example.hime.app.MyApplication;
import com.example.hime.net.INetCallBack;
import com.example.hime.net.INetWork;
import com.example.hime.net.LogUtils;
import com.example.hime.net.api.URLConstants;
import com.example.instantmessaging.net.api.NetApi;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * 网络请求工具类  retrofit请求方式
 */
public class RetrofitUtils implements INetWork {

    public static  volatile  RetrofitUtils retrofitUtils;
    private final Retrofit retrofit;
    private final OkHttpClient okHttpClient;
    private final NetApi netApi;

    private RetrofitUtils() {

        File cacheFile = new File(MyApplication.myApplication.getCacheDir(),"cache");
        Cache cache = new Cache(cacheFile,1024*1024*10);

        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(addNetHeaderInterceptor())
//                .addInterceptor(addCacheInterceptor())
//                .cache(cache)
                .connectTimeout(10000, TimeUnit.MILLISECONDS)
                .readTimeout(10000,TimeUnit.MILLISECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(URLConstants.BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        netApi = retrofit.create(NetApi.class);
    }

    public static RetrofitUtils getInstance() {
        if(retrofitUtils == null){
            synchronized (RetrofitUtils.class){
                if (retrofitUtils == null){
                    retrofitUtils = new RetrofitUtils();
                }
            }
        }
        return retrofitUtils;
    }


    @Override
    public <T> void get(String url, final INetCallBack<T> netCallBack) {

        Log.e("TAG","网络请求执行了");

        netApi.get(url)
//
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {

                        try {
                            String body = responseBody.string();

                            Log.e("TAG","网络请求GET方法打印："+body);

                            Type[] genericInterfaces = netCallBack.getClass().getGenericInterfaces();
                            Type[] actualTypeArguments = ((ParameterizedType) genericInterfaces[0]).getActualTypeArguments();

                            Type type =  actualTypeArguments[0];

                            Gson gson = new Gson();
                            T t = gson.fromJson(body, type);
                            netCallBack.onSuccess(t);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


        //        做网络请求    重要吗？

        //将请求到的结果通过咱们的   INetCallBack   返回的


    }

    @Override
    public <T> void get(String url, HashMap<String, String> s,final INetCallBack<T> netCallBack) {

//        rxjava


        Log.e("TAG","网络请求执行了");

        netApi.get(url,s)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {

                        try {
                            String body = responseBody.string();

                            LogUtils.e("TAG","网络请求POST方法打印："+body);

                            Type[] genericInterfaces = netCallBack.getClass().getGenericInterfaces();
                            Type[] actualTypeArguments = ((ParameterizedType) genericInterfaces[0]).getActualTypeArguments();

                            Type type =  actualTypeArguments[0];

                            Gson gson = new Gson();
                            T t = gson.fromJson(body, type);
                            netCallBack.onSuccess(t);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e("TAG","请问错误="+e.getMessage());

                    }

                    @Override
                    public void onComplete() {

                    }
                });



    }

    @Override
    public <T> void post(String url, final INetCallBack<T> netCallBack) {

//        rxjava


        Log.e("TAG","网络请求执行了");

        netApi.post(url)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {

                        try {
                            String body = responseBody.string();

                            LogUtils.e("TAG","网络请求GET方法打印："+body);

                            Type[] genericInterfaces = netCallBack.getClass().getGenericInterfaces();
                            Type[] actualTypeArguments = ((ParameterizedType) genericInterfaces[0]).getActualTypeArguments();

                            Type type =  actualTypeArguments[0];

                            Gson gson = new Gson();
                            T t = gson.fromJson(body, type);
                            netCallBack.onSuccess(t);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e("TAG","请问错误="+e.getMessage());

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public <T> void post(String url, HashMap<String, String> s, final INetCallBack<T> netCallBack) {

//        rxjava


        Log.e("TAG","网络请求执行了");

        netApi.post(url,s)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {

                        try {
                            String body = responseBody.string();

                            LogUtils.e("TAG","网络请求POST方法打印："+body);

                            Type[] genericInterfaces = netCallBack.getClass().getGenericInterfaces();
                            Type[] actualTypeArguments = ((ParameterizedType) genericInterfaces[0]).getActualTypeArguments();

                            Type type =  actualTypeArguments[0];

                            Gson gson = new Gson();
                            T t = gson.fromJson(body, type);
                            netCallBack.onSuccess(t);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e("TAG","请问错误="+e.getMessage());

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


//    网络请求


    //应用拦截器、网络拦截器
    private Interceptor addNetHeaderInterceptor(){
        Interceptor headerInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                Request request = chain.request();
                Request.Builder requestbuilder = request.newBuilder();
//                        .header("Cache-control", "no-cache")
//                        .header("sigan","*********");
                Request build = requestbuilder.build();
                return chain.proceed(build);
            }
        };
        return headerInterceptor;
    }


    /**
     * 两种形式--服务器支持缓存，设置标志就好了，如果不支持，就修改相应头。让服务器支持缓存
     *
     * 什么情况下使用缓存？  --没网络的时候，网络请求进行缓存
     *
     * @return
     */
    private  Interceptor addCacheInterceptor(){

        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if(isNetWorkValid()){
                    request =  request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response response = chain.proceed(request);
                if(isNetWorkValid()){
//                    有网络的情况下，咱们就不使用缓存
                    response.newBuilder()
                            .header("Cache-Control","public,max-age="+0)
//                            清楚头信息，如果服务器不知粗--看网络请求返回结果
                            .removeHeader("")
                            .build();
                }else {
                    int max_stale = 60*60;
                    response.newBuilder()
                            .header("Cache-Control","public,only-if-cached,max-stale="+max_stale)
                            .removeHeader("")
                            .build();
                }
                return response;
            }
        };

        return cacheInterceptor;
    }




    private  boolean isNetWorkValid(){

        ConnectivityManager manager = (ConnectivityManager) MyApplication.myApplication.getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if(null == manager){
            return false;
        }
        NetworkInfo info = manager.getActiveNetworkInfo();
        if(null == info || !info.isAvailable()){
            return false;
        }
        return true;
    }


}
