package com.itba.hci.smarthome.view.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.itba.hci.smarthome.R;
import com.itba.hci.smarthome.model.entities.AlarmState;
import com.itba.hci.smarthome.model.entities.OvenState;
import com.itba.hci.smarthome.model.viewModel.AlarmViewModel;
import com.itba.hci.smarthome.model.viewModel.OvenViewModel;
import com.itba.hci.smarthome.model.viewModel.SmartHomeViewModel;
import com.itba.hci.smarthome.view.Navigator;
import com.itba.hci.smarthome.view.util.CommonUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnItemSelected;

public class AlarmFragment extends SmartHomeFragment {

    private String deviceId;
    private AlarmState alarmState;

    @Inject
    Navigator navigator;

    @BindView(R.id.alarm_status)
    TextView alarmStatusText;

    @BindView(R.id.actions_spinner)
    Spinner actionSpinner;

    @BindView(R.id.current_security_layout)
    LinearLayout currentSecurityLayout;

    @BindView(R.id.current_security_code_edit_text)
    EditText currentSecurityEditText;

    @BindView(R.id.new_security_layout)
    LinearLayout newSecurityLayout;

    @BindView(R.id.new_security_code_edit_text)
    EditText newSecurityEditText;

    AlarmViewModel alarmViewModel;

    public static Fragment newInstance(String deviceId) {
        AlarmFragment fragment = new AlarmFragment();
        fragment.setDeviceId(deviceId);
        return fragment;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        alarmViewModel = ViewModelProviders.of(this).get(AlarmViewModel.class);
        super.onCreate(savedInstanceState);
        alarmViewModel.getAlarmState(deviceId).observe(this, new Observer<AlarmState>() {
            @Override
            public void onChanged(@Nullable AlarmState alarm) {
                alarmState = alarm;
                alarmStatusText.setText(getString(CommonUtils.getResourceId(alarm.getStatus())));
            }
        });
    }

    @Override
    protected List<SmartHomeViewModel> getViewModels() {
        return new ArrayList<SmartHomeViewModel>(Collections.singletonList(alarmViewModel));
    }

    @Override
    protected int getViewId() {
        return R.layout.fragment_alarm;
    }

    @OnClick(R.id.button_cancel)
    public void onCancelClick(){
        navigator.showDevicesActivity(this);
    }

    @OnItemSelected(R.id.actions_spinner)
    public void onItemSelected(int position){
        setAllGone();
        switch(position){
            case 0:
                break;
            case 1:
                currentSecurityLayout.setVisibility(View.VISIBLE);
                newSecurityLayout.setVisibility(View.VISIBLE);
            case 2:
            case 3:
            case 4:
                currentSecurityLayout.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void setAllGone(){
        currentSecurityLayout.setVisibility(View.GONE);
        newSecurityLayout.setVisibility(View.GONE);
    }

    @OnClick(R.id.button_accept)
    public void onAcceptClick(){
        boolean goBack = true;
        if(actionSpinner.getSelectedItemPosition() == 1){
            String currentPassValue = String.valueOf(currentSecurityEditText.getText());
            String newPassValue = String.valueOf(newSecurityEditText.getText());
            if(currentPassValue.length() < 4){
                goBack = false;
                showToastError(getString(R.string.alarm_invalid_current_code));
            }
            else if(newPassValue.length() < 4){
                goBack = false;
                showToastError(getString(R.string.alarm_invalid_new_code));
            }
            else {
                alarmViewModel.changeSecurityCodeAlarm(deviceId, currentPassValue, newPassValue).observe(this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(@Nullable Boolean aBoolean) {
                        if (aBoolean != null && aBoolean) {
                            sendNotification(getContext(), getString(R.string.alarm), getString(R.string.d_changed_configuration));
                            showToastError(getString(R.string.alarm_code_changed));
                        } else if (aBoolean != null) {
                            showToastError(getString(R.string.alarm_wrong_code));
                        } else {
                            showToastError(getString(R.string.error));
                        }
                    }
                });
            }
        }
        else if(actionSpinner.getSelectedItemPosition() == 2){
            String passValue = String.valueOf(currentSecurityEditText.getText());
            if(alarmState.getStatus().equals("armedStay")){
                goBack = false;
                showToastError(getString(R.string.alarm_already_armed));
            }
            else if(passValue.length() < 4){
                goBack = false;
                showToastError(getString(R.string.alarm_invalid_code));
            }
            else {
                alarmViewModel.armStayAlarm(deviceId, passValue).observe(this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(@Nullable Boolean aBoolean) {
                        if (aBoolean != null && aBoolean) {
                            sendNotification(getContext(), getString(R.string.alarm), getString(R.string.d_turned_on));
                            showToastError(getString(R.string.alarm_activated));
                        } else if (aBoolean != null) {
                            showToastError(getString(R.string.alarm_wrong_code));
                        } else {
                            showToastError(getString(R.string.error));
                        }
                    }
                });
            }
        }
        else if(actionSpinner.getSelectedItemPosition() == 3){
            String passValue = String.valueOf(currentSecurityEditText.getText());
            if(alarmState.getStatus().equals("armedAway")){
                goBack = false;
                showToastError(getString(R.string.alarm_already_armed));
            }
            else if(passValue.length() < 4){
                goBack = false;
                showToastError(getString(R.string.alarm_invalid_code));
            }
            else {
                alarmViewModel.armAwayAlarm(deviceId, passValue).observe(this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(@Nullable Boolean aBoolean) {
                        if (aBoolean != null && aBoolean) {
                            sendNotification(getContext(), getString(R.string.alarm), getString(R.string.d_turned_on));
                            showToastError(getString(R.string.alarm_activated));
                        } else if (aBoolean != null) {
                            showToastError(getString(R.string.alarm_wrong_code));
                        } else {
                            showToastError(getString(R.string.error));
                        }
                    }
                });
            }
        }
        else if(actionSpinner.getSelectedItemPosition() == 4){
            String passValue = String.valueOf(currentSecurityEditText.getText());
            if(alarmState.getStatus().equals("disarmed")){
                goBack = false;
                showToastError(getString(R.string.alarm_already_disarmed));
            }
            else if(passValue.length() < 4){
                goBack = false;
                showToastError(getString(R.string.alarm_invalid_code));
            }
            else {
                alarmViewModel.disarmAlarm(deviceId, passValue).observe(this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(@Nullable Boolean aBoolean) {
                        if (aBoolean != null && aBoolean) {
                            sendNotification(getContext(), getString(R.string.alarm), getString(R.string.d_turned_off));
                            showToastError(getString(R.string.alarm_disarmed));
                        } else if (aBoolean != null) {
                            showToastError(getString(R.string.alarm_wrong_code));
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
