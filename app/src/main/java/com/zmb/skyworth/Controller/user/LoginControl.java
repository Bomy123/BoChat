package com.zmb.skyworth.Controller.user;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.util.Log;

import com.zmb.skyworth.config.Constant;
import com.zmb.skyworth.util.OkHttpUtil;

import java.io.IOException;

/**
 * Created by zhangmingbao on 16-11-14.
 */

public class LoginControl {
    private final String TAG = "LoginControl";
    private String username = "";
    private String password = "";
    private boolean isRemember = false;
    SharedPreferences loginInfo = null;

    public LoginControl(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public LoginControl(String username, String password, boolean isRemember) {
        this.username = username;
        this.password = password;
        this.isRemember = isRemember;
    }

    public LoginControl(String username, String password, boolean isRemember, SharedPreferences loginInfo) {
        this.username = username;
        this.password = password;
        this.isRemember = isRemember;
        this.loginInfo = loginInfo;
    }

    public int tryLogin() throws IOException {
        if (username.equals(""))
            return Constant.NAME_NULL;
        else if (password.equals(""))
            return Constant.PWD_NULL;
        if (loginInfo == null) {
            return getLoginRes();
        }
        if (isRemember) {
            if (loginInfo.contains(username)) {
                if (loginInfo.getString(username, password) != null)
                    return Constant.SUCCESS;
                else if (getLoginRes() == Constant.SUCCESS) {
                    SharedPreferences.Editor editor = loginInfo.edit();
                    editor.remove(username);
                    editor.putString(username, password);
                    editor.commit();
                    return Constant.SUCCESS;
                } else
                    return Constant.FAILED;
            } else {
                switch (getLoginRes()) {
                    case Constant.SUCCESS: {
                        SharedPreferences.Editor editor = loginInfo.edit();
                        editor.putString(username, password);
                        editor.commit();
                        return Constant.SUCCESS;
                    }
                    default:
                        return Constant.FAILED;
                }
            }

        } else {
            return getLoginRes();
        }
    }

    public int getLoginRes() throws IOException {
        StringBuffer urlbuilder = new StringBuffer();

        String url = urlbuilder.append(Constant.SERVER_URI).append("login?username=").append(username).append("&password=").append(password).toString();
        String res = OkHttpUtil.getResponseBodyString(url);
        Log.d(TAG, url);
        if (res.equals("success"))
            return Constant.SUCCESS;
        else
            return Constant.FAILED;
    }
}
