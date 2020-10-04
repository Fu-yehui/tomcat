package com.roger.tomcat.chapter2;

import java.io.File;

public enum Constants {
    /**
     * 静态资源位于该目录下
     */
    WEB_ROOT(System.getProperty("user.dir")+ File.separator+"webroot");

    private Constants(String desc){
        this.description=desc;
    }
    private String description;

    public String getDescription(){
        return description;
    }

    @Override
    public String toString(){
        return description;
    }
}
