package com.trevisc.android.trevsdemo.data.model;

import com.trevisc.android.trevsdemo.data.helper.GitHelper;

import java.io.Serializable;
import java.util.List;

public class Repo implements Serializable {
    private long Id;
    private String url;
    private String description;

    public long getId(){
        return Id;
    }
    public void setId(long Id){
        this.Id = Id;
    }
    public  String getUrl(){
        return url;
    }
    public void setUrl(String url){
        this.url = url;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }

}