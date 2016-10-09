package com.zmb.skyworth.bochat;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.zmb.skyworth.fragment.ChatFragment;
import com.zmb.skyworth.fragment.ContactFragment;
import com.zmb.skyworth.fragment.PrivateFragment;
import com.zmb.skyworth.fragment.RelativeFragment;

public class MainPage extends Activity implements View.OnClickListener{
private ImageButton setup,chat,contact,relative,myself;
    private Button more;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
initView();

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
    }
private void replaceFragment(Fragment fragment)
{
    FragmentManager mFragmentManager = getFragmentManager();
    FragmentTransaction mFragmentTransaction  = mFragmentManager.beginTransaction();
    mFragmentTransaction.replace(R.id.contantfragment,fragment);
    mFragmentTransaction.commit();
}
    @Override
    public void onClick(View v) {
    switch (v.getId())
    {
        case R.id.chat:
            ChatFragment chatFragment = new ChatFragment();
           replaceFragment(chatFragment);
            break;
        case R.id.contact:
            ContactFragment contactFragment = new ContactFragment();
            replaceFragment(contactFragment);
            break;
        case R.id.myself:
            PrivateFragment privateFragment = new PrivateFragment();
            replaceFragment(privateFragment);
            break;
        case R.id.relative:
            RelativeFragment relativeFragment = new RelativeFragment();
            replaceFragment(relativeFragment);
            break;
        case R.id.setupbtn:
            Intent intent = new Intent(MainPage.this,MainActivity.class);
            startActivity(intent);
            break;
        case R.id.more:
            WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics dm = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(dm);

            mshow(this,dm.widthPixels,dm.heightPixels);
            break;
        default:
            ChatFragment mchatFragment = new ChatFragment();
            replaceFragment(mchatFragment);
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

