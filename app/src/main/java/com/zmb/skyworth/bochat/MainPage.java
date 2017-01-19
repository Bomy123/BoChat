package com.zmb.skyworth.bochat;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.zmb.skyworth.fragment.ChatFragment;
import com.zmb.skyworth.fragment.ContactFragment;
import com.zmb.skyworth.fragment.IFragment;
import com.zmb.skyworth.fragment.PrivateFragment;
import com.zmb.skyworth.fragment.RelativeFragment;

import java.util.ArrayList;
import java.util.List;

public class MainPage extends Activity implements View.OnClickListener{
private ImageButton setup,chat,contact,relative,myself;
    private Button more;
private List<IFragment> fragmentList = null;
    ContactFragment contactFragment = null;
    ChatFragment chatFragment = null;
    PrivateFragment privateFragment = null;
    RelativeFragment relativeFragment = null;
    FragmentTransaction transaction = null;
    IFragment upFragment = null;
    public static final int CHATF = 0;
    public static final int CONTF = 1;
    public static final int PRIVF = 2;
    public static final int RELAF = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null) {
            setContentView(R.layout.activity_main_page);
            initView();
        }

//        ChatFragment chatFragment = new ChatFragment();
//        replaceFragment(chatFragment);
//        chatFragment.call();
    }
    private void initView()
    {
        setup= (ImageButton) findViewById(R.id.setupbtn);
        chat= (ImageButton) findViewById(R.id.chat);
        contact= (ImageButton) findViewById(R.id.contact);
        relative= (ImageButton) findViewById(R.id.relative);
        myself= (ImageButton) findViewById(R.id.myself);
        more = (Button) findViewById(R.id.more);
        setup.setOnClickListener(this);
        chat.setOnClickListener(this);
        more.setOnClickListener(this);
        contact.setOnClickListener(this);
        relative.setOnClickListener(this);
        myself.setOnClickListener(this);
        initFragment();
    }
//private void replaceFragment(Fragment fragment)
//{
//    FragmentManager mFragmentManager = getFragmentManager();
//    FragmentTransaction mFragmentTransaction  = mFragmentManager.beginTransaction();
//    mFragmentTransaction.add(R.id.contantfragment,fragment);
//    mFragmentTransaction.replace(R.id.contantfragment,fragment);
//    mFragmentTransaction.commit();
//}
    public void hideAndShowFragment(IFragment fragment)
    {
        transaction = getFragmentManager().beginTransaction();
        if (!fragmentList.contains(fragment))
        {
            fragmentList.add(fragment);
            transaction.add(R.id.contantfragment,fragment);
        }

        if(upFragment != null)
        {
            upFragment.flag = false;
            transaction.hide(upFragment);
        }
        transaction.show(fragment);
        transaction.commit();
        fragment.flag = true;
        upFragment = fragment;
    }

    public void initFragment()
    {

        fragmentList = new ArrayList<IFragment>();
//        if(chatFragment == null)
//        {
            chatFragment = new ChatFragment();
//        }
//        if(!fragmentList.contains(chatFragment))
//        {
//            fragmentList.add(chatFragment);
//            transaction.add(R.id.contantfragment,chatFragment);
//        }
//
//        if(contactFragment == null)
//        {
            contactFragment = new ContactFragment();
//        }
//        if(fragmentList.contains(contactFragment)) {
//            fragmentList.add(contactFragment);
//            transaction.add(R.id.contantfragment,contactFragment);
//        }
//        if(relativeFragment == null)
//        {
            relativeFragment = new RelativeFragment();
//        }
//        if (!fragmentList.contains(relativeFragment)) {
//            fragmentList.add(relativeFragment);
//            transaction.add(R.id.contantfragment,relativeFragment);
//        }
//        if(privateFragment == null)
//        {
            privateFragment = new PrivateFragment();
//        }
//        if (!fragmentList.contains(privateFragment))
//        {
//            fragmentList.add(privateFragment);
//            transaction.add(R.id.contantfragment,privateFragment);
//        }
//
//

    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("destroy");
    }

    @Override
    public void onClick(View v) {
    switch (v.getId())
    {
        case R.id.chat:
            hideAndShowFragment(chatFragment);
            break;
        case R.id.contact:
            hideAndShowFragment(contactFragment);
            break;
        case R.id.myself:
            hideAndShowFragment(privateFragment);
            break;
        case R.id.relative:
            hideAndShowFragment(relativeFragment);
            break;
        case R.id.setupbtn:
            Intent intent = new Intent(MainPage.this,MainActivity.class);
            startActivity(intent);
            break;
        case R.id.more:
            //WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics dm = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(dm);

            mshow(this,dm.widthPixels,dm.heightPixels);
            break;
        default:
            hideAndShowFragment(chatFragment);
            break;

    }
        Toast.makeText(MainPage.this,"您点击了"+v.getTag(),Toast.LENGTH_SHORT).show();
    }
    public void mshow(Context context , int X , int Y)
    {
        mDialog  mdialog=new mDialog(context,R.style.dialog);
        Window win = mdialog.getWindow();

        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.x = X/2-20-115;
        Log.e("x",String.valueOf(Y));
        params.y =-Y/4 ;
        win.setAttributes(params);
        mdialog.setCanceledOnTouchOutside(true);
        mdialog.show();
    }
    class mDialog extends AlertDialog
        {
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.more_dialog);
            }

            protected mDialog(Context context, int themeResId) {
                super(context, themeResId);
            }


    }


}

