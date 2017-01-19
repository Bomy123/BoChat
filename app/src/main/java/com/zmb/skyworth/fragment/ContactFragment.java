package com.zmb.skyworth.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.zmb.skyworth.bochat.R;
import com.zmb.skyworth.customview.View2;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * create an instance of this fragment.
 */
public class ContactFragment extends  IFragment{
    private FrameLayout ll = null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contactFRagment = inflater.inflate(R.layout.fragment_contact,container,false);
        ll = (FrameLayout) contactFRagment.findViewById(R.id.root_all);
        ll.addView(new View2(getActivity()));
        return contactFRagment;
    }

    @Override
    public void call() {

    }
}
