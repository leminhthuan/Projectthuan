package com.miosys.finder.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class SplashScreen extends Activity{
private final int SPLASH_DISPLAY_LENGTH = 1000;
//	private String mClassToLaunch;
//	private String mClassToLaunchPackage;
	
	/*-- Called when the activity is first created. --*/
	@Override
	public void onCreate(Bundle icicle){
		super.onCreate(icicle);
		setContentView(R.layout.splash_screen);
		    
		/*
		 * New handler to start the Menu-Activity and close thi Splash-Screen
		 * after some seconds
		 * */
		
		new Handler().postDelayed(new Runnable(){
			@Override
			public void run(){
				/*- Create an Intent that will start the Menu-Activity. */
				Intent mainIntent = new Intent(SplashScreen.this, PfinderTargets.class);
				SplashScreen.this.startActivity(mainIntent);
				SplashScreen.this.finish();
			}
		}, SPLASH_DISPLAY_LENGTH);
	}
	
}
