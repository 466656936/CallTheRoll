package com.example.administrator.calltheroll;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/11/2.
 */
public class Insert extends Activity {
    SQLiteDatabase db;
    EditText insert_id;
    Button insert_search;
    EditText insert_number;
    EditText insert_name;
    EditText insert_class;
    EditText insert_job;
    int number=0;
    int length=0;
    String stu_name[];
    String stu_number[];
    String stu_class[];
    String stu_job[];
    int stu_photo[];
    Button insert_last,insert_sure,insert_next;
    ImageView photo;
    Cursor get_photo;
    Bitmap imagebitmap;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        db = openOrCreateDatabase("student.db", MODE_PRIVATE, null);
        find();
        photo= (ImageView) findViewById(R.id.insert_photo);
        insert_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (safe()) {
                    String search = "select stu_name,stu_number,stu_class,stu_job,photo_id from students" +
                            " where stu_name='" + insert_id.getText().toString() + "' or" +
                            " stu_number='" + insert_id.getText().toString() + "'";
                    Cursor cursor = db.rawQuery(search, null);
                    length = cursor.getCount();
                    if (length > 0) {
                        stu_name = new String[length];
                        stu_number = new String[length];
                        stu_class = new String[length];
                        stu_job = new String[length];
                        stu_photo=new int[length];
                        number = 0;
                        for (int i = 0; cursor.moveToNext(); i++) {
                            stu_name[i] = cursor.getString(cursor.getColumnIndex("stu_name"));
                            stu_class[i] = cursor.getString(cursor.getColumnIndex("stu_class"));
                            stu_job[i] = cursor.getString(cursor.getColumnIndex("stu_job"));
                            stu_number[i] = cursor.getString(cursor.getColumnIndex("stu_number"));
                            stu_photo[i]=cursor.getInt(cursor.getColumnIndex("photo_id"));
                        }
                        insert_number.setEnabled(false);
                        insert_last.setVisibility(View.VISIBLE);
                        insert_next.setVisibility(View.VISIBLE);
                        insert_number.setText(stu_number[number]);
                        insert_name.setText(stu_name[number]);
                        insert_class.setText(stu_class[number]);
                        insert_job.setText(stu_job[number]);
                        get_photo=db.rawQuery("select photo from stu_photos where photo_id="+stu_photo[number],null);
                        if(get_photo.moveToNext()) {
                            byte[] imagequery = get_photo.getBlob(get_photo.getColumnIndex("photo"));
                            imagebitmap = BitmapFactory.decodeByteArray(imagequery, 0, imagequery.length);
                            photo.setImageBitmap(imagebitmap);
                        }
                    } else {
                        Toast.makeText(Insert.this, "未找到学号或姓名为:" + insert_id.getText().toString() + "的信息！", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                    Toast.makeText(Insert.this, "别想着玩坏数据库好吗!", Toast.LENGTH_SHORT).show();
            }
        });
        insert_last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!insert_number.isEnabled()) {
                    if (number > 0) {
                        number--;
                        insert_number.setText(stu_number[number]);
                        insert_name.setText(stu_name[number]);
                        insert_class.setText(stu_class[number]);
                        insert_job.setText(stu_job[number]);
                        get_photo=db.rawQuery("select photo from stu_photos where photo_id="+stu_photo[number],null);
                        if(get_photo.moveToNext()) {
                            byte[] imagequery = get_photo.getBlob(get_photo.getColumnIndex("photo"));
                            imagebitmap = BitmapFactory.decodeByteArray(imagequery, 0, imagequery.length);
                            photo.setImageBitmap(imagebitmap);
                        }
                    } else {
                        Toast.makeText(Insert.this, "该生为第一个", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        insert_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!insert_number.isEnabled()) {
                    if (number < length - 1) {
                        number++;
                        insert_number.setText(stu_number[number]);
                        insert_name.setText(stu_name[number]);
                        insert_class.setText(stu_class[number]);
                        insert_job.setText(stu_job[number]);
                        get_photo=db.rawQuery("select photo from stu_photos where photo_id="+stu_photo[number],null);
                        if(get_photo.moveToNext()) {
                            byte[] imagequery = get_photo.getBlob(get_photo.getColumnIndex("photo"));
                            imagebitmap = BitmapFactory.decodeByteArray(imagequery, 0, imagequery.length);
                            photo.setImageBitmap(imagebitmap);
                        }
                    } else {
                        Toast.makeText(Insert.this, "该生为最后一个", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        insert_sure.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (safe()) {
                    if (!insert_number.isEnabled()) {
                        if (insert_name.getText().toString().length() > 0 && insert_class.getText().toString().length() > 0 && insert_job.getText().toString().length() > 0) {

                            String update = "update students set stu_name='" + insert_name.getText().toString() + "'," +
                                    "stu_class='" + insert_class.getText().toString() + "',stu_job='" + insert_job.getText().toString() +
                                    "' where stu_number='" + insert_number.getText().toString() + "'";
                            db.execSQL(update);
                            Toast.makeText(Insert.this, "更新学生信息完成!", Toast.LENGTH_SHORT).show();
                            insert_id.setText("");
                            insert_class.setText("");
                            insert_number.setText("");
                            insert_name.setText("");
                            insert_id.setText("");
                            insert_job.setText("");

                            insert_number.setEnabled(true);
                            insert_last.setVisibility(View.INVISIBLE);
                            insert_next.setVisibility(View.INVISIBLE);
                        } else
                            Toast.makeText(Insert.this, "请输入完整信息!", Toast.LENGTH_SHORT).show();
                    } else {
                        if (insert_number.getText().length() == 12 && insert_number.getText().charAt(0) == '6' && insert_number.getText().charAt(1) == '3' &&
                                insert_name.getText().toString().length() > 0 && insert_class.getText().toString().length() > 0) {
                            if (insert_job.getText().toString().length() == 0)
                                insert_job.setText("未任职");
                            String insert = "insert into students(stu_number,stu_name,stu_class,stu_job)" +
                                    "values('" + insert_number.getText().toString() + "','"
                                    + insert_name.getText().toString() + "','" + insert_class.getText().toString()
                                    + "','" + insert_job.getText().toString() + "')";
                            db.execSQL(insert);
                            Toast.makeText(Insert.this, "添加学生信息完成!", Toast.LENGTH_SHORT).show();
                            insert_id.setText("");
                            insert_class.setText("");
                            insert_number.setText("");
                            insert_name.setText("");
                            insert_id.setText("");
                            insert_job.setText("");
                        } else
                            Toast.makeText(Insert.this, "请输入除职务外的完整正确信息!", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                    Toast.makeText(Insert.this, "别想着玩坏数据库好吗!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void find(){
        insert_id=(EditText) findViewById(R.id.insert_id);
        insert_search=(Button)findViewById(R.id.insert_search);
        insert_number=(EditText)findViewById(R.id.insert_number);
        insert_name= (EditText) findViewById(R.id.insert_name);
        insert_class= (EditText) findViewById(R.id.insert_class);
        insert_job=(EditText) findViewById(R.id.insert_job);
        insert_last= (Button) findViewById(R.id.inser_last);
        insert_next= (Button) findViewById(R.id.insert_next);
        insert_sure=(Button) findViewById(R.id.insert_sure);
    }
    public boolean safe(){
        String check[]=new String[5];
        check[0]=insert_id.getText().toString();
        check[1]=insert_number.getText().toString();
        check[2]=insert_name.getText().toString();
        check[3]=insert_class.getText().toString();
        check[4]=insert_job.getText().toString();
        for(int i=0;i<5;i++){
            if(check[i].contains("'")||check[i].contains("-")||check[i].contains("/")||check[i].contains("*"))
                return false;
        }
        return true;
    }
}
