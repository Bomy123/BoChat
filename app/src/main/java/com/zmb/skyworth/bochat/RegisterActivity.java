package com.zmb.skyworth.bochat;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.zmb.skyworth.Controller.user.RegisterControll;
import com.zmb.skyworth.bean.User;
import com.zmb.skyworth.config.Constant;
import com.zmb.skyworth.util.JsonUtil;
import com.zmb.skyworth.util.OkHttpUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends Activity {
    private TextView name, pwd, sex, age, photo;
    private EditText editname, editpwd, editage, editphoto;
    private Button register;
    private RadioButton man;
    JsonUtil jsonUtil;
    MyHandler myHandler = new MyHandler();
    private RegisterControll registerControll = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();

    }

    private void initView() {
        name = (TextView) findViewById(R.id.name);
        pwd = (TextView) findViewById(R.id.pwd);
        sex = (TextView) findViewById(R.id.sex);
        age = (TextView) findViewById(R.id.age);
        photo = (TextView) findViewById(R.id.photo);
        editname = (EditText) findViewById(R.id.inputname);
        editpwd = (EditText) findViewById(R.id.inputpwd);
        editage = (EditText) findViewById(R.id.inputage);
        editphoto = (EditText) findViewById(R.id.selphoto);
        register = (Button) findViewById(R.id.register);
        man = (RadioButton) findViewById(R.id.man);
        man.setSelected(true);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regist();
            }
        });

    }

    private void regist() {
        jsonUtil = new JsonUtil();
        User user = new User();
        user.setUsername(editname.getText().toString());
        user.setPassword(editpwd.getText().toString());
        user.setAge(Integer.parseInt(editage.getText().toString()));
        user.setSex(man.isSelected() ? 1 : 0);
        user.setPhoto(editphoto.getText().toString());
        registerControll = new RegisterControll(user);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = registerControll.tryRegister();
                RegisterActivity.this.myHandler.sendMessage(message);
            }
        }).start();

    }

    class MyHandler extends Handler {
        public MyHandler() {
            super();
        }

        public MyHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Constant.NAME_NULL:
                    Toast.makeText(RegisterActivity.this, "用户名不能为空！", Toast.LENGTH_SHORT).show();
                    break;
                case Constant.PWD_NULL:
                    Toast.makeText(RegisterActivity.this, "密码不不能为空！", Toast.LENGTH_SHORT).show();
                    break;
                case Constant.SUCCESS:
                    Toast.makeText(RegisterActivity.this, "恭喜您，注册成功！", Toast.LENGTH_SHORT).show();
                    break;
                case Constant.FAILED:
                    Toast.makeText(RegisterActivity.this, "sorry，注册失败，请检查注册信息！", Toast.LENGTH_SHORT).show();
                    break;
                case Constant.EXISTS:
                    Toast.makeText(RegisterActivity.this, "该用户以存在，请更换用户名或直接登陆！", Toast.LENGTH_SHORT).show();
                    break;
                case Constant.ERROR:
                    Toast.makeText(RegisterActivity.this, "系统错误！", Toast.LENGTH_SHORT).show();
            }
            super.handleMessage(msg);
        }
    }
}
