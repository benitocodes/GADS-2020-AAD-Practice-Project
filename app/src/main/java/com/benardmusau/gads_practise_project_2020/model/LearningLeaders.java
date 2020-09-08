package com.benardmusau.gads_practise_project_2020.model;

import android.content.Context;

import com.benardmusau.gads_practise_project_2020.R;

/***
 * Application was developed by Software Developer Benard Musau for GADS 2020 practice project
 * email: musaubm3@gmail.com
 */

public class LearningLeaders {

	private String name;
	private int hours;
	private String country;
	private String badgeUrl;



	public LearningLeaders(String name, int hours, String country, String badgeUrl) {
		this.name = name;
		this.hours = hours;
		this.country = country;
		this.badgeUrl = badgeUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getBadgeUrl() {
		return badgeUrl;
	}

	public void setBadgeUrl(String badgeUrl) {
		this.badgeUrl = badgeUrl;
	}

	public String getDescription(Context context) {
		return getHours()+" "+context.getResources().getString(R.string.learning_hours)+", "+getCountry();
	}


}
