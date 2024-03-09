package com.example.myapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.Activity.List_Item_Activity;
import com.example.myapplication.R;

public class Adapter_2 extends BaseAdapter {


    List_Item_Activity list_item_2;
    int img;
    int i;
    String[] x;

    public Adapter_2(List_Item_Activity list_item_2, int img, int i, String[] x) {
        this.list_item_2 = list_item_2;
        this.img = img;
        this.i = i;
        this.x = x;
    }

    @Override
    public int getCount() {
        return x.length;
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

        view = LayoutInflater.from(list_item_2).inflate(R.layout.list_item_2,viewGroup,false);
        ImageView imageView=view.findViewById(R.id.imageview);
        TextView txt1=view.findViewById(R.id.txt1);

        imageView.setImageResource(img);
        txt1.setText(""+x[i]);

        return view;
    }
}
