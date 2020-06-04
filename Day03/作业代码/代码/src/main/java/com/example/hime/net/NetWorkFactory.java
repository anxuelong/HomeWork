package com.example.hime.net;

public class NetWorkFactory {
    //    制造产品
//    RetrfitUtils   、    HttpUrlConnettionUtils
    //    如果设置1  表示使用retrofit请求方式
//    如果设置其他   表示用httpurl请求方式
    private static int NET_TYPE = 1;
    public static volatile NetWorkFactory netWorkFactory;
    public INetWork netWork;

    public static NetWorkFactory getInstance() {
        if (netWorkFactory == null){
            synchronized (NetWorkFactory.class){
                if (netWorkFactory == null){
                    netWorkFactory = new NetWorkFactory();
                }
            }
        }
        return netWorkFactory;
    }

    public INetWork getNetWork(){
        if (NET_TYPE == 1){
            netWork = com.example.instantmessaging.net.RetrofitUtils.getInstance();
        }else {
            netWork = HttpUrlConnectionUtils.getInstance();
        }
        return netWork;
    }
}
