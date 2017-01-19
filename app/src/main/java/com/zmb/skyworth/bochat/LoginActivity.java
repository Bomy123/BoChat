package com.zmb.skyworth.bochat;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.zmb.skyworth.Controller.user.LoginControl;
import com.zmb.skyworth.config.Constant;

import java.io.IOException;

public class LoginActivity extends Activity {
private EditText uname,pwd;
    private Button login,fpwd,register;
    private CheckBox isRemember;
    private ProgressBar progressBar;
    MyHandler myHandler = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        myHandler = new MyHandler();
        initView();
    }
    private void initView()
    {
        uname = (EditText) findViewById(R.id.inputname);
        pwd = (EditText) findViewById(R.id.inputpwd);
        isRemember = (CheckBox) findViewById(R.id.remember);
        login = (Button) findViewById(R.id.login);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        fpwd = (Button) findViewById(R.id.forgetPwd);
        fpwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                tryLogin();
            }
        });
        register = (Button) findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });


    }
    private void tryLogin()
    {
        Log.i("login","trylogin");
        String username = uname.getText().toString();
        String password = pwd.getText().toString();
        Boolean rememberMe = isRemember.isChecked();
        SharedPreferences sp = this.getSharedPreferences("loginData", MODE_PRIVATE);
        final LoginControl loginControl = new LoginControl(username,password,rememberMe,sp);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Message msg = new Message();
                    Log.i("login","msg");
                    msg.what =  loginControl.tryLogin();
                    Log.i("Login",String.valueOf(msg.what));
                    LoginActivity.this.myHandler.sendMessage(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    class MyHandler extends Handler
    {
        public MyHandler()
        {
            super();
        }
        public MyHandler(Looper looper)
        {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            progressBar.setVisibility(View.GONE);
            switch (msg.what)
            {
                case Constant.SUCCESS:
                    Intent intent = new Intent(LoginActivity.this,MainPage.class);
                    startActivity(intent);
                    break;
                case Constant.FAILED:
                    Toast.makeText(LoginActivity.this,"用户名或密码错误！",Toast.LENGTH_SHORT).show();
                    break;
                case Constant.NAME_NULL:
                    Toast.makeText(LoginActivity.this,"用户名不能为空！",Toast.LENGTH_SHORT).show();
                    break;
                case Constant.PWD_NULL:
                    Toast.makeText(LoginActivity.this,"密码不能为空！",Toast.LENGTH_SHORT).show();

            }

                super.handleMessage(msg);
        }
    }

    class User
    {

    }
}
