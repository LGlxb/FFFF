package com.example.lg.zhangzixuweek01_1203;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import helpers.MyHelper;
import view.FlowVivw;

public class SearchActivity extends AppCompatActivity {

    private EditText text;
    private Button btn_add;
    private Button btn_del;
    private List<String> list = new ArrayList<>();
    private FlowVivw vivw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        //数据库
        MyHelper helper = new MyHelper(SearchActivity.this);
        final SQLiteDatabase database = helper.getReadableDatabase();
        //初始化控件
        initViews();
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
                //获取输入数据
                String s = text.getText().toString();
                //添加数据库
                database.execSQL("insert into test (name) values (?) ", new String[]{s});
                list.add(s);
                Toast.makeText(SearchActivity.this,"查看"+s,Toast.LENGTH_SHORT);
                final TextView textView = new TextView(SearchActivity.this);
                textView.setText(s);
                textView.setTextSize(30);
                textView.setPadding(25, 15, 25, 15);
                vivw.addView(textView, params);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String s1 = textView.getText().toString();
                        textView.setClickable(true);
                    }
                });
            }
        });
        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vivw.removeAllViews();
                database.delete("test", null, null);
            }
        });
    }

    private void initViews() {
        btn_add = findViewById(R.id.btn_add);
        btn_del = findViewById(R.id.btn_del);
        vivw = findViewById(R.id.Flow);
        text = findViewById(R.id.ed_add);
    }
}
