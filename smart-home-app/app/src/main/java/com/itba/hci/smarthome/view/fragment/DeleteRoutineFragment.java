package com.itba.hci.smarthome.view.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.itba.hci.smarthome.R;
import com.itba.hci.smarthome.model.viewModel.DeleteRoutineViewModel;
import com.itba.hci.smarthome.model.viewModel.SmartHomeViewModel;
import com.itba.hci.smarthome.view.Navigator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.OnClick;

public class DeleteRoutineFragment extends SmartHomeFragment{

    private String routineId;

    @Inject
    Navigator navigator;

    DeleteRoutineViewModel deleteRoutineViewModel;

    public static Fragment newInstance(String routineId) {
        DeleteRoutineFragment fragment = new DeleteRoutineFragment();
        fragment.setRoutineId(routineId);
        return fragment;
    }

    public void setRoutineId(String routineId) {
        this.routineId = routineId;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        deleteRoutineViewModel = ViewModelProviders.of(this).get(DeleteRoutineViewModel.class);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected List<SmartHomeViewModel> getViewModels() {
        return new ArrayList<SmartHomeViewModel>(Collections.singletonList(deleteRoutineViewModel));
    }

    @Override
    protected int getViewId() {
        return R.layout.fragment_delete_routine;
    }

    @OnClick(R.id.no_button)
    public void onNoDeleteRoutineCancelClick(){
        navigator.showRoutinesActivity(this);
    }

    @OnClick(R.id.yes_button)
    public void onYesDeleteRoutineAcceptClick(){
        deleteRoutineViewModel.deleteRoutine(routineId).observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean result) {
                if (result != null && result) {
                    showToastError(getResources().getString(R.string.routine_delete_ok));
                } else {
                    showToastError(getResources().getString(R.string.error));
                }
            }
        });
        navigator.showRoutinesActivity(this);
    }
}

