package com.itba.hci.smarthome.view.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.itba.hci.smarthome.R;
import com.itba.hci.smarthome.model.entities.BlindsState;
import com.itba.hci.smarthome.model.entities.Device;
import com.itba.hci.smarthome.model.request.DeviceRequest;
import com.itba.hci.smarthome.model.viewModel.BlindsViewModel;
import com.itba.hci.smarthome.model.viewModel.EditDeviceViewModel;
import com.itba.hci.smarthome.model.viewModel.SmartHomeViewModel;
import com.itba.hci.smarthome.view.Navigator;
import com.itba.hci.smarthome.view.util.CommonUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class BlindsFragment extends SmartHomeFragment {

    private String deviceId;
    private BlindsState blindsState;

    @Inject
    Navigator navigator;

    @BindView(R.id.blinds_status)
    TextView blindStatusText;

    @BindView(R.id.blinds_level)
    TextView blindsLevelText;

    @BindView(R.id.actions_spinner)
    Spinner actionSpinner;

    BlindsViewModel blindsViewModel;

    public static Fragment newInstance(String deviceId) {
        BlindsFragment fragment = new BlindsFragment();
        fragment.setDeviceId(deviceId);
        return fragment;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        blindsViewModel = ViewModelProviders.of(this).get(BlindsViewModel.class);
        super.onCreate(savedInstanceState);
        blindsViewModel.getBlindsState(deviceId).observe(this, new Observer<BlindsState>() {
            @Override
            public void onChanged(@Nullable BlindsState b) {
                if(b != null) {
                    blindsState = b;
                    blindStatusText.setText(getString(CommonUtils.getIdResourceBlinds(b.getStatus())));
                    blindsLevelText.setText(blindsState.getLevel().toString());
                }
            }
        });
    }

    @Override
    protected List<SmartHomeViewModel> getViewModels() {
        return new ArrayList<SmartHomeViewModel>(Collections.singletonList(blindsViewModel));
    }

    @Override
    protected int getViewId() {
        return R.layout.fragment_blinds;
    }

    @OnClick(R.id.button_cancel)
    public void onCancelClick(){
        navigator.showDevicesActivity(this);
    }

    @OnClick(R.id.button_accept)
    public void onEditDeviceAcceptClick(){
        if(actionSpinner.getSelectedItemPosition() == 1){
            blindsViewModel.openBlinds(deviceId).observe(this, new Observer<Boolean>() {
                @Override
                public void onChanged(@Nullable Boolean aBoolean) {
                    if(aBoolean != null && aBoolean){
                        showToastError(getString(R.string.opening_blinds));
                    }
                    else if(aBoolean != null){
                        showToastError(getString(R.string.cant_open_blinds));
                    }
                    else{
                        showToastError(getString(R.string.error));
                    }
                }
            });
        }
        else if(actionSpinner.getSelectedItemPosition() == 2){
            blindsViewModel.closeBlinds(deviceId).observe(this, new Observer<Boolean>() {
                @Override
                public void onChanged(@Nullable Boolean aBoolean) {
                    if(aBoolean != null && aBoolean){
                        showToastError(getString(R.string.closing_blinds));
                    }
                    else if(aBoolean != null){
                        showToastError(getString(R.string.cant_close_blinds));
                    }
                    else{
                        showToastError(getString(R.string.error));
                    }
                }
            });
        }
        navigator.showDevicesActivity(this);
    }
}
