package com.benardmusau.gads_practise_project_2020;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Pair;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.viewbinding.BuildConfig;


import com.benardmusau.gads_practise_project_2020.databinding.ActivityHomeBinding;

/***
 * Application was developed by Software Developer Benard Musau for GADS 2020 practice project
 * email: musaubm3@gmail.com
 */

public class SplashScreen extends AppCompatActivity {

	private Handler mHandler;
	private ActivityHomeBinding mBinding;

	private static void setTransparentForWindow(Activity activity) {
		Window window = activity.getWindow();
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			window.setStatusBarColor(Color.TRANSPARENT);
			window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
		} else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mBinding = DataBindingUtil.setContentView(this,R.layout.activity_home);
		setTransparentForWindow(this);

		mHandler = new Handler();
		enableStrictMode();
		setUpAnimations();
		changeActivity();
	}

	private void enableStrictMode() {

		if (BuildConfig.DEBUG) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build();
			StrictMode.setThreadPolicy(policy);

		}
	}

	private void changeActivity() {
		long TOTAL_TIME = 4000;
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				Intent intent = new Intent(SplashScreen.this, MainActivity.class);

				Pair pair= new Pair<View, String>(mBinding.gadsLogo, getResources().getString(R.string.gads));

				ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(SplashScreen.this, pair);
				startActivity(intent, activityOptions.toBundle());
				finish();
			}
		}, TOTAL_TIME);
	}

	public void setUpAnimations() {
		Animator gadsAnimation = AnimatorInflater.loadAnimator(this, R.animator.animation);
		gadsAnimation.setTarget(mBinding.gadsLogo);
		gadsAnimation.start();
	}

}
