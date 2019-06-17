package com.itba.hci.smarthome.view.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.itba.hci.smarthome.R;
import com.itba.hci.smarthome.model.entities.Device;
import com.itba.hci.smarthome.model.viewModel.DeleteDeviceViewModel;
import com.itba.hci.smarthome.model.viewModel.SmartHomeViewModel;
import com.itba.hci.smarthome.view.Navigator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.OnClick;

public class DeleteDeviceFragment extends SmartHomeFragment {

    private String deviceId;

    @Inject
    Navigator navigator;

    DeleteDeviceViewModel deleteDeviceViewModel;

    public static Fragment newInstance(String deviceId) {
        DeleteDeviceFragment fragment = new DeleteDeviceFragment();
        fragment.setDeviceId(deviceId);
        return fragment;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        deleteDeviceViewModel = ViewModelProviders.of(this).get(DeleteDeviceViewModel.class);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected List<SmartHomeViewModel> getViewModels() {
        return new ArrayList<SmartHomeViewModel>(Collections.singletonList(deleteDeviceViewModel));
    }

    @Override
    protected int getViewId() {
        return R.layout.fragment_delete_device;
    }

    @OnClick(R.id.no_button)
    public void onNoDeleteDeviceCancelClick(){
        navigator.showDevicesActivity(this);
    }

    @OnClick(R.id.yes_button)
    public void onYesDeleteDeviceAcceptClick(){
        deleteDeviceViewModel.deleteDevice(deviceId).observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean result) {
                if (result != null && result) {
                    showToastError(getResources().getString(R.string.device_delete_ok));
                } else {
                    showToastError(getResources().getString(R.string.error));
                }
            }
        });
        navigator.showDevicesActivity(this);
    }
}
