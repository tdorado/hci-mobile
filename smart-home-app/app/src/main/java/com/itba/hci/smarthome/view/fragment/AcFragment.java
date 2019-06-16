package com.itba.hci.smarthome.view.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.itba.hci.smarthome.R;
import com.itba.hci.smarthome.model.entities.AcState;
import com.itba.hci.smarthome.model.viewModel.AcViewModel;
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

public class AcFragment extends SmartHomeFragment {

    private String deviceId;
    private AcState acState;

    @Inject
    Navigator navigator;

    @BindView(R.id.ac_status)
    TextView acStatusText;

    @BindView(R.id.ac_temperature)
    TextView acTemperatureText;

    @BindView(R.id.ac_mode)
    TextView acModeText;

    @BindView(R.id.ac_vertical_swing)
    TextView acVerticalSwingText;

    @BindView(R.id.ac_horizontal_swing)
    TextView acHorizontalSwingText;

    @BindView(R.id.ac_fan_speed)
    TextView acFanSpeedText;

    @BindView(R.id.actions_spinner)
    Spinner actionSpinner;

    @BindView(R.id.temperature_layout)
    LinearLayout temperatureLayout;

    @BindView(R.id.temperature_edit_text)
    EditText temperatureEditText;

    @BindView(R.id.mode_layout)
    LinearLayout modeLayout;

    @BindView(R.id.ac_mode_spinner)
    Spinner modeSpinner;

    @BindView(R.id.vertical_swing_layout)
    LinearLayout verticalSwingLayout;

    @BindView(R.id.ac_vertical_swing_spinner)
    Spinner verticalSwingSpinner;

    @BindView(R.id.horizontal_swing_layout)
    LinearLayout horizontalSwingLayout;

    @BindView(R.id.ac_horizontal_swing_spinner)
    Spinner horizontalSwingSpinner;

    @BindView(R.id.fan_speed_layout)
    LinearLayout fanSpeedLayout;

    @BindView(R.id.ac_fan_speed_spinner)
    Spinner fanSpeedSpinner;

    AcViewModel acViewModel;

    public static Fragment newInstance(String deviceId) {
        AcFragment fragment = new AcFragment();
        fragment.setDeviceId(deviceId);
        return fragment;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        acViewModel = ViewModelProviders.of(this).get(AcViewModel.class);
        super.onCreate(savedInstanceState);
        acViewModel.getAcState(deviceId).observe(this, new Observer<AcState>() {
            @Override
            public void onChanged(@Nullable AcState ac) {
                acState = ac;
                acStatusText.setText(getString(CommonUtils.getResourceId(ac.getStatus())));
                acTemperatureText.setText(String.valueOf(ac.getTemperature()));
                acModeText.setText(getString(CommonUtils.getResourceId(ac.getMode())));
                acVerticalSwingText.setText(CommonUtils.getStringWithAuto(getContext(), ac.getVerticalSwing()));
                acHorizontalSwingText.setText(CommonUtils.getStringWithAuto(getContext(), ac.getHorizontalSwing()));
                acFanSpeedText.setText(CommonUtils.getStringWithAuto(getContext(), ac.getFanSpeed()));
            }
        });
    }

    @Override
    protected List<SmartHomeViewModel> getViewModels() {
        return new ArrayList<SmartHomeViewModel>(Collections.singletonList(acViewModel));
    }

