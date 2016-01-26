package com.trevisc.android.trevsdemo.data.helper;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.trevisc.android.trevsdemo.R;
import com.trevisc.android.trevsdemo.data.model.Repo;

public class GitHelper{

    public static List<Repo> getGitRepos(String uri,String accountName) {
        String url = getGitUrl(uri,accountName);
        List<Repo> repos = getGitReposList(uri);
        return repos;
    }

    private static String getGitUrl(String url,String accountName){

        if (accountName != null) {
            if(accountName.length()==0){
                accountName ="trevisc";
            }
            url += accountName + "/repos";
        }
        return url;
    }
    private static List<Repo> getGitReposList(String url){
        List<Repo> repos = new ArrayList<>();
        String json = getGitReposJSON(url);
        return repos;
    }

    private static String getGitReposJSON(String url) {
        HttpURLConnection httpConn = null;
        try {
            URL uri = new URL(url);
            httpConn = (HttpURLConnection) uri.openConnection();
            httpConn.connect();
            int status = httpConn.getResponseCode();
            switch (status) {
                case 200:
                case 201:
                    BufferedReader br = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line+"\n");
                    }
                    br.close();
                    return sb.toString();
            }

        } catch (Exception ex) {
            return ex.toString();
        } finally {
            if (httpConn != null) {
                try {
                    httpConn.disconnect();
                } catch (Exception ex) {
                    //disconnect error
                }
            }
        }
        return null;
    }

}