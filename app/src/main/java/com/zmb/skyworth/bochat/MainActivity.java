package com.zmb.skyworth.bochat;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class MainActivity extends Activity
{
	private SlidMenu mMenu;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.sample_slid_menu);
		mMenu = (SlidMenu) findViewById(R.id.id_menu);

	}

	public void toggleMenu(View view)
	{
		mMenu.toggle();
	}
}
