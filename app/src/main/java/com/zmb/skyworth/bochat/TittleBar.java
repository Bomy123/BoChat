package com.zmb.skyworth.bochat;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

public class TittleBar extends LinearLayout {

    public TittleBar(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.activity_tittle_bar,null);
    }


}
