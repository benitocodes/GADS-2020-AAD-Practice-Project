package com.benardmusau.gads_practise_project_2020.databinding;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.benardmusau.gads_practise_project_2020.BR;
import com.benardmusau.gads_practise_project_2020.model.LearningLeaders;
import com.benardmusau.gads_practise_project_2020.model.SkillIQLeaders;

/***
 * Application was developed by Software Developer Benard Musau for GADS 2020 practice project
 * email: musaubm3@gmail.com
 */

public class ItemDataBinding extends BaseObservable {

	private LearningLeaders mItemLearning;
	private SkillIQLeaders mItemSkill;
	private String classType;

	@Bindable
	public LearningLeaders getItemLearning() {
		return mItemLearning;
	}
	public void setItemLearning(LearningLeaders itemLearning) {
		mItemLearning = itemLearning;
		notifyPropertyChanged(BR.itemLearning);
	}

	@Bindable
	public SkillIQLeaders getItemSkill() {
		return mItemSkill;
	}
	public void setItemSkill(SkillIQLeaders itemSkill) {
		mItemSkill = itemSkill;
		notifyPropertyChanged(BR.itemSkill);
	}

	@Bindable
	public String getClassType() {
		return classType;
	}
	public void setClassType(String classType) {
		this.classType = classType;
		notifyPropertyChanged(BR.classType);
	}
}
