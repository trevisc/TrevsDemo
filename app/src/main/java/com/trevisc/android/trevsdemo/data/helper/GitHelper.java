package com.trevisc.android.trevsdemo.data.helper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GitHelper{

       public static String getGitReposJSON(String url) {
        HttpURLConnection httpConn = null;
        try {
            URL uri = new URL(url);
            httpConn = (HttpURLConnection) uri.openConnection();
            httpConn.setRequestMethod("GET");
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