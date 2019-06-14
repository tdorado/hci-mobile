package com.itba.hci.smarthome.view.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.EditText;
import android.widget.Spinner;

import com.itba.hci.smarthome.R;
import com.itba.hci.smarthome.model.entities.Device;
import com.itba.hci.smarthome.model.entities.DeviceTypes;
import com.itba.hci.smarthome.model.request.DeviceRequest;
import com.itba.hci.smarthome.model.viewModel.EditDeviceViewModel;
import com.itba.hci.smarthome.model.viewModel.NewDeviceViewModel;
import com.itba.hci.smarthome.model.viewModel.SmartHomeViewModel;
import com.itba.hci.smarthome.view.Navigator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class EditDeviceFragment extends SmartHomeFragment {

    private String deviceId;
    private Device deviceE;

    @Inject
    Navigator navigator;

    @BindView(R.id.edit_device_name)
    EditText editDeviceNameEditText;

    EditDeviceViewModel editDeviceViewModel;

    public static Fragment newInstance(String deviceId) {
        EditDeviceFragment fragment = new EditDeviceFragment();
        fragment.setDeviceId(deviceId);
        return fragment;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        editDeviceViewModel = ViewModelProviders.of(this).get(EditDeviceViewModel.class);
        super.onCreate(savedInstanceState);
        editDeviceViewModel.getDevice(deviceId).observe(this, new Observer<Device>() {
            @Override
            public void onChanged(@Nullable Device device) {
                if(device != null){
                    deviceE = device;
                    editDeviceNameEditText.setText(device.getName());
                }
            }
        });
    }

    @Override
    protected List<SmartHomeViewModel> getViewModels() {
        return new ArrayList<SmartHomeViewModel>(Collections.singletonList(editDeviceViewModel));
    }

    @Override
    protected int getViewId() {
        return R.layout.fragment_edit_device;
    }

    @OnClick(R.id.button_cancel_changes_device)
    public void onEditDeviceCancelClick(){
        navigator.showDevicesActivity(this);
    }

    @OnClick(R.id.button_accept_changes_device)
    public void onEditDeviceAcceptClick(){
        String newDeviceName = editDeviceNameEditText.getText().toString();
        if(newDeviceName.isEmpty()){
            showToastError("Ingrese un nombre para el dispositivo");
        }
        else {
            DeviceRequest deviceRequest = new DeviceRequest(deviceE.getTypeId(), newDeviceName, deviceE.getMeta());
            editDeviceViewModel.editDevice(deviceId, deviceRequest).observe(this, new Observer<Boolean>() {
                @Override
                public void onChanged(@Nullable Boolean result) {
                    if (result != null && result) {
                        showToastError("Dispositivo modificado correctamente");
                    } else {
                        showToastError("Algo salió mal al intentar modificar el dispositivo");
                    }
                }
            });
            navigator.showDevicesActivity(this);
        }
    }
}
