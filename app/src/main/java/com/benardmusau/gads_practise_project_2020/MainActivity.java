package com.benardmusau.gads_practise_project_2020;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;


import com.benardmusau.gads_practise_project_2020.databinding.ActivityMainBinding;
import com.benardmusau.gads_practise_project_2020.databinding.ActivityMainDataBinding;
import com.benardmusau.gads_practise_project_2020.interfaces.Api;
import com.benardmusau.gads_practise_project_2020.interfaces.UpdateUserInterface;
import com.benardmusau.gads_practise_project_2020.model.ApiBuilder;
import com.benardmusau.gads_practise_project_2020.model.LearningLeaders;
import com.benardmusau.gads_practise_project_2020.model.SkillIQLeaders;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/***
 * Application was developed by Software Developer Benard Musau for GADS 2020 practice project
 * email: musaubm3@gmail.com
 */

public class MainActivity extends AppCompatActivity implements UpdateUserInterface {


	boolean mSkillFetched;
	boolean mLearningLeadersFetched;
	long CURRENT_TIME = 0;
	private List<LearningLeaders> mLearningLeaders;
	private List<SkillIQLeaders> mSkillIQLeaders;
	private ActivityMainBinding mBinding;
	private ActivityMainDataBinding mActivityMainDataBinding;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

		setTransparentBackground(this);


		mBinding.setUpdateui(this);
		mSkillFetched = false;
		mLearningLeadersFetched = false;
		mActivityMainDataBinding = new ActivityMainDataBinding();
		mBinding.setDataview(mActivityMainDataBinding);

		getData(this);

	}

	private void setTransparentBackground(Activity activity) {
		Window window = activity.getWindow();
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			window.setStatusBarColor(Color.TRANSPARENT);
			window.setNavigationBarColor(Color.TRANSPARENT);
			window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
		} else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		}
	}


	public void updateUi() {
		if (mSkillFetched && mLearningLeadersFetched) {
			mActivityMainDataBinding.setDownloaded(true);
			mActivityMainDataBinding.setShow(true);
			mActivityMainDataBinding.setLearningLeaders(mLearningLeaders);
			mActivityMainDataBinding.setSkillIQLeaders(mSkillIQLeaders);
			mBinding.tab.setupWithViewPager(mBinding.viewpager);
		}
	}

	private boolean isConnected(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo wifiNetwork = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		NetworkInfo mobileNetwork = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

		if ((wifiNetwork != null && wifiNetwork.isConnected()) || (mobileNetwork != null && mobileNetwork.isConnected())) {
			return true;
		} else return false;


	}

	/**
	 *
	 * @param context check internet connecting if not available disable the viewpager, progressbar and display group
	 */
	public void getData(Context context) {

		if (isConnected(context)) {
			Api api = ApiBuilder.buildService(Api.class);
			Call<List<LearningLeaders>> learningLeadersCall = api.getLearningLeaders();
			Call<List<SkillIQLeaders>> skillIQCall = api.getSkillIQLeaders();
			learningLeadersCall.enqueue(new Callback<List<LearningLeaders>>() {
				@Override
				public void onResponse(Call<List<LearningLeaders>> call, Response<List<LearningLeaders>> response) {
					mLearningLeaders = response.body();
					mLearningLeadersFetched = true;
					updateUi();
				}


				@Override
				public void onFailure(Call<List<LearningLeaders>> call, Throwable t) {
					mActivityMainDataBinding.setDownloaded(false);
					mActivityMainDataBinding.setShow(true);
					mBinding.txtError.setText(R.string.oops_developer);
					Toast.makeText(MainActivity.this, "error on fetching learning leaders", Toast.LENGTH_SHORT).show();
				}
			});
			skillIQCall.enqueue(new Callback<List<SkillIQLeaders>>() {
				@Override
				public void onResponse(Call<List<SkillIQLeaders>> call, Response<List<SkillIQLeaders>> response) {
					mSkillIQLeaders = response.body();
					mSkillFetched = true;
					updateUi();
				}

				@Override
				public void onFailure(Call<List<SkillIQLeaders>> call, Throwable t) {
					mActivityMainDataBinding.setDownloaded(false);
					mActivityMainDataBinding.setShow(true);
					mBinding.txtError.setText(R.string.oops_developer);
					Toast.makeText(MainActivity.this, "error on fetching skill iq", Toast.LENGTH_SHORT).show();
				}
			});
		} else {
			mActivityMainDataBinding.setDownloaded(false);
			mActivityMainDataBinding.setShow(true);
			mBinding.txtError.setText(R.string.network_error);
		}
	}

	@Override
	public void submit() {
		Intent intent = new Intent(MainActivity.this, SubmitActivity.class);
		startActivity(intent);
	}

	@Override
	public void refresh() {
		mActivityMainDataBinding.setShow(false);
		getData(MainActivity.this);
	}

	@Override
	public void onBackPressed() {
		if (CURRENT_TIME == 0) {
			CURRENT_TIME = System.currentTimeMillis();
			Toast.makeText(this, R.string.exit, Toast.LENGTH_SHORT).show();
		} else if (SystemClock.elapsedRealtime() <= CURRENT_TIME + 4000) {
			CURRENT_TIME = 0;
			super.onBackPressed();
		}
	}
}
