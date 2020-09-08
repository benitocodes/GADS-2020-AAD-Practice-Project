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

public class ActivityMainDataBinding extends BaseObservable {

	private boolean downloaded;
	private boolean show;
	private List<LearningLeaders> mLearningLeaders;
	private List<SkillIQLeaders> mSkillIQLeaders;

	@Bindable
	public List<LearningLeaders> getLearningLeaders() {
		return mLearningLeaders;
	}
	public void setLearningLeaders(List<LearningLeaders> learningLeaders) {
		mLearningLeaders = learningLeaders;
		notifyPropertyChanged(BR.learningLeaders);
	}

	@Bindable
	public List<SkillIQLeaders> getSkillIQLeaders() {
		return mSkillIQLeaders;
	}
	public void setSkillIQLeaders(List<SkillIQLeaders> skillIQLeaders) {
		mSkillIQLeaders = skillIQLeaders;
		notifyPropertyChanged(BR.skillIQLeaders);
	}

	@Bindable
	public boolean isDownloaded() {
		return downloaded;
	}
	public void setDownloaded(boolean downloaded) {
		this.downloaded = downloaded;
		notifyPropertyChanged(BR.downloaded);
	}

	@Bindable
	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
		notifyPropertyChanged(BR.show);
	}
}
