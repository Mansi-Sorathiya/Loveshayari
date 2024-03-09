package com.example.myapplication.Adapter;

import static com.example.myapplication.All_Data.colorArr;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.R;


public class Color_Adapter extends BaseAdapter
{
    private final Activity activity;

    public Color_Adapter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public int getCount() {

        return colorArr.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View view1= LayoutInflater.from(activity).inflate(R.layout.color_layout_item,viewGroup,false);
        TextView textView=view1.findViewById(R.id.color_item);
        textView.setBackgroundResource(colorArr[i]);
       // textView.setTextColor(colorArr[i]);
        return view1;
    }
}
