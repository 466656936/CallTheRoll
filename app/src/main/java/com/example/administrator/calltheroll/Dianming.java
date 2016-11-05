package com.example.administrator.calltheroll;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.session.PlaybackState;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/10/31.
 */
public class Dianming extends Activity{
    private SQLiteDatabase db;
    TextView dian_time;
    TextView dian_number;
    TextView dian_name;
    TextView dian_job;
    TextView dian_class;
    String stu_number[];
    String stu_name[];
    String stu_class[];
    String stu_job[];
    int stu_photo[];
    Button quandao;
    Button dian_1,dian_2,dian_3,dian_4,dian_5;
    Button next;
    int number;
    int length;
    int num_week;
    String state;
    boolean click_quandao=false;

    ImageView photo;
    Cursor get_photo;
    Bitmap imagebitmap;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dianming);
        db = openOrCreateDatabase("student.db", MODE_PRIVATE, null);
        String sql = "select stu_number,stu_name,stu_class,stu_job,photo_id from students";
        Cursor cursor = db.rawQuery(sql, null);
        length = cursor.getCount();
        Cursor week = db.rawQuery("select max(week) num_week from dianming", null);
        week.moveToNext();

        num_week = week.getInt(week.getColumnIndex("num_week"));
        if (num_week==0) {
            num_week = 1;
            number = 0;
        }
        else {
            Cursor finish = db.rawQuery("select * from dianming where week=" + num_week, null);
            if (finish.getCount() < length) {
               number=finish.getCount();
            }
            else {
                num_week = week.getInt(week.getColumnIndex("num_week")) + 1;
                number=0;
            }
        }
        stu_number = new String[length];
        stu_class = new String[length];
        stu_job = new String[length];
        stu_name = new String[length];
        stu_photo=new int[length];
        int i=0;
        while (cursor.moveToNext()) {
            stu_number[i] = cursor.getString(cursor.getColumnIndex("stu_number"));
            stu_name[i] = cursor.getString(cursor.getColumnIndex("stu_name"));
            stu_class[i] = cursor.getString(cursor.getColumnIndex("stu_class"));
            stu_job[i] = cursor.getString(cursor.getColumnIndex("stu_job"));
            stu_photo[i]=cursor.getInt(cursor.getColumnIndex("photo_id"));
            i++;
        }
        dian_class = (TextView) findViewById(R.id.dian_class);
        dian_name = (TextView) findViewById(R.id.dian_name);
        dian_job = (TextView) findViewById(R.id.dian_job);
        dian_number = (TextView) findViewById(R.id.dian_number);
        dian_time = (TextView) findViewById(R.id.dian_time);
        //图片处理
        photo=(ImageView)findViewById(R.id.dian_photo);
        get_photo=db.rawQuery("select photo from stu_photos where photo_id="+stu_photo[number],null);
        if(get_photo.moveToNext()) {
            byte[] imagequery = get_photo.getBlob(get_photo.getColumnIndex("photo"));
            imagebitmap = BitmapFactory.decodeByteArray(imagequery, 0, imagequery.length);
            photo.setImageBitmap(imagebitmap);
        }

        //图片处理结束
        dian_time.setText(num_week + "");
        dian_class.setText(stu_class[number]);
        dian_number.setText(stu_number[number]);
        dian_name.setText(stu_name[number]);
        dian_job.setText(stu_job[number]);
        state = "√";
        quandao = (Button) findViewById(R.id.quandao);
        next = (Button) findViewById(R.id.dian_next);

        quandao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!click_quandao) {
                    //click_quandao=true;
                    Toast.makeText(Dianming.this, "正在添加剩余学生点名信息", Toast.LENGTH_SHORT).show();
                    Cursor get_time;
                    String get;
                    int quanqin_time;
                    int shangke_time;
                    while (number < length) {
                        /*
                        db.execSQL("insert into dianming(stu_number,state,week) values('" + stu_number[number] + "','√'," + num_week + ")");
                        get_time = db.rawQuery("select quanqin from students where stu_number='" + stu_number[number] + "'", null);
                        get_time.moveToNext();
                        quanqin_time = get_time.getInt(get_time.getColumnIndex("quanqin")) + 1;
                        get_time = db.rawQuery("select shangke from students where stu_number='" + stu_number[number] + "'", null);
                        get_time.moveToNext();
                        shangke_time = get_time.getInt(get_time.getColumnIndex("shangke")) + 1;
                        db.execSQL("update students set quanqin=" + quanqin_time + " where stu_number='" + stu_number[number] + "'");
                        db.execSQL("update students set shangke=" + shangke_time + " where stu_number='" + stu_number[number] + "'");
                        ++number;*/
                        next.performClick();
                    }
                    Toast.makeText(Dianming.this, "已点完所有学生", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Dianming.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!click_quandao) {
                    if (number < length) {
                        Cursor get_time;
                        String state_str = "";
                        int state_time;
                        int shangke_time;
                        db.execSQL("insert into dianming(stu_number,state,week) values('" + stu_number[number] + "','" + state + "'," + num_week + ")");
                        switch (state) {
                            case "√":
                                state_str = "quanqin";
                                break;
                            case "○":
                                state_str = "chidao";
                                break;
                            case "△":
                                state_str = "qingjia";
                                break;
                            case "×":
                                state_str = "kuangke";
                                break;
                            case "☆":
                                state_str = "zaotui";
                                break;
                            //“△”代表请假，“×”代表旷课，“√”代表全勤，“☆”代表早退。
                        }
                        //更新状态
                        get_time = db.rawQuery("select " + state_str.toString() + " from students where stu_number='" + stu_number[number] + "'", null);
                        get_time.moveToNext();
                        state_time = get_time.getInt(get_time.getColumnIndex(state_str.toString())) + 1;
                        db.execSQL("update students set " + state_str.toString() + "=" + state_time + " where stu_number='" + stu_number[number] + "'");
                        //更新总课时
                        get_time = db.rawQuery("select shangke from students where stu_number='" + stu_number[number] + "'", null);
                        get_time.moveToNext();
                        shangke_time = get_time.getInt(get_time.getColumnIndex("shangke")) + 1;
                        db.execSQL("update students set shangke=" + shangke_time + " where stu_number='" + stu_number[number] + "'");
                        number++;
                        state = "√";
                        if (number < length) {
                            dian_class.setText(stu_class[number]);
                            dian_number.setText(stu_number[number]);
                            dian_name.setText(stu_name[number]);
                            dian_job.setText(stu_job[number]);
                            get_photo=db.rawQuery("select photo from stu_photos where photo_id="+stu_photo[number],null);
                            if(get_photo.moveToNext()) {
                                byte[] imagequery = get_photo.getBlob(get_photo.getColumnIndex("photo"));
                                imagebitmap = BitmapFactory.decodeByteArray(imagequery, 0, imagequery.length);
                                photo.setImageBitmap(imagebitmap);
                            }
                        } else {
                            Toast.makeText(Dianming.this, "已点完所有学生", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Dianming.this, MainActivity.class);
                            startActivity(intent);

                        }
                    }
                }
            }
        });
        dian_1=(Button) findViewById(R.id.dianming1);
        dian_2=(Button) findViewById(R.id.dianming2);
        dian_3=(Button) findViewById(R.id.dianming3);
        dian_4=(Button) findViewById(R.id.dianming4);
        dian_5=(Button) findViewById(R.id.dianming5);
        dian_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                state="√";
                Toast.makeText(Dianming.this, "该生全勤", Toast.LENGTH_SHORT).show();
            }
        });
        //
        dian_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                state="△";
                Toast.makeText(Dianming.this, "该生请假", Toast.LENGTH_SHORT).show();
            }
        });
        dian_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                state="○";
                Toast.makeText(Dianming.this, "该生迟到", Toast.LENGTH_SHORT).show();
            }
        });
        dian_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                state="☆";
                Toast.makeText(Dianming.this, "该生早退", Toast.LENGTH_SHORT).show();
            }
        });
        dian_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                state="×";
                Toast.makeText(Dianming.this, "该生旷课", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
