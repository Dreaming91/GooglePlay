package com.HY.googleplay.Http;

/**
 * 定义url常量
 *
 * @author lxj
 */
public interface Url {
    String ServerHost = "http://127.0.0.1:8090/";//内网ip
//    String ServerHost = "http://100.2.1.44:8090/";//外网ip
    String ImagePrefix = ServerHost + "image?name=";//图片前缀


    String Home = ServerHost + "home?index=";//Home页的地址
    String App = ServerHost + "app?index=";
    String Game = ServerHost + "game?index=";
    String Subject = ServerHost + "subject?index=";
    String Recommend = ServerHost + "recommend?index=0";
    String Category = ServerHost + "category?index=0";
    String Hot = ServerHost + "hot?index=0";
    String Detail = ServerHost + "detail?packageName=%s";//%s是占位符，完整写法是%1$s,%2$s,%3$d
    //下载的接口
    String Download = ServerHost + "download?name=%s";//
    //断点下载的地址
    String BreakDownload = ServerHost + "download?name=%s&range=%d";//
}
