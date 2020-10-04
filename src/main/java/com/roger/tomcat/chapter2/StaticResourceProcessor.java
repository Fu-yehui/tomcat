package com.roger.tomcat.chapter2;

import java.io.IOException;

/**
 * 处理对静态资源的请求
 */
public class StaticResourceProcessor {

    public void process(Request request,Response response){
        try{
            response.sendStaticResource();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
