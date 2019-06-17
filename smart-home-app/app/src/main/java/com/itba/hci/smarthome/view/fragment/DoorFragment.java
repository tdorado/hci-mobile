package com.itba.hci.smarthome.view.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.Spinner;
import android.widget.TextView;

import com.itba.hci.smarthome.R;
import com.itba.hci.smarthome.model.entities.DoorState;
import com.itba.hci.smarthome.model.viewModel.DoorViewModel;
import com.itba.hci.smarthome.model.viewModel.SmartHomeViewModel;
import com.itba.hci.smarthome.view.Navigator;
import com.itba.hci.smarthome.view.util.CommonUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class DoorFragment extends SmartHomeFragment {

    private String deviceId;
    private DoorState doorState;

    @Inject
    Navigator navigator;

    @BindView(R.id.door_status)
    TextView doorStatusText;

    @BindView(R.id.door_locked_unlocked)
    TextView doorLockText;

    @BindView(R.id.actions_spinner)
    Spinner actionSpinner;

    DoorViewModel doorViewModel;

    public static Fragment newInstance(String deviceId) {
        DoorFragment fragment = new DoorFragment();
        fragment.setDeviceId(deviceId);
        return fragment;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        doorViewModel = ViewModelProviders.of(this).get(DoorViewModel.class);
        super.onCreate(savedInstanceState);
        doorViewModel.getDoorState(deviceId).observe(this, new Observer<DoorState>() {
            @Override
            public void onChanged(@Nullable DoorState d) {
                doorState = d;
                doorStatusText.setText(getString(CommonUtils.getResourceId(d.getStatus())));
                doorLockText.setText(getString(CommonUtils.getResourceId(d.getLock())));
            }
        });
    }

    @Override
    protected List<SmartHomeViewModel> getViewModels() {
        return new ArrayList<SmartHomeViewModel>(Collections.singletonList(doorViewModel));
    }

    @Override
    protected int getViewId() {
        return R.layout.fragment_door;
    }

    @OnClick(R.id.button_cancel)
    public void onCancelClick(){
        navigator.showDevicesActivity(this);
    }

    @OnClick(R.id.button_accept)
    public void onAcceptClick(){
        boolean goBack = true;
        String doorStatus = doorState.getStatus();
        String doorLock = doorState.getLock();

        if(actionSpinner.getSelectedItemPosition() == 1){
            if(doorStatus.equals("opened")){
                goBack = false;
                showToastError(getString(R.string.door_already_opened));
            }
            else if(doorLock.equals("locked")){
                goBack = false;
                showToastError(getString(R.string.door_locked));
            }
            else {
                doorViewModel.openDoor(deviceId).observe(this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(@Nullable Boolean aBoolean) {
                        if (aBoolean != null && aBoolean) {
                            showToastError(getString(R.string.door_opened));
                        } else if (aBoolean != null) {
                            showToastError(getString(R.string.cant_open_door));
                        } else {
                            showToastError(getString(R.string.error));
                        }
                    }
                });
            }
        }
        else if(actionSpinner.getSelectedItemPosition() == 2){
            if(doorStatus.equals("closed")){
                goBack = false;
                showToastError(getString(R.string.door_already_closed));
            }
            else {
                doorViewModel.closeDoor(deviceId).observe(this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(@Nullable Boolean aBoolean) {
                        if (aBoolean != null && aBoolean) {
                            showToastError(getString(R.string.door_closed));
                        } else if (aBoolean != null) {
                            showToastError(getString(R.string.cant_close_door));
                        } else {
                            showToastError(getString(R.string.error));
                        }
                    }
                });
            }
        }
        else if(actionSpinner.getSelectedItemPosition() == 3){
            if(doorLock.equals("locked")){
                goBack = false;
                showToastError(getString(R.string.door_already_locked));
            }
            else if(doorStatus.equals("opened")){
                goBack = false;
                showToastError(getString(R.string.door_opened));
            }
            else {
                doorViewModel.lockDoor(deviceId).observe(this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(@Nullable Boolean aBoolean) {
                        if (aBoolean != null && aBoolean) {
                            showToastError(getString(R.string.door_locked));
                        } else if (aBoolean != null) {
                            showToastError(getString(R.string.cant_lock_door));
                        } else {
                            showToastError(getString(R.string.error));
                        }
                    }
                });
            }
        }
        else if(actionSpinner.getSelectedItemPosition() == 4){
            if(doorLock.equals("unlocked")){
                goBack = false;
                showToastError(getString(R.string.door_already_unlocked));
            }
            else if(doorStatus.equals("closed")){
                goBack = false;
                showToastError(getString(R.string.door_closed));
            }
            else {
                doorViewModel.unlockDoor(deviceId).observe(this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(@Nullable Boolean aBoolean) {
                        if (aBoolean != null && aBoolean) {
                            showToastError(getString(R.string.door_unlocked));
                        } else if (aBoolean != null) {
                            showToastError(getString(R.string.cant_unlock_door));
                        } else {
                            showToastError(getString(R.string.error));
                        }
                    }
                });
            }
        }

        if(goBack) {
            navigator.showDevicesActivity(this);
        }
    }
}
