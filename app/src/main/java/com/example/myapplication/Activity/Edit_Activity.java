package com.example.myapplication.Activity;

import static com.example.myapplication.All_Data.assestArr;
import static com.example.myapplication.All_Data.colorArr;
import static com.example.myapplication.All_Data.emojiArr;
import static com.example.myapplication.All_Data.gradientArr;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Adapter.Color_Adapter;
import com.example.myapplication.Adapter.Dialog_Adapter;
import com.example.myapplication.Adapter.Emoji_Adapter;
import com.example.myapplication.Adapter.Font_Adapter;
import com.example.myapplication.All_Data;
import com.example.myapplication.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Locale;
import java.util.Random;


public class Edit_Activity extends AppCompatActivity  {
    TextView textView;
    ImageView expandimg, reloadimg;
    int pose;
    Button btnbg, btntextcolor, btnemoji, textsize, btnfont, btnshare;
    String[] title;
    SeekBar seekBar;
    private int prog;
    private File downloadFile;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        reloadimg = findViewById(R.id.relodimg);
        textView = findViewById(R.id.shayaritxt);
        expandimg = findViewById(R.id.expandimg);
        btnbg = findViewById(R.id.background);
        btntextcolor = findViewById(R.id.textcolor);
        btnshare = findViewById(R.id.share);
        btnfont = findViewById(R.id.font);
        btnemoji = findViewById(R.id.emoji);
        textsize = findViewById(R.id.textsize);


        title = getIntent().getStringArrayExtra("txt");
        pose = getIntent().getIntExtra("position", 0);

        Log.d("VVV", "onCreate: "+pose);
        textView.setText(emojiArr[pose]+"\n"+title[pose]+"\n"+emojiArr[pose]);

        expandimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog dialog = new BottomSheetDialog(Edit_Activity.this);
                view = getLayoutInflater().inflate(R.layout.dialog_layout, null);
                dialog.setContentView(view);
                GridView gridView = dialog.findViewById(R.id.dialogGridview);
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        textView.setBackgroundResource(gradientArr[i]);
                    }
                });
                Dialog_Adapter adapter = new Dialog_Adapter(Edit_Activity.this);
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

        btnbg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BottomSheetDialog dialog = new BottomSheetDialog(Edit_Activity.this);
                dialog.setContentView(R.layout.color_layout);
                GridView gridView = dialog.findViewById(R.id.colorgridview);
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        textView.setBackgroundResource(colorArr[i]);
                    }
                });
                Color_Adapter adapter = new Color_Adapter(Edit_Activity.this);
                gridView.setAdapter(adapter);
                dialog.show();
            }
        });
        btntextcolor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BottomSheetDialog dialog = new BottomSheetDialog(Edit_Activity.this);
                dialog.setContentView(R.layout.color_layout);
                GridView gridView = dialog.findViewById(R.id.colorgridview);
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        textView.setTextColor(getColor(colorArr[i]));
                    }
                });
                Color_Adapter adapter = new Color_Adapter(Edit_Activity.this);
                gridView.setAdapter(adapter);
                dialog.show();
            }
        });


        btnshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bitmap image = getBitmapFromView(textView);
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("image/jpeg");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                image.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
                String currentDateandTime = sdf.format(new Date());
                //downloadFile/IMG_20230711_151334
                downloadFile = new File(All_Data.file.getAbsoluteFile() + "/IMG_" + currentDateandTime + ".jpg");
                try {
                    downloadFile.createNewFile();
                    FileOutputStream fo = new FileOutputStream(downloadFile);
                    fo.write(bytes.toByteArray());
                    Toast.makeText(Edit_Activity.this, "file download", Toast.LENGTH_SHORT).show();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                share.putExtra(Intent.EXTRA_STREAM, Uri.parse(downloadFile.getAbsolutePath()));
                startActivity(Intent.createChooser((Intent) share,"share image"));
            }
        });

        btnemoji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog dialog = new BottomSheetDialog(Edit_Activity.this);
                dialog.setContentView(R.layout.activity_emoji);
                ListView listView = dialog.findViewById(R.id.emojilist);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                        txt_emoji.setText(emojiArr[i]);
//                        txt_emoji2.setText(emojiArr[i]);
                        textView.setText(emojiArr[i] + "\n" + title[pose] + "\n" + emojiArr[i]);
                    }
                });
                Emoji_Adapter adapter = new Emoji_Adapter(Edit_Activity.this);
                listView.setAdapter(adapter);
                dialog.show();
            }
        });

        textsize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog dialog = new BottomSheetDialog(Edit_Activity.this);
                dialog.setContentView(R.layout.font_size_layout);
                seekBar = dialog.findViewById(R.id.seekBar);
                seekBar.setProgress(prog);
                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seeekBar, int progress, boolean fromUser) {
                        if (progress == 0) {
                            //seekBar.setProgress(0);
                            textView.setTextSize(2, 20 + progress);
                        } else {

                            textView.setTextSize(2, 20 + progress);
                        }
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seeekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seeekBar) {
                        prog = seekBar.getProgress();
                        seekBar.setProgress(seekBar.getProgress());
                        dialog.dismiss();
                    }
                });
                dialog.show();
                seekBar.setActivated(true);
            }
        });


        btnfont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {

                BottomSheetDialog dialog = new BottomSheetDialog(Edit_Activity.this);
                dialog.setContentView(R.layout.font_layout);
                GridView gridView = dialog.findViewById(R.id.fontlist);
                Font_Adapter adapter = new Font_Adapter(Edit_Activity.this);
                gridView.setNumColumns(assestArr.length);
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                        Typeface typeface=Typeface.createFromAsset(getAssets(),assestArr[i]);
                        textView.setTypeface(typeface);
                    }
                });
                gridView.setAdapter(adapter);
                dialog.show();
            }
        });
    }


    private Bitmap getBitmapFromView(TextView txt) {
        Bitmap returnedBitmap = Bitmap.createBitmap(txt.getWidth(), txt.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable = txt.getBackground();
        if (bgDrawable != null) {
            bgDrawable.draw(canvas);
        } else {
            canvas.drawColor(Color.WHITE);

        }
        txt.draw(canvas);
        return returnedBitmap;
    }
}




