package com.itba.hci.smarthome.view.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.itba.hci.smarthome.R;
import com.itba.hci.smarthome.model.entities.Routine;
import com.itba.hci.smarthome.model.viewModel.RoutinesViewModel;
import com.itba.hci.smarthome.model.viewModel.SmartHomeViewModel;
import com.itba.hci.smarthome.view.util.RoutinesAdapter;
import com.itba.hci.smarthome.view.fragmentView.ClickListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;

public class RoutinesFragment extends SmartHomeFragment implements ClickListener {

    RoutinesAdapter routinesAdapter = new RoutinesAdapter(this);

    @BindView(R.id.recycler_routines)
    RecyclerView routines;

    RoutinesViewModel routinesViewModel;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        routinesViewModel = ViewModelProviders.of(this).get(RoutinesViewModel.class);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected List<SmartHomeViewModel> getViewModels() {
        return new ArrayList<SmartHomeViewModel>(Collections.singletonList(routinesViewModel));
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        routines.setLayoutManager(new LinearLayoutManager(getContext()));
        routines.setAdapter(routinesAdapter);
    }

    public void onResume() {
        super.onResume();
        routinesViewModel.getAllRoutines().
                observe(this, new Observer<List<Routine>>() {
                    @Override
                    public void onChanged(@Nullable List<Routine> routines) {
                        routinesAdapter.setList(routines);
                        routinesAdapter.notifyDataSetChanged();
                    }
                });
    }


    @Override
    protected int getViewId() {
        return R.layout.fragment_routines;
    }

    @Override
    public void onClick(int item, String idItemClicked) {
        routinesViewModel.executeRoutine(idItemClicked).observe(this, new Observer<List<Boolean>>() {
            @Override
            public void onChanged(@Nullable List<Boolean> booleans) {
                if(booleans != null) {
                    int i = 0;
                    for (Boolean b : booleans) {
                        if (b) {
                            i++;
                        }
                    }
                    if (i ==0)
                        showToastError(getResources().getString(R.string.actions_cant_execute));
                    else if (i == 1)
                        showToastError(i + " " + getResources().getString(R.string.one_action_executed));
                    else
                        showToastError(i + " " + getResources().getString(R.string.actions_executed));
                }
            }
        });
    }
}
