package com.example.myapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.myapplication.Activity.Details_Activity;
import com.example.myapplication.R;

public class View_Pager_Adapter extends PagerAdapter
{

    Details_Activity details_activity;
    String[] txt;
    TextView textView;
    public View_Pager_Adapter(Details_Activity details_activity, String[] txt, TextView textView)
    {
        this.details_activity=details_activity;
        this.txt=txt;
        this.textView=textView;

    }

    @Override
    public int getCount() {
        return txt.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {

        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view= LayoutInflater.from(details_activity).inflate(R.layout.view_pager_item,container,false);

//        TextView textView1=view.findViewById(R.id.shayaritxt);
//        textView1.setText(""+txt[position]);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((View) object);
    }
}
