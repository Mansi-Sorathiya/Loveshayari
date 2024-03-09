package com.example.myapplication.Activity;

import static com.example.myapplication.All_Data.emojiArr;
import static com.example.myapplication.All_Data.gradientArr;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.Adapter.Dialog_Adapter;
import com.example.myapplication.Adapter.View_Pager_Adapter;
import com.example.myapplication.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Random;

public class Details_Activity extends AppCompatActivity {

    TextView textView, details_txt;
    Toolbar toolbar;
    String txt[];
    ImageView expandimg, priviousimg, nextitemimg, pencilimg, reloadimg, shareimg, btncopy;
    int position, total;
    int i;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        expandimg = findViewById(R.id.expandimg);
        reloadimg = findViewById(R.id.relodimg);
        details_txt = findViewById(R.id.details_txt);

        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        textView = findViewById(R.id.shayaritxt);

        txt = getIntent().getStringArrayExtra("x");
        total = getIntent().getIntExtra("total", 0);
        position = getIntent().getIntExtra("i", 0);

        textView.setText(emojiArr[position] + "\n" + txt[position] + "\n" + emojiArr[position]);
        details_txt.setText((position + 1) + "/" + txt.length);


        viewPager = findViewById(R.id.view_pager);
        View_Pager_Adapter view_pager_adapter = new View_Pager_Adapter(Details_Activity.this, txt, textView);
        viewPager.setAdapter(view_pager_adapter);
        viewPager.setCurrentItem(position);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                textView.setText("" + txt[position]);
                if (position > 0) {

                    details_txt.setText((position + 1) + "/" + txt.length);
                    textView.setText("" + txt[position]);
                    textView.setText(emojiArr[i] + "\n" + txt[position] + "\n" + emojiArr[i]);

                }
                if (position < txt.length - 1) {

                    details_txt.setText((position + 1) + "/" + txt.length);
                    textView.setText("" + txt[position]);
                    textView.setText(emojiArr[i] + "\n" + txt[position] + "\n" + emojiArr[i]);

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        priviousimg = findViewById(R.id.previousimg);
        priviousimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position > 0) {
                    position--;
                    textView.setText(emojiArr[position] + "\n" + txt[position] + "\n" + emojiArr[position]);
                    details_txt.setText((position + 1) + "/" + txt.length);
                }
            }
        });

        nextitemimg = findViewById(R.id.nextitemimg);
        nextitemimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position < txt.length - 1) {
                    position++;
                    textView.setText(emojiArr[position] + "\n" + txt[position] + "\n" + emojiArr[position]);
                    details_txt.setText((position + 1) + "/" + txt.length);

                }
            }
        });

        expandimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog dialog = new BottomSheetDialog(Details_Activity.this);
                dialog.setContentView(R.layout.dialog_layout);
                GridView gridView = dialog.findViewById(R.id.dialogGridview);
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        textView.setBackgroundResource(gradientArr[i]);
                    }
                });
                Dialog_Adapter adapter = new Dialog_Adapter(Details_Activity.this);
                gridView.setAdapter(adapter);
                dialog.show();
            }
        });

        reloadimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int r = new Random().nextInt(gradientArr.length);
                textView.setBackgroundResource(gradientArr[r]);
            }
        });

        btncopy = findViewById(R.id.copyimg);
        btncopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(textView.getText());
                Toast.makeText(Details_Activity.this, "Copied to clipboard", Toast.LENGTH_SHORT).show();

            }
        });


        pencilimg = findViewById(R.id.pencilimg);
        pencilimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Details_Activity.this, Edit_Activity.class);
                intent.putExtra("txt", txt);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });

        shareimg = findViewById(R.id.shareimg);
        shareimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                /*This will be the actual content you wish you share.*/
                String shareBody = textView.getText().toString();
                /*The type of the content is text, obviously.*/
                intent.setType("text/plain");
                /*Applying information Subject and Body.*/
                intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Share text");
                intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                /*Fire!*/
                startActivity(Intent.createChooser(intent, "Text"));
            }
        });

    }

    private void updateTextView(int position) {
        textView.setText(emojiArr[position] + "\n" + txt[position] + "\n" + emojiArr[position]);
        details_txt.setText((position + 1) + "/" + txt.length);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_Share) {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
            String shareMessage = "\nHi I have downloaded Hindi Love Shayari app \n" +
                    "It is a best app for read and share shayari.\n" +
                    "You should also try\n\n";
            shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=com.loveshayarisn";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            startActivity(Intent.createChooser(shareIntent, "choose one"));
        }
        if (item.getItemId() == R.id.share) {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
            String shareMessage = "\nHi I have downloaded Hindi Love Shayari app \n" +
                    "It is a best app for read and share shayari.\n" +
                    "You should also try\n\n";
            shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=com.loveshayarisn";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            startActivity(Intent.createChooser(shareIntent, "choose one"));
        }

        return super.onOptionsItemSelected(item);
    }
}