package com.benardmusau.gads_practise_project_2020.databinding;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.benardmusau.gads_practise_project_2020.BR;

/***
 * Application was developed by Software Developer Benard Musau for GADS 2020 practice project
 * email: musaubm3@gmail.com
 */

public class DialogFragmentDataBinding extends BaseObservable {


	private String dialogText;
	private int animationId;


	@Bindable
	public String getDialogText() {
		return dialogText;
	}

	public void setDialogText(String dialogText) {
		this.dialogText = dialogText;
		notifyPropertyChanged(BR.dialogText);
	}

	@Bindable
	public int getAnimationId() {
		return animationId;
	}

	public void setAnimationId(int animationId) {
		this.animationId = animationId;
		notifyPropertyChanged(BR.animationId);
	}
}
