package com.example.administrator.blackfloat;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/3.
 */

public class MyAdapet extends BaseAdapter {
    Context context;
    List<PackageInfo> list;
    PackageManager packageManager;
    List<Integer> select;
    MyAdapet(Context context, List<PackageInfo> list){
        this.context = context;
        this.list = list;
        packageManager = context.getPackageManager();
        select = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return list.size();
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View mview = inflater.inflate(R.layout.appslist,viewGroup,true);
        final TextView textView = mview.findViewById(R.id.list_text);
        textView.setText(packageManager.getApplicationLabel(list.get(i).applicationInfo).toString());

        ImageView image = mview.findViewById(R.id.list_img);
        image.setImageDrawable(packageManager.getApplicationIcon(list.get(i).applicationInfo));

        LinearLayout layout = mview.findViewById(R.id.list_layout);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (select.contains(i))
                {
                    //已经点击过了
                    select.remove((Object)i);
                    textView.setBackgroundColor(0x00ffffff);
                }else
                {
                    //没点击过
                    select.add(i);
                    textView.setBackgroundColor(0x00122543);
                }
            }
        });
        return mview;
    }

    public List<Integer> getSelect() {
        return select;
    }
}
