package com.zmb.skyworth.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.zmb.skyworth.bochat.MainPage;
import com.zmb.skyworth.bochat.R;
import com.zmb.skyworth.customview.View1;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class ChatFragment extends  IFragment{
    private FrameLayout ll = null;
    public View1 view1 = null;
    WindowManager wm = null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View chatFragment = inflater.inflate(R.layout.fragment_chat, container, false);
        view1 = new View1((MainPage)getActivity());
        wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        view1.setLayoutParams(new ViewGroup.LayoutParams(wm.getDefaultDisplay().getWidth(), wm.getDefaultDisplay().getWidth()));
        view1.setBackgroundColor(Color.BLUE);
        ll = (FrameLayout) chatFragment.findViewById(R.id.root_all);
        ll.addView(view1);
        return chatFragment;
    }

    @Override
    public void onResume() {
        super.onResume();

    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(hidden)
        {
            flag = false;
            System.out.println("pause");

        }
        else
        {
            if(view1 != null) {
                ll.removeView(view1);
                view1 = new View1((MainPage)getActivity());
                view1.setLayoutParams(new ViewGroup.LayoutParams(wm.getDefaultDisplay().getWidth(), wm.getDefaultDisplay().getWidth()));
                view1.setBackgroundColor(Color.BLUE);
                ll.addView(view1);
                flag = true;
                System.out.println(hidden+"  "+view1);
                //call();
            }

        }
    }

    @Override
    public void call()
    {
//        view1.Callback();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while(flag&&once) {
//                    try {
//
//                        for (int i = 10; i < 500; i++) {
//                            if(flag&&once) {
//                                Thread.sleep(2);
//                                view1.setR(i);
//                            }
//                            else break;
//                        }
//                        for (int i = 500; i > 100; i--) {
//                            if(flag&&once) {
//                                Thread.sleep(2);
//                                view1.setR(i);
//                            }
//                            else break;
//
//                        }
//                        for (int x = 500; x < 1000; x++) {
//                            if(flag&&once) {
//                                Thread.sleep(2);
//                                view1.setX(x);
//                            }
//                            else break;
//                        }
//                        for (int y = 500; y < 1000; y++) {
//                            if(flag&&once) {
//                                Thread.sleep(2);
//                                view1.setY(y);
//                            }
//                            else break;
//                        }
//                        for (int y = 1000, x = 1000; y > 100 && x > 100; x--, y--) {
//                            if(flag&&once) {
//                                Thread.sleep(2);
//                                view1.setX(x);
//                                view1.setY(y);
//                            }
//                            else break;
//                        }
//                        if(flag) {
//                            once = false;
//                        }
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();
        view1.Release();
    }


}
