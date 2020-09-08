package com.benardmusau.gads_practise_project_2020.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import androidx.viewpager.widget.PagerAdapter;

import com.benardmusau.gads_practise_project_2020.R;
import com.benardmusau.gads_practise_project_2020.databinding.ViewPagerDataBinding;

import com.benardmusau.gads_practise_project_2020.databinding.ViewpagerItemBinding;
import com.benardmusau.gads_practise_project_2020.model.LearningLeaders;
import com.benardmusau.gads_practise_project_2020.model.SkillIQLeaders;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/***
 * Application was developed by Software Developer Benard Musau for GADS 2020 practice project
 * email: musaubm3@gmail.com
 */

public class ViewPagerAdapter extends PagerAdapter {


	private LayoutInflater layoutInflater;
	private String[] mTitles;
	private List<LearningLeaders> mLearningLeaders;
	private List<SkillIQLeaders> mSkillIQLeaders;

	public ViewPagerAdapter(Context context, String[] titles, List<LearningLeaders> learningLeaders, List<SkillIQLeaders> skillIQLeaders) {
		this.mTitles = titles;
		this.mLearningLeaders = learningLeaders;
		this.mSkillIQLeaders = skillIQLeaders;
		layoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return mTitles.length;
	}

	@Override
	public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
		return view == object;
	}

	@NotNull
	public Object instantiateItem(@NonNull ViewGroup container, int position) {
		ViewpagerItemBinding itemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.viewpager_item, null, false);

		ViewPagerDataBinding viewPagerDataBinding = new ViewPagerDataBinding();
		viewPagerDataBinding.setLearningList(mLearningLeaders);
		viewPagerDataBinding.setSkillList(mSkillIQLeaders);
		viewPagerDataBinding.setPosition(position);
		itemBinding.setViewPagerDataBinding(viewPagerDataBinding);

		container.addView(itemBinding.getRoot());
		return itemBinding.getRoot();
	}

	@Override
	public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
		container.removeView((View) object);
	}

	@Nullable
	@Override
	public CharSequence getPageTitle(int position) {
		return mTitles[position];
	}


	public void update(List<LearningLeaders> learningLeaders, List<SkillIQLeaders> skillIQLeaders) {
		mLearningLeaders = learningLeaders;
		mSkillIQLeaders = skillIQLeaders;
		notifyDataSetChanged();
	}
}
