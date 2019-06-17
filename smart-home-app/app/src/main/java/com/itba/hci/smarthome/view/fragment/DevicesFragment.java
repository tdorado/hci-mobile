package com.itba.hci.smarthome.view.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.res.Configuration;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.OrientationEventListener;
import android.view.View;

import com.itba.hci.smarthome.R;
import com.itba.hci.smarthome.model.entities.Device;
import com.itba.hci.smarthome.model.viewModel.DeviceViewModel;
import com.itba.hci.smarthome.model.viewModel.SmartHomeViewModel;
import com.itba.hci.smarthome.view.util.DevicesAdapter;
import com.itba.hci.smarthome.view.Navigator;
import com.itba.hci.smarthome.view.fragmentView.ClickListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class DevicesFragment extends SmartHomeFragment implements ClickListener {

    DevicesAdapter devicesAdapter = new DevicesAdapter(this);

    @BindView(R.id.recycler_devices)
    RecyclerView devices;

    @Inject
    Navigator navigator;

    DeviceViewModel deviceViewModel;

    @OnClick(R.id.button_add_device)
    public void onAddDeviceClick(){
        navigator.showNewDeviceActivity(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        deviceViewModel = ViewModelProviders.of(this).get(DeviceViewModel.class);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected List<SmartHomeViewModel> getViewModels() {
        return new ArrayList<SmartHomeViewModel>(Collections.singletonList(deviceViewModel));
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        if(isTablet()){
            devices.setLayoutManager(new GridLayoutManager(getContext(), 2));
        }
        else {
            devices.setLayoutManager(new LinearLayoutManager(getContext()));
        }
        devices.setAdapter(devicesAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        deviceViewModel.getAllDevices().
                    observe(this, new Observer<List<Device>>() {
                        @Override
                        public void onChanged(@Nullable List<Device> devices) {
                            List<Device> lamps = new ArrayList<>();
                            List<Device> acs = new ArrayList<>();
                            List<Device> blinds = new ArrayList<>();
                            List<Device> alarms = new ArrayList<>();
                            List<Device> doors = new ArrayList<>();
                            List<Device> ovens = new ArrayList<>();
                            List<Device> refrigerators = new ArrayList<>();
                            List<Device> timers = new ArrayList<>();
                            for(Device device : devices){
                                switch(device.getTypeId()){
                                    case "go46xmbqeomjrsjr": //lamp
                                        lamps.add(device);
                                        break;
                                    case "li6cbv5sdlatti0j": //ac
                                        acs.add(device);
                                        break;
                                    case "eu0v2xgprrhhg41g": // blinds
                                        blinds.add(device);
                                        break;
                                    case "mxztsyjzsrq7iaqc": // alarm
                                        alarms.add(device);
                                        break;
                                    case "lsf78ly0eqrjbz91": // door
                                        doors.add(device);
                                        break;
                                    case "im77xxyulpegfmv8": // oven
                                        ovens.add(device);
                                        break;
                                    case "rnizejqr2di0okho": // refrigerator
                                        refrigerators.add(device);
                                        break;
                                    case "ofglvd9gqX8yfl3l": // timer
                                        timers.add(device);
                                        break;
                                }
                            }
                            List<Device> devicesOrdered = new ArrayList<>();
                            devicesOrdered.addAll(lamps);
                            devicesOrdered.addAll(acs);
                            devicesOrdered.addAll(blinds);
                            devicesOrdered.addAll(alarms);
                            devicesOrdered.addAll(doors);
                            devicesOrdered.addAll(ovens);
                            devicesOrdered.addAll(refrigerators);
                            devicesOrdered.addAll(timers);
                            devicesAdapter.setList(devicesOrdered);
                            devicesAdapter.notifyDataSetChanged();
                        }
                    });
    }

    @Override
    protected int getViewId() {
        return R.layout.fragment_devices;
    }

    @Override
    public void onClick(int item, String idItemClicked) {
        switch(item) {
            case 0:
                navigator.showEditDeviceActivity(this, idItemClicked);
                break;
            case 1:
                navigator.showLampActivity(this, idItemClicked);
                break;
            case 2:
                navigator.showAcActivity(this, idItemClicked);
                break;
            case 3:
                navigator.showBlindsActivity(this, idItemClicked);
                break;
            case 4:
                navigator.showAlarmActivity(this, idItemClicked);
                break;
            case 5:
                navigator.showDoorActivity(this, idItemClicked);
                break;
            case 6:
                navigator.showOvenActivity(this, idItemClicked);
                break;
            case 7:
            case 8:
                showToastError(getString(R.string.device_not_implemented));
                break;
            case 10:
                navigator.showDeleteDeviceActivity(this, idItemClicked);
                break;
        }
    }
}
