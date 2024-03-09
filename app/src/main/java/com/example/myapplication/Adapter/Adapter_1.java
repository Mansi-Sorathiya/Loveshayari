package com.example.myapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.Activity.MainActivity;
import com.example.myapplication.R;

public class Adapter_1 extends BaseAdapter {
    MainActivity mainActivity;
    String[] name;
    int[] imagearr;

    public Adapter_1(MainActivity mainActivity, String[] name, int[] imagearr) {
        this.mainActivity = mainActivity;
        this.name = name;
        this.imagearr = imagearr;
    }

    @Override
    public int getCount() {
        return name.length;
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
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        view = LayoutInflater.from(mainActivity).inflate(R.layout.list_item_2,viewGroup,false);
        ImageView imageView=view.findViewById(R.id.imageview);
        TextView txt1=view.findViewById(R.id.txt1);

        imageView.setImageResource(imagearr[i]);
        txt1.setText(""+name[i]);
        return view;
    }
}
