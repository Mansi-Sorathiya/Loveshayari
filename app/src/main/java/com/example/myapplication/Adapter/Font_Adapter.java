package com.example.myapplication.Adapter;

import static com.example.myapplication.All_Data.assestArr;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.Activity.Edit_Activity;
import com.example.myapplication.R;

public class Font_Adapter extends BaseAdapter {
    Edit_Activity Edit_Activity;

    public Font_Adapter(Edit_Activity Edit_Activity) {
        this.Edit_Activity=Edit_Activity;

    }

    @Override
    public int getCount() {
        return assestArr.length;
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

        View view1= LayoutInflater.from(Edit_Activity).inflate(R.layout.font_layout_item,viewGroup,false);
        TextView textView=view1.findViewById(R.id.fontName);
        Typeface face = Typeface.createFromAsset(Edit_Activity.getAssets(), assestArr[i]);
        textView.setTypeface(face);

        return view1;
    }
}
