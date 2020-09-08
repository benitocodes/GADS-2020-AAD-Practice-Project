package com.benardmusau.gads_practise_project_2020.interfaces;

import com.benardmusau.gads_practise_project_2020.model.LearningLeaders;
import com.benardmusau.gads_practise_project_2020.model.SkillIQLeaders;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

import static com.benardmusau.gads_practise_project_2020.Constants.EMAIL_ENTRY_ID;
import static com.benardmusau.gads_practise_project_2020.Constants.GITHUB_LINK_ENTRY_ID;
import static com.benardmusau.gads_practise_project_2020.Constants.LAST_NAME_ENTRY_ID;
import static com.benardmusau.gads_practise_project_2020.Constants.LEARNING_LEADERS_GET_URL;
import static com.benardmusau.gads_practise_project_2020.Constants.NAME_ENTRY_ID;
import static com.benardmusau.gads_practise_project_2020.Constants.SKILL_IQ_GET_URL;
import static com.benardmusau.gads_practise_project_2020.Constants.SUBMIT_POST_URL;

/***
 * Application was developed by Software Developer Benard Musau for GADS 2020 practice project
 * email: musaubm3@gmail.com
 */

public interface Api {

	@GET(LEARNING_LEADERS_GET_URL)
	Call<List<LearningLeaders>> getLearningLeaders();

	@GET(SKILL_IQ_GET_URL)
	Call<List<SkillIQLeaders>> getSkillIQLeaders();

	@POST(SUBMIT_POST_URL)
	@FormUrlEncoded
	Call<Void> submitProject(@Field(EMAIL_ENTRY_ID) String email, @Field(NAME_ENTRY_ID) String name, @Field(LAST_NAME_ENTRY_ID) String lastName, @Field(GITHUB_LINK_ENTRY_ID) String gitHubLink);


}
