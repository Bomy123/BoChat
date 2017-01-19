package com.zmb.skyworth.fragment;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.zmb.skyworth.bochat.R;
import com.zmb.skyworth.customview.View3;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class RelativeFragment extends  IFragment{
    private FrameLayout ll = null;
    public View3 view3 = null;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View relativeFragment = inflater.inflate(R.layout.fragment_relative,container,false);
        view3 = new View3(RelativeFragment.this.getActivity());
        ll = (FrameLayout) relativeFragment.findViewById(R.id.root_all);
        ll.addView(view3);
        return relativeFragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        System.out.println("1"+view3);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        System.out.println("2"+view3);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("3"+view3);
    }

    @Override
    public void onStart() {
        super.onStart();
        System.out.println("4"+view3);
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("5"+view3);
    }

    @Override
    public void onPause() {
        super.onPause();
        System.out.println("6"+view3);
    }

    @Override
    public void onStop() {
        super.onStop();
        System.out.println("7"+view3);
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
            if(view3 != null) {
                ll.removeView(view3);
                view3 = new View3(RelativeFragment.this.getActivity());
                ll.addView(view3);
                flag = true;
                System.out.println(hidden+"  "+view3);
                //call();
            }

        }
    }

    @Override
    public void call()
    {
        //view3.Callback();
        view3.Callback();
    }
}
