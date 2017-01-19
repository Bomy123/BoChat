package com.zmb.skyworth.Controller.user;

import com.zmb.skyworth.bean.User;
import com.zmb.skyworth.config.Constant;
import com.zmb.skyworth.util.JsonUtil;
import com.zmb.skyworth.util.OkHttpUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangmingbao on 16-12-19.
 */

public class RegisterControll {
    private User user = null;
    private List<User> userlist = null;
    private JsonUtil jsonUtil = null;
    String res = null;

    public RegisterControll(User user) {
        this.user = user;
    }

    public int tryRegister() {
        if (user.getUsername() == null) {
            return Constant.NAME_NULL;
        } else if (user.getPassword() == null) {
            return Constant.PWD_NULL;
        } else {
            res = getRegisterRes();
            System.out.println("res:"+res);
            if (res.equals("success"))
                return Constant.SUCCESS;
            else if (res.equals("failed"))
                return Constant.FAILED;
            else if (res.equals("exists"))
                return Constant.EXISTS;
            else
                return Constant.ERROR;
        }
    }

    private String getRegisterRes() {
        userlist = new ArrayList<>();
        userlist.add(user);
        jsonUtil = new JsonUtil();
        try {
            return OkHttpUtil.postJsonRequest(jsonUtil.getJson(userlist), Constant.SERVER_URI + "register");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
