package com.example.administrator.blackfloat;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
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
    private final SharedPreferences sp;
    private final SharedPreferences.Editor editor;

    MyAdapet(Context context, List<PackageInfo> list){
        this.context = context;
        this.list = list;
        packageManager = context.getPackageManager();
        sp = context.getSharedPreferences("app", Context.MODE_PRIVATE);
        editor = sp.edit();
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
        View mview = inflater.inflate(R.layout.appslist,null,false);
        final ApplicationInfo info = list.get(i).applicationInfo;
        final TextView textView = mview.findViewById(R.id.list_text);
        textView.setText(packageManager.getApplicationLabel(info).toString());
        if (sp.getAll().containsKey(packageManager.getApplicationLabel(info).toString()))
        {
            textView.setBackgroundColor(0xffFF7575);
        }

        ImageView image = mview.findViewById(R.id.list_img);
        image.setImageDrawable(packageManager.getApplicationIcon(info));

        LinearLayout layout = mview.findViewById(R.id.list_layout);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sp.getAll().containsKey(packageManager.getApplicationLabel(info).toString()))
                {
                    //已经点击过了
                    textView.setBackgroundColor(0xffffffff);
                    editor.remove(textView.getText().toString());
                    editor.apply();
                }else
                {
                    //没点击过
                    textView.setBackgroundColor(0xffFF7575);
                    editor.putString(textView.getText().toString(),info.packageName);
                    editor.apply();
                }
            }
        });
        return mview;
    }
}
