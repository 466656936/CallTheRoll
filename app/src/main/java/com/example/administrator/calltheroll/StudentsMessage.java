package com.example.administrator.calltheroll;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/30.
 */
public class StudentsMessage extends Activity {
    //分别定义通讯录中的用户名、电话、地区等信息
    private String stu_number;
    private String stu_name;
    private String stu_class;
    private String stu_job;
    private int shangkeshu;
    private int quanqin;
    private int qingjia;
    private int chidao;
    private int zaotui;
    private int kuangke;
    private SQLiteDatabase db;
    //定义一个ArrayList数组，每一条数据对应通讯录中的一个联系人信息
    private ArrayList<Map<String,Object>> student= new ArrayList<Map<String,Object>>();
    //定义一个ListView
    private ListView mListView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_message);
        db=openOrCreateDatabase("student.db",MODE_PRIVATE,null);
        mListView = (ListView) findViewById(R.id.listView);
        String sql="select stu_number,stu_name,stu_class,stu_job,shangke,quanqin,qingjia,chidao,zaotui,kuangke from students";
        Cursor cursor = db.rawQuery(sql,null);
        int number=0;
        while(cursor.moveToNext()){
            stu_number = cursor.getString(cursor.getColumnIndex("stu_number"));
            stu_name = cursor.getString(cursor.getColumnIndex("stu_name"));
            stu_class = cursor.getString(cursor.getColumnIndex("stu_class"));
            stu_job=cursor.getString(cursor.getColumnIndex("stu_job"));
            shangkeshu=cursor.getInt(cursor.getColumnIndex("shangke"));
            quanqin=cursor.getInt(cursor.getColumnIndex("quanqin"));
            qingjia=cursor.getInt(cursor.getColumnIndex("qingjia"));
            chidao=cursor.getInt(cursor.getColumnIndex("chidao"));
            zaotui=cursor.getInt(cursor.getColumnIndex("zaotui"));
            kuangke=cursor.getInt(cursor.getColumnIndex("kuangke"));
            Map<String,Object> item = new HashMap<String,Object>();
            item.put("number", stu_number);
            item.put("name", stu_name);
            item.put("class", stu_class);
            item.put("job",stu_job);
            item.put("shangkeshu",new String(shangkeshu+""));
            item.put("quanqin",new String(quanqin+""));
            item.put("qingjia",new String(qingjia+""));
            item.put("chidao",new String(chidao+""));
            item.put("zaotui",new String(zaotui+""));
            item.put("kuangke",new String(kuangke+""));
            student.add(item);
            number++;
        }
        //定义一个SimpleAdapter
        SimpleAdapter adapter = new SimpleAdapter(this, student, R.layout.student_message,
                new String[]{"name","number","class","job","shangkeshu","quanqin","qingjia","chidao","zaotui","kuangke"},
                new int[]{R.id.stu_name,R.id.stu_number,R.id.stu_class,R.id.stu_job,R.id.sum_class,R.id.quanqing,R.id.qingjia,R.id.chidao,R.id.zaotui,R.id.kuangke});
        //设置mListView的适配器为adapter
        mListView.setAdapter(adapter);

    }
}
