package com.itba.hci.smarthome.view.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;
import android.widget.Spinner;

import com.itba.hci.smarthome.R;
import com.itba.hci.smarthome.model.request.DeviceRequest;
import com.itba.hci.smarthome.model.viewModel.NewDeviceViewModel;
import com.itba.hci.smarthome.model.viewModel.SmartHomeViewModel;
import com.itba.hci.smarthome.view.Navigator;
import com.itba.hci.smarthome.view.util.CommonUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class NewDeviceFragment extends SmartHomeFragment{

    @Inject
    Navigator navigator;

    @BindView(R.id.device_type_spinner)
    Spinner typesSpinner;

    @BindView(R.id.new_device_name)
    EditText newDeviceNameEditText;

    NewDeviceViewModel newDeviceViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        newDeviceViewModel = ViewModelProviders.of(this).get(NewDeviceViewModel.class);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected List<SmartHomeViewModel> getViewModels() {
        return new ArrayList<SmartHomeViewModel>(Collections.singletonList(newDeviceViewModel));
    }

    @Override
    protected int getViewId() {
        return R.layout.fragment_new_device;
    }

    @OnClick(R.id.button_cancel_new_device)
    public void onNewDeviceCancelClick(){
        navigator.showDevicesActivity(this);
    }

    @OnClick(R.id.button_accept_new_device)
    public void onNewDeviceAcceptClick(){
        String newDeviceName = newDeviceNameEditText.getText().toString();
        if(newDeviceName.isEmpty()) {
            showToastError(getResources().getString(R.string.name_for_device));
        }
        else {
            DeviceRequest deviceRequest = new DeviceRequest(CommonUtils.getDeviceTypes().get(typesSpinner.getSelectedItemPosition()), newDeviceName, "{}");
            newDeviceViewModel.createDevice(deviceRequest).observe(this, new Observer<Boolean>() {
                @Override
                public void onChanged(@Nullable Boolean result) {
                    if (result != null && result) {
                        showToastError(getResources().getString(R.string.device_update_ok));
                    } else {
                        showToastError(getResources().getString(R.string.error_create_device));
                    }
                }
            });
            navigator.showDevicesActivity(this);
        }
    }
}
