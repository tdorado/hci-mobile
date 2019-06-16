package com.itba.hci.smarthome.view.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.itba.hci.smarthome.R;
import com.itba.hci.smarthome.model.entities.Device;
import com.itba.hci.smarthome.model.viewModel.DeviceViewModel;
import com.itba.hci.smarthome.model.viewModel.SmartHomeViewModel;
import com.itba.hci.smarthome.util.DevicesAdapter;
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
        devices.setLayoutManager(new LinearLayoutManager(getContext()));
        devices.setAdapter(devicesAdapter);
    }

    public void onResume() {
        super.onResume();
        deviceViewModel.getAllDevices().
                    observe(this, new Observer<List<Device>>() {
                        @Override
                        public void onChanged(@Nullable List<Device> devices) {
                            devicesAdapter.setList(devices);
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
            case 3:
                navigator.showBlindsActivity(this, idItemClicked);
        }
    }
}
