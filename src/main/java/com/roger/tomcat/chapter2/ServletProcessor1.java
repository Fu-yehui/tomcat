package com.roger.tomcat.chapter2;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 处理对servlet资源的HTTP请求
 */
public class ServletProcessor1 {

    public void process(Request request,Response response){
        String uri=request.getUri();
        String servletName=uri.substring(uri.lastIndexOf("/")+1);
        URLClassLoader loader=null;
        try{
            URL[] urls=new URL[1];
            URLStreamHandler streamHandler=null;
            Path path= Paths.get(Constants.WEB_ROOT.toString());
            String repository=(new URL("file",null,path.toFile().getCanonicalPath()+ File.separator)).toString();
            urls[0]=new URL(null,repository,streamHandler);
            loader=new URLClassLoader(urls);

        } catch (IOException e) {
            e.printStackTrace();
        }
        Class myClass=null;
        try{
            myClass=loader.loadClass(servletName);
        } catch (ClassNotFoundException e) {
            System.out.println(e.toString());
        }

        Servlet servlet=null;
        try{
            servlet=(Servlet)myClass.newInstance();
            servlet.service((ServletRequest)request,(ServletResponse)response);
        } catch (IllegalAccessException e) {
            System.out.println(e.toString());

        } catch (InstantiationException e) {
            System.out.println(e.toString());

        } catch (ServletException e) {
            System.out.println(e.toString());

        } catch (IOException e) {
            System.out.println(e.toString());

        }
    }
}
