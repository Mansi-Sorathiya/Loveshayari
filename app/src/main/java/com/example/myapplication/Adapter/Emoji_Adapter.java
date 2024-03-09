package com.example.myapplication.Adapter;

import static com.example.myapplication.All_Data.emojiArr;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.R;


public class Emoji_Adapter extends BaseAdapter
{
    private final Activity activity;

    public Emoji_Adapter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return emojiArr.length;
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

        View view1= LayoutInflater.from(activity).inflate(R.layout.activity_emoji_item,viewGroup,false);
        TextView textView=view1.findViewById(R.id.emojitext);
        textView.setText(emojiArr[i]);
        return view1;
    }
}
