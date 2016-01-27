package com.trevisc.android.trevsdemo.data;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.trevisc.android.trevsdemo.R;
import com.trevisc.android.trevsdemo.data.model.Repo;

import java.util.ArrayList;

public class RepoAdapter extends ArrayAdapter<Repo> {


    Context con;
    int res;

    public RepoAdapter(Context context,int resource,ArrayList<Repo> repoData){
        super(context,resource,repoData);
        this.con = context;
        this.res = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Repo repoData = getItem(position);

        if(convertView==null){
            LayoutInflater inflator=LayoutInflater.from(con);
            convertView=inflator.inflate(res,parent,false);
        }

        TextView tvTitle = (TextView)convertView.findViewById(R.id.repolist_name);
        tvTitle.setText(repoData.getDescription());

        TextView tvId = (TextView)convertView.findViewById(R.id.repo_list_url);
        tvId.setText(repoData.getUrl());

        return convertView;
    }
}
