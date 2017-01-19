package com.zmb.skyworth.fragment;

import android.app.Fragment;

/**
 * Created by zhangmingbao on 17-1-16.
 */

public abstract class IFragment extends Fragment{
   public abstract void call();
   public boolean flag = true;
   public boolean once = true;
}
