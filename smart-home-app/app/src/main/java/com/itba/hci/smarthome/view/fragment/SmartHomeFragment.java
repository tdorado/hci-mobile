package com.itba.hci.smarthome.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import com.itba.hci.smarthome.R;
import com.itba.hci.smarthome.core.SmartHomeApplication;
import com.itba.hci.smarthome.dagger.components.SmartHomeComponents;
import com.itba.hci.smarthome.model.viewModel.SmartHomeViewModel;
import com.itba.hci.smarthome.view.fragmentView.View;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;

public abstract class SmartHomeFragment extends Fragment implements View {

    @Inject
    SmartHomeApplication smartHomeApplication;
    private boolean isTablet;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeViewModels(getViewModels());
        isTablet = getResources().getBoolean(R.bool.is_tablet);
    }

    public boolean isTablet(){
        return isTablet;
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public android.view.View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        android.view.View view = inflater.inflate(getViewId(), container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    protected void initializeViewModels(List<SmartHomeViewModel> viewModels) {
        if (viewModels == null || viewModels.size() == 0)
            return;
        for (SmartHomeViewModel p : viewModels) {
            p.initialize(smartHomeApplication.getComponent());
        }
    }

    protected abstract List<SmartHomeViewModel> getViewModels();

    protected SmartHomeComponents getComponent(){
        return ((SmartHomeApplication) getActivity().getApplication()).getComponent();
    }

    protected abstract int getViewId();

    public void showToastError(String error) {
        if(getContext()!=null) {
            Toast toast = Toast.makeText(getContext(), error, Toast.LENGTH_SHORT);
            toast.show();
        }
    }


}
