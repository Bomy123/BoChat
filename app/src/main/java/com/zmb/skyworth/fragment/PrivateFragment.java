package com.zmb.skyworth.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zmb.skyworth.bochat.R;

/**
 * A simple {@link Fragment} subclass.

 * create an instance of this fragment.
 */
public class PrivateFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View privateFragment = inflater.inflate(R.layout.fragment_private, container,false);
        return privateFragment;
    }
}
