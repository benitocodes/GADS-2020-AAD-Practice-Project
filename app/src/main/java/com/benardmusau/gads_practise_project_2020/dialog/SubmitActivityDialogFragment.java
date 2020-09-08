package com.benardmusau.gads_practise_project_2020.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.databinding.DataBindingUtil;

import com.benardmusau.gads_practise_project_2020.Constants;
import com.benardmusau.gads_practise_project_2020.R;
import com.benardmusau.gads_practise_project_2020.databinding.DialogFragmentDataBinding;
import com.benardmusau.gads_practise_project_2020.databinding.SubmitConfirmationLayoutBinding;
import com.benardmusau.gads_practise_project_2020.databinding.SubmitDialogLayoutBinding;
import com.benardmusau.gads_practise_project_2020.interfaces.SubmitDialogInterface;

import java.util.Objects;

/***
 * Application was developed by Software Developer Benard Musau for GADS 2020 practice project
 * email: musaubm3@gmail.com
 */

public class SubmitActivityDialogFragment extends AppCompatDialogFragment {


	private SubmitDialogInterface mListener;
	private String type;

	public SubmitActivityDialogFragment(String type) {
		this.type = type;
	}

	@NonNull
	@Override
	public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
		LayoutInflater layoutInflater = getActivity().getLayoutInflater();

		if (type == null) {
			SubmitConfirmationLayoutBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.submit_confirmation_layout, null, false);

			binding.setSubmittingDialogInterface(mListener);
			binding.setConfirmationText(getResources().getString(R.string.submissionConfirmation));

			View view = binding.getRoot();
			builder.setView(view);
			builder.setCancelable(false);

		} else {
			SubmitDialogLayoutBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.submit_dialog_layout, null, false);

			DialogFragmentDataBinding dialogFragmentDataBinding = new DialogFragmentDataBinding();
			binding.setDialogFragmentDataBinding(dialogFragmentDataBinding);
			binding.setSubmitDialogInterface(mListener);

			View view = binding.getRoot();
			builder.setView(view);

			if (type.equalsIgnoreCase(Constants.TYPE_SUCCESS)) {
				dialogFragmentDataBinding.setAnimationId(R.raw.check_mark_success_animation);
				dialogFragmentDataBinding.setDialogText(getResources().getString(R.string.submissionSuccess));

			} else if (type.equalsIgnoreCase(Constants.TYPE_FAILURE)) {
				dialogFragmentDataBinding.setAnimationId(R.raw.animation_failure);
				dialogFragmentDataBinding.setDialogText(getResources().getString(R.string.submissionFailure));
			}
		}
		return builder.create();
	}

	@Override
	public void onAttach(@NonNull Context context) {
		super.onAttach(context);
		try {
			mListener = (SubmitDialogInterface) context;
		} catch (Exception e) {
			throw new ClassCastException(context.toString() + " must implement SubmitDialog Listener");
		}
	}
}

