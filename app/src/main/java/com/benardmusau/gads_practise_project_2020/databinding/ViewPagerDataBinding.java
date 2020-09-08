package com.benardmusau.gads_practise_project_2020.databinding;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.benardmusau.gads_practise_project_2020.BR;
import com.benardmusau.gads_practise_project_2020.model.LearningLeaders;
import com.benardmusau.gads_practise_project_2020.model.SkillIQLeaders;

import java.util.List;

/***
 * Application was developed by Software Developer Benard Musau for GADS 2020 practice project
 * email: musaubm3@gmail.com
 */

public class ViewPagerDataBinding extends BaseObservable {

	private List<LearningLeaders> mLearningList;
	private List<SkillIQLeaders> mSkillList;
	private int position;


	@Bindable
	public List<LearningLeaders> getLearningList() {
		return mLearningList;
	}
	public void setLearningList(List<LearningLeaders> learningList) {
		mLearningList = learningList;
		notifyPropertyChanged(BR.learningList);
	}

	@Bindable
	public List<SkillIQLeaders> getSkillList() {
		return mSkillList;
	}
	public void setSkillList(List<SkillIQLeaders> skillList) {
		mSkillList = skillList;
		notifyPropertyChanged(BR.skillList);
	}

	@Bindable
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
		notifyPropertyChanged(BR.position);
	}
}
