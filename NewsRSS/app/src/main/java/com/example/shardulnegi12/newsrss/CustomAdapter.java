package com.example.shardulnegi12.newsrss;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import it.sephiroth.android.library.picasso.Picasso;

public class CustomAdapter extends BaseAdapter {

    Context context;
    ArrayList<News> news;
    LayoutInflater inflater;

    public CustomAdapter(Context context, ArrayList<News> news) {
        this.context = context;
        this.news = news;
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return news.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View myView = inflater.inflate(R.layout.custom, null);

        TextView tvTitle = (TextView) myView.findViewById(R.id.tvTitle);
        ImageView imageView = (ImageView)myView.findViewById(R.id.imageView);
        TextView tvDesc = (TextView)myView.findViewById(R.id.tvDescription);
        TextView tvpubDate = (TextView)myView.findViewById(R.id.tvPubDate);

        tvTitle.setText(news.get(position).getTitle());
        tvDesc.setText(news.get(position).getDiscription());
        tvpubDate.setText(news.get(position).getPubdate());

        Picasso.with(context).load(news.get(position).getImage()).into(imageView);

        return myView;
    }
}

