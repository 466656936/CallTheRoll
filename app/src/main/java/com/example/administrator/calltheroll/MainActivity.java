package com.example.administrator.calltheroll;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    Button bt1;
    Button bt2;
    Button bt3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db=openOrCreateDatabase("student.db",MODE_PRIVATE,null);
        open();
        bt1=(Button)findViewById(R.id.button1);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, StudentsMessage.class);
                    startActivity(intent);
            }
        });
        bt2=(Button)findViewById(R.id.button2);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Insert.class);
                startActivity(intent);
            }
        });
        bt3=(Button)findViewById(R.id.button3);
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Dianming.class);
                startActivity(intent);
            }
        });

    }
    void open(){
        String sql = "create table if not exists students(number integer PRIMARY KEY autoincrement,stu_number text unique,stu_name text not null,stu_class text not null,stu_job text default '未任职'," +
                "shangke integer default 0,quanqin integer default 0,qingjia integer default 0,chidao integer default 0,zaotui default 0,kuangke default 0,photo_id integer default -1)";
        db.execSQL(sql.toString());
        sql="create table if not exists dianming(number integer PRIMARY KEY autoincrement,stu_number text,week integer,state text)";
        db.execSQL(sql.toString());
        sql="create table if not exists stu_photos(photo_id integer PRIMARY KEY,photo BLOB)";
        db.execSQL(sql);
        Cursor check=db.rawQuery("select * from students",null);
        if(check.getCount()==0)
        {
            sql="insert into students(stu_number,stu_name,stu_class) values(?,?,?)";
            db.execSQL(sql,new Object[]{"631406010102","莫天金","计科1401班"});
            db.execSQL(sql,new Object[]{"631406010103","吴国平","计科1401班"});
            db.execSQL(sql,new Object[]{"631406010104","孙文斌","计科1401班"});
            db.execSQL(sql,new Object[]{"631406010105","潘俊旭","计科1401班"});
            db.execSQL(sql,new Object[]{"631406010106","石佳磊","计科1401班"});
            db.execSQL(sql,new Object[]{"631406010107","赵权","计科1401班"});
            db.execSQL(sql,new Object[]{"631406010108","马鹏","计科1401班"});
            db.execSQL(sql,new Object[]{"631406010109","郭文浩","计科1401班"});
            db.execSQL(sql,new Object[]{"631406010110","李季","计科1401班"});
            db.execSQL(sql,new Object[]{"631406010111","陈仕豪","计科1401班"});
            db.execSQL(sql,new Object[]{"631406010112","杜菲","计科1401班"});
            db.execSQL(sql,new Object[]{"631406010113","李红兵","计科1401班"});
            db.execSQL(sql,new Object[]{"631406010114","蔡佳辰","计科1401班"});
            db.execSQL(sql,new Object[]{"631406010115","肖洒益","计科1401班"});
            db.execSQL(sql,new Object[]{"631406010117","伍凯荣","计科1401班"});
            db.execSQL(sql,new Object[]{"631406010118","张林","计科1401班"});
            db.execSQL(sql,new Object[]{"631406010119","王斌","计科1401班"});
            db.execSQL(sql,new Object[]{"631406010120","廖宇峰","计科1401班"});
            db.execSQL(sql,new Object[]{"631406010122","谭建","计科1401班"});
            db.execSQL(sql,new Object[]{"631406010123","左永和","计科1401班"});
            db.execSQL(sql,new Object[]{"631406010124","王增辉","计科1401班"});
            db.execSQL(sql,new Object[]{"631406010128","任中豪","计科1401班"});
            db.execSQL(sql,new Object[]{"631406010129","何泳桦","计科1401班"});
            db.execSQL(sql,new Object[]{"631406010130","张力","计科1401班"});
            db.execSQL(sql,new Object[]{"631406010131","任达","计科1401班"});
            db.execSQL(sql,new Object[]{"631404090425","李自力","计科1402班"});
            db.execSQL(sql,new Object[]{"631406010201","肖霞","计科1402班"});
            db.execSQL(sql,new Object[]{"631406010203","郑建峰","计科1402班"});
            db.execSQL(sql,new Object[]{"631406010206","程飘","计科1402班"});
            db.execSQL(sql,new Object[]{"631406010207","王浩","计科1402班"});
            db.execSQL(sql,new Object[]{"631406010208","李建鹏","计科1402班"});
            db.execSQL(sql,new Object[]{"631406010209","张向守","计科1402班"});
            db.execSQL(sql,new Object[]{"631406010210","邱凯","计科1402班"});
            db.execSQL(sql,new Object[]{"631406010211","罗七奇","计科1402班"});
            db.execSQL(sql,new Object[]{"631406010214","李佩科","计科1402班"});
            db.execSQL(sql,new Object[]{"631406010216","黄许飞","计科1402班"});
            db.execSQL(sql,new Object[]{"631406010217","钟富胜","计科1402班"});
            db.execSQL(sql,new Object[]{"631406010218","陈鸿","计科1402班"});
            db.execSQL(sql,new Object[]{"631406010219","易家洛","计科1402班"});
            db.execSQL(sql,new Object[]{"631406010220","邓强","计科1402班"});
            db.execSQL(sql,new Object[]{"631406010222","原晨","计科1402班"});
            db.execSQL(sql,new Object[]{"631406010223","袁益","计科1402班"});
            db.execSQL(sql,new Object[]{"631406010224","石峻臣","计科1402班"});
            db.execSQL(sql,new Object[]{"631406010226","张洋","计科1402班"});
            db.execSQL(sql,new Object[]{"631406010227","唐玉","计科1402班"});
            db.execSQL(sql,new Object[]{"631406010228","秦皓","计科1402班"});
            db.execSQL(sql,new Object[]{"631406010229","刘妍","计科1402班"});
            db.execSQL(sql,new Object[]{"631406010230","严伟安","计科1402班"});
            db.execSQL(sql,new Object[]{"631406010231","杨煌","计科1402班"});
            db.execSQL(sql,new Object[]{"631426140213","李中耀","计科1402班"});
            db.execSQL(sql,new Object[]{"631306050204","高杰","计科1403班"});
            db.execSQL(sql,new Object[]{"631406010301","李佳佳","计科1403班"});
            db.execSQL(sql,new Object[]{"631406010303","何友鹏","计科1403班"});
            db.execSQL(sql,new Object[]{"631406010306","郭耕佐","计科1403班"});
            db.execSQL(sql,new Object[]{"631406010307","杨飘","计科1403班"});
            db.execSQL(sql,new Object[]{"631406010308","李中清","计科1403班"});
            db.execSQL(sql,new Object[]{"631406010309","王亢","计科1403班"});
            db.execSQL(sql,new Object[]{"631406010311","郭睿","计科1403班"});
            db.execSQL(sql,new Object[]{"631406010312","江航","计科1403班"});
            db.execSQL(sql,new Object[]{"631406010313","张丰伟","计科1403班"});
            db.execSQL(sql,new Object[]{"631406010314","左琴","计科1403班"});
            db.execSQL(sql,new Object[]{"631406010315","徐红涛","计科1403班"});
            db.execSQL(sql,new Object[]{"631406010316","王梦迪","计科1403班"});
            db.execSQL(sql,new Object[]{"631406010317","陶军华","计科1403班"});
            db.execSQL(sql,new Object[]{"631406010318","黄震国","计科1403班"});
            db.execSQL(sql,new Object[]{"631406010319","张舰心","计科1403班"});
            db.execSQL(sql,new Object[]{"631406010321","杨升","计科1403班"});
            db.execSQL(sql,new Object[]{"631406010322","成黉","计科1403班"});
            db.execSQL(sql,new Object[]{"631406010323","丁莹","计科1403班"});
            db.execSQL(sql,new Object[]{"631406010324","冯明建","计科1403班"});
            db.execSQL(sql,new Object[]{"631406010326","陈雷","计科1403班"});
            db.execSQL(sql,new Object[]{"631406010327","孙作明","计科1403班"});
            db.execSQL(sql,new Object[]{"631406010328","李帆","计科1403班"});
            db.execSQL(sql,new Object[]{"631406010329","樊庆珂","计科1403班"});
            db.execSQL(sql,new Object[]{"631406010330","张建辉","计科1403班"});
            db.execSQL(sql,new Object[]{"631406010331","欧诗卿","计科1403班"});
            db.execSQL(sql,new Object[]{"631424210205","李嘉华","计科1403班"});
            db.execSQL(sql,new Object[]{"631406010401","刘翠芳","计科1404班"});
            db.execSQL(sql,new Object[]{"631406010402","李杰","计科1404班"});
            db.execSQL(sql,new Object[]{"631406010404","杨林","计科1404班"});
            db.execSQL(sql,new Object[]{"631406010405","刘佳","计科1404班"});
            db.execSQL(sql,new Object[]{"631406010408","刘钊宏","计科1404班"});
            db.execSQL(sql,new Object[]{"631406010409","董刚","计科1404班"});
            db.execSQL(sql,new Object[]{"631406010410","伍守增","计科1404班"});
            db.execSQL(sql,new Object[]{"631406010411","裴丹","计科1404班"});
            db.execSQL(sql,new Object[]{"631406010412","梁健","计科1404班"});
            db.execSQL(sql,new Object[]{"631406010413","李奕达","计科1404班"});
            db.execSQL(sql,new Object[]{"631406010416","陈劲","计科1404班"});
            db.execSQL(sql,new Object[]{"631406010417","朱彤","计科1404班"});
            db.execSQL(sql,new Object[]{"631406010418","张亮","计科1404班"});
            db.execSQL(sql,new Object[]{"631406010419","陈兴","计科1404班"});
            db.execSQL(sql,new Object[]{"631406010422","龚毅","计科1404班"});
            db.execSQL(sql,new Object[]{"631406010423","罗艺","计科1404班"});
            db.execSQL(sql,new Object[]{"631406010424","陈朝阳","计科1404班"});
            db.execSQL(sql,new Object[]{"631406010425","张宇","计科1404班"});
            db.execSQL(sql,new Object[]{"631406010426","向健","计科1404班"});
            db.execSQL(sql,new Object[]{"631406010428","廖旺","计科1404班"});
            db.execSQL(sql,new Object[]{"631406010430","陈建川","计科1404班"});
            input_photo();
        }
    }
    void input_photo(){
        Cursor get=db.rawQuery("select number,stu_number from students",null);
        Context ctx=getBaseContext();
        int photoId;
        int id;
        String stu_number;
        String img_name;
        String insert;String update;
        byte[] imagedata1;
        Bitmap bitmap1;
        ByteArrayOutputStream baos;
        while (get.moveToNext()) {
            id = get.getInt(get.getColumnIndex("number"));
            stu_number=get.getString(get.getColumnIndex("stu_number"));
            img_name="a"+stu_number;
            photoId = getResources().getIdentifier(img_name, "drawable" , ctx.getPackageName());
            if(photoId==0) photoId=R.drawable.error;
            bitmap1 = BitmapFactory.decodeResource(getResources(), photoId);
            int size = bitmap1.getWidth() * bitmap1.getHeight() * 4;
            baos = new ByteArrayOutputStream(size);
            //设置位图的压缩格式，质量为100%，并放入字节数组输出流中
            bitmap1.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            //将字节数组输出流转化为字节数组byte[]
            imagedata1 = baos.toByteArray();
            insert = "insert into stu_photos(photo_id,photo) values(?,?)";
            db.execSQL(insert, new Object[]{id,imagedata1});
            //关闭字节数组输出流
            baos.reset();
            bitmap1.recycle();
            update="update students set photo_id="+id+" where stu_number='"+stu_number+"'";
            db.execSQL(update);
        }
    }
}