    @Override
    protected int getViewId() {
        return R.layout.fragment_ac;
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
            case 1:
            case 2:
                break;
            case 3:
                temperatureLayout.setVisibility(View.VISIBLE);
                break;
            case 4:
                modeLayout.setVisibility(View.VISIBLE);
                break;
            case 5:
                verticalSwingLayout.setVisibility(View.VISIBLE);
                break;
            case 6:
                horizontalSwingLayout.setVisibility(View.VISIBLE);
                break;
            case 7:
                fanSpeedLayout.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void setAllGone(){
        temperatureLayout.setVisibility(View.GONE);
        modeLayout.setVisibility(View.GONE);
        verticalSwingLayout.setVisibility(View.GONE);
        horizontalSwingLayout.setVisibility(View.GONE);
        fanSpeedLayout.setVisibility(View.GONE);
    }

    @OnClick(R.id.button_accept)
    public void onEditDeviceAcceptClick(){
        boolean goBack = true;
        if(actionSpinner.getSelectedItemPosition() == 1){
            if(acState.getStatus().equals("on")){
                goBack = false;
                showToastError(getString(R.string.cant_turn_on));
            }
            else {
                acViewModel.turnOnAc(deviceId).observe(this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(@Nullable Boolean aBoolean) {
                        if (aBoolean != null && aBoolean) {
                            showToastError(getString(R.string.turn_on));
                        } else if (aBoolean != null) {
                            showToastError(getString(R.string.cant_turn_on));
                        } else {
                            showToastError(getString(R.string.error));
                        }
                    }
                });
            }
        }
        else if(actionSpinner.getSelectedItemPosition() == 2){
            if(acState.getStatus().equals("off")){
                goBack = false;
                showToastError(getString(R.string.cant_turn_off));
            }
            else {
                acViewModel.turnOffAc(deviceId).observe(this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(@Nullable Boolean aBoolean) {
                        if (aBoolean != null && aBoolean) {
                            showToastError(getString(R.string.turn_off));
                        } else if (aBoolean != null) {
                            showToastError(getString(R.string.cant_turn_off));
                        } else {
                            showToastError(getString(R.string.error));
                        }
                    }
                });
            }
        }
        else if(actionSpinner.getSelectedItemPosition() == 3){
            int temperatureValue = Integer.valueOf(temperatureEditText.getText().toString());
            if(temperatureValue < 18 || temperatureValue > 38){
                goBack = false;
                showToastError(getString(R.string.invalid_params));
            }
            else if(temperatureValue == acState.getTemperature()){
                showToastError(getString(R.string.current_option));
                goBack = false;
            }
            else{
                acViewModel.setTemperatureAc(deviceId, temperatureValue).observe(this, new Observer<Integer>() {
                    @Override
                    public void onChanged(@Nullable Integer oldTemperatureValue) {
                        if(acState.getTemperature().equals(oldTemperatureValue)){
                            showToastError(getString(R.string.configuration_changed));
                        }
                        else{
                            showToastError(getString(R.string.error));
                        }
                    }
                });
            }
        }
        else if(actionSpinner.getSelectedItemPosition() == 4){
            String modeValue = CommonUtils.getModeFromArray(modeSpinner.getSelectedItemPosition());
            if(modeValue.equals(acState.getMode())){
                showToastError(getString(R.string.current_option));
                goBack = false;
            }
            else{
                acViewModel.setModeAc(deviceId, modeValue).observe(this, new Observer<String>() {
                    @Override
                    public void onChanged(@Nullable String s) {
                        if(acState.getMode().equals(s)){
                            showToastError(getString(R.string.configuration_changed));
                        }
                        else{
                            showToastError(getString(R.string.error));
                        }
                    }
                });
            }
        }
        else if(actionSpinner.getSelectedItemPosition() == 5){
            String verticalSwingValue = CommonUtils.getStringItemWithAuto(verticalSwingSpinner);
            if(verticalSwingValue.equals(acState.getVerticalSwing())){
                showToastError(getString(R.string.current_option));
                goBack = false;
            }
            else{
                acViewModel.setVerticalSwingAc(deviceId, verticalSwingValue).observe(this, new Observer<String>() {
                    @Override
                    public void onChanged(@Nullable String s) {
                        if(acState.getVerticalSwing().equals(s)){
                            showToastError(getString(R.string.configuration_changed));
                        }
                        else{
                            showToastError(getString(R.string.error));
                        }
                    }
                });
            }
        }
        else if(actionSpinner.getSelectedItemPosition() == 6){
            String horizontalSwingValue = CommonUtils.getStringItemWithAuto(horizontalSwingSpinner);
            if(horizontalSwingValue.equals(acState.getHorizontalSwing())){
                showToastError(getString(R.string.current_option));
                goBack = false;
            }
            else{
                acViewModel.setHorizontalSwingAc(deviceId, horizontalSwingValue).observe(this, new Observer<String>() {
                    @Override
                    public void onChanged(@Nullable String s) {
                        if(acState.getHorizontalSwing().equals(s)){
                            showToastError(getString(R.string.configuration_changed));
                        }
                        else{
                            showToastError(getString(R.string.error));
                        }
                    }
                });
            }
        }
        else if(actionSpinner.getSelectedItemPosition() == 7){
            String fanSpeedValue = CommonUtils.getStringItemWithAuto(fanSpeedSpinner);
            if(fanSpeedValue.equals(acState.getFanSpeed())){
                showToastError(getString(R.string.current_option));
                goBack = false;
            }
            else{
                acViewModel.setFanSpeedAc(deviceId, fanSpeedValue).observe(this, new Observer<String>() {
                    @Override
                    public void onChanged(@Nullable String s) {
                        if(acState.getFanSpeed().equals(s)){
                            showToastError(getString(R.string.configuration_changed));
                        }
                        else{
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
