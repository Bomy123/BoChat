package com.zmb.skyworth.fragment;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.zmb.skyworth.bochat.MainPage;
import com.zmb.skyworth.bochat.R;
import com.zmb.skyworth.customview.MyProgress;
import com.zmb.skyworth.customview.View4;

/**
 * A simple {@link Fragment} subclass.

 * create an instance of this fragment.
 */
public class PrivateFragment extends  IFragment{
    private FrameLayout ll = null;
    private View4 view4 = null;
    private MyProgress myProgress= null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View privateFragment = inflater.inflate(R.layout.fragment_private, container,false);
        privateFragment.setLayerType(View.LAYER_TYPE_SOFTWARE,null);
        view4 = new View4(getActivity());
        myProgress = new MyProgress(PrivateFragment.this.getActivity());
        System.out.println(PrivateFragment.this.getActivity());
        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        myProgress.setLayoutParams(new ViewGroup.LayoutParams(wm.getDefaultDisplay().getWidth(), wm.getDefaultDisplay().getWidth()));
        ll = (FrameLayout) privateFragment.findViewById(R.id.root_all);
        ll.addView(view4);
        ll.addView(myProgress);
        return privateFragment;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        System.out.println("1"+myProgress);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        System.out.println("2"+myProgress);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("3"+myProgress);
    }

    @Override
    public void onStart() {
        super.onStart();
        System.out.println("4"+myProgress);

    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("5"+myProgress);
        //call();

    }

    @Override
    public void onPause() {
        super.onPause();
        System.out.println("6"+myProgress);
    }

    @Override
    public void onStop() {
        super.onStop();
        System.out.println("7"+myProgress);
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
            if(view4 != null) {
                ll.removeView(view4);
                view4 = new View4((MainPage)getActivity());
                ll.addView(view4);
                flag = true;
                System.out.println(hidden+"  "+view4);
                call();
            }

        }
    }
    @Override
    public void call()
    {
        view4.Release();
        new Thread(new Runnable() {
            @Override
            public void run() {
                    if (myProgress == null) {
                        System.out.println("myProgress null");
                    } else {
                        myProgress.setSize(1300, 15);
                        myProgress.Callback();
                    }
            }
        }).start();


    }

//    private FrameLayout ll = null;
//    public static View1 view1 = null;
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View privateFragment = inflater.inflate(R.layout.fragment_private, container, false);
//        view1 = new View1(PrivateFragment.this.getActivity());
//        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
//        view1.setLayoutParams(new ViewGroup.LayoutParams(wm.getDefaultDisplay().getWidth(), wm.getDefaultDisplay().getWidth()));
//        view1.setBackgroundColor(Color.BLUE);
//        ll = (FrameLayout) privateFragment.findViewById(R.id.root_all);
//        ll.addView(view1);
//
//        return privateFragment;
//    }
//    public void call()
//    {
//        view1.Callback();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    for (int i = 10; i < 500; i++) {
//                        Thread.sleep(2);
//                        view1.setR(i);
//                    }
//                    for (int i = 500; i > 100; i--) {
//                        Thread.sleep(2);
//                        view1.setR(i);
//
//                    }
//                    for (int x = 500; x < 1000; x++) {
//                        Thread.sleep(2);
//                        view1.setX(x);
//                    }
//                    for (int y = 500; y < 1000; y++) {
//                        Thread.sleep(2);
//                        view1.setY(y);
//                    }
//                    for (int y = 1000,x=1000; y >100 && x>100; x--,y--) {
//                        Thread.sleep(2);
//                        view1.setX(x);
//                        view1.setY(y);
//                    }
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//    }
}
