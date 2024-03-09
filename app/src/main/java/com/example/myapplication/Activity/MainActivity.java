package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.myapplication.Adapter.Adapter_1;
import com.example.myapplication.R;

public class MainActivity extends AppCompatActivity
{
    ListView listView;
    Toolbar toolbar;
    String name[]={"शुभकामनाएं शायरी","दोस्ती शायरी","मजेदार शायरी","भगवान शायरी","प्रेरणा श्रोत शायरी","जीवन शायरी",
                    "मोहब्बत शायरी","यादें शायरी","अन्य शायरी","राजनीति शायरी","प्रेम शायरी","दर्द शायरी",
                    "शराब शायरी","बेवफा शायरी", "जन्मदिन शायरी", "होली शायरी"};
    private int imagearr[]={R.drawable.shubh,R.drawable.dosti,R.drawable.majedar,R.drawable.bhagvan,
                            R.drawable.prerna,R.drawable.jivan, R.drawable.mohabbat,R.drawable.yaade,
                            R.drawable.anya,R.drawable.rajniti,R.drawable.prem,R.drawable.dard,
                            R.drawable.sharab,R.drawable.bewafa,R.drawable.janamdin,R.drawable.holi};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=findViewById(R.id.listview);
        toolbar=findViewById(R.id.toolBar);

        setSupportActionBar(toolbar);

        Adapter_1 adapter =new Adapter_1(MainActivity.this,name,imagearr);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, List_Item_Activity.class);
                intent.putExtra("pos",i);
                intent.putExtra("img",imagearr[i]);
                startActivity(intent);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menu_Share)
        {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
            String shareMessage= "\nHi I have downloaded Hindi Love Shayari app \n" +
                    "It is a best app for read and share shayari.\n" +
                    "You should also try\n\n";
            shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=com.loveshayarisn";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            startActivity(Intent.createChooser(shareIntent, "choose one"));
        }
        if(item.getItemId()==R.id.share)
        {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
            String shareMessage= "\nHi I have downloaded Hindi Love Shayari app \n" +
                    "It is a best app for read and share shayari.\n" +
                    "You should also try\n\n";
            shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=com.loveshayarisn";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            startActivity(Intent.createChooser(shareIntent, "choose one"));
        }

        return super.onOptionsItemSelected(item);
    }
}