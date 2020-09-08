package com.benardmusau.gads_practise_project_2020.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.benardmusau.gads_practise_project_2020.Constants;
import com.benardmusau.gads_practise_project_2020.R;
import com.benardmusau.gads_practise_project_2020.databinding.ItemDataBinding;
import com.benardmusau.gads_practise_project_2020.databinding.ItemViewBinding;
import com.benardmusau.gads_practise_project_2020.model.LearningLeaders;
import com.benardmusau.gads_practise_project_2020.model.SkillIQLeaders;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/***
 * Application was developed by Software Developer Benard Musau for GADS 2020 practice project
 * email: musaubm3@gmail.com
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {


	private final List mData;


	public RecyclerViewAdapter(List data) {
		mData = data;
		sort();
	}


	@NonNull
	@Override
	public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
		ItemViewBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_view, parent, false);
		return new RecyclerViewHolder(binding.getRoot());
	}

	@Override
	public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {

		Object object = mData.get(position);

		if (holder.mBinding != null) {
			if (object.getClass().equals(LearningLeaders.class)) {
				setLearningLeaders(holder, (LearningLeaders) object);
			} else if (object.getClass().equals(SkillIQLeaders.class)) {
				setSkillIQ(holder, (SkillIQLeaders) object);
			}
		}

	}

	private void setSkillIQ(@NonNull RecyclerViewHolder holder, SkillIQLeaders object) {
		ItemDataBinding itemDataBinding = new ItemDataBinding();
		itemDataBinding.setClassType(Constants.SKILL_TYPE);
		itemDataBinding.setItemLearning(null);
		itemDataBinding.setItemSkill(object);

		assert holder.mBinding != null;
		holder.mBinding.setItemDataBinding(itemDataBinding);
		holder.mBinding.executePendingBindings();
	}

	private void setLearningLeaders(@NonNull RecyclerViewHolder holder, LearningLeaders object) {
		ItemDataBinding itemDataBinding = new ItemDataBinding();
		itemDataBinding.setClassType(Constants.LEARNING_TYPE);
		itemDataBinding.setItemLearning(object);
		itemDataBinding.setItemSkill(null);

		assert holder.mBinding != null;
		holder.mBinding.setItemDataBinding(itemDataBinding);
		holder.mBinding.executePendingBindings();
	}


	private void sort() {
		if (mData.get(0).getClass().equals(LearningLeaders.class)) {
			Collections.sort(mData, new Comparator() {
				@Override
				public int compare(Object o, Object t1) {
					if (((LearningLeaders) o).getHours() <= ((LearningLeaders) t1).getHours())
						return 1;
					return -1;
				}
			});
			notifyDataSetChanged();
		} else if (mData.get(0).getClass().equals(SkillIQLeaders.class)) {
			Collections.sort(mData, new Comparator() {
				@Override
				public int compare(Object o, Object t1) {
					if (((SkillIQLeaders) o).getScore() <= ((SkillIQLeaders) t1).getScore())
						return 1;
					return -1;
				}
			});
			notifyDataSetChanged();
		}


	}

	@Override
	public int getItemCount() {
		return mData.size();
	}

	static class RecyclerViewHolder extends RecyclerView.ViewHolder {

		private final ItemViewBinding mBinding;


		RecyclerViewHolder(@NonNull View itemView) {
			super(itemView);
			mBinding = DataBindingUtil.bind(itemView);
		}
	}
}
