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
import com.itba.hci.smarthome.model.entities.LampState;
import com.itba.hci.smarthome.model.viewModel.LampViewModel;
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

public class LampFragment extends SmartHomeFragment{

    private String deviceId;
    private LampState lampState;

    @Inject
    Navigator navigator;

    @BindView(R.id.lamp_status)
    TextView lampStatusText;

    @BindView(R.id.lamp_color)
    TextView lampColorText;

    @BindView(R.id.lamp_brightness)
    TextView lampBrightnessText;

    @BindView(R.id.actions_spinner)
    Spinner actionSpinner;

    @BindView(R.id.color_layout)
    LinearLayout colorLayout;

    @BindView(R.id.color_spinner)
    Spinner colorSpinner;

    @BindView(R.id.brightness_layout)
    LinearLayout brightnessLayout;

    @BindView(R.id.brightness_edit_text)
    EditText brightnessEditText;


    LampViewModel lampViewModel;

    public static Fragment newInstance(String deviceId) {
        LampFragment fragment = new LampFragment();
        fragment.setDeviceId(deviceId);
        return fragment;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        lampViewModel = ViewModelProviders.of(this).get(LampViewModel.class);
        super.onCreate(savedInstanceState);
        lampViewModel.getLampState(deviceId).observe(this, new Observer<LampState>() {
            @Override
            public void onChanged(@Nullable LampState lamp) {
                lampState = lamp;
                lampStatusText.setText(getString(CommonUtils.getResourceId(lamp.getStatus())));
                lampColorText.setText(getString(CommonUtils.getResourceId(lamp.getColor())));
                lampBrightnessText.setText(String.valueOf(lamp.getBrightness()));
            }
        });
    }

    @Override
    protected List<SmartHomeViewModel> getViewModels() {
        return new ArrayList<SmartHomeViewModel>(Collections.singletonList(lampViewModel));
    }

    @Override
    protected int getViewId() {
        return R.layout.fragment_lamp;
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
                colorLayout.setVisibility(View.VISIBLE);
                break;
            case 4:
                brightnessLayout.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void setAllGone(){
        brightnessLayout.setVisibility(View.GONE);
        colorLayout.setVisibility(View.GONE);
    }

    @OnClick(R.id.button_accept)
    public void onAcceptClick(){
        boolean goBack = true;
        if(actionSpinner.getSelectedItemPosition() == 1){
            if(lampState.getStatus().equals("on")){
                goBack = false;
                showToastError(getString(R.string.cant_turn_on));
            }
            else {
                lampViewModel.turnOnLamp(deviceId).observe(this, new Observer<Boolean>() {
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
            if(lampState.getStatus().equals("off")){
                goBack = false;
                showToastError(getString(R.string.cant_turn_off));
            }
            else {
                lampViewModel.turnOffLamp(deviceId).observe(this, new Observer<Boolean>() {
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
            String colorValue = CommonUtils.getColorFromArray(colorSpinner.getSelectedItemPosition());
            if(colorValue.equals(lampState.getColor())){
                showToastError(getString(R.string.current_option));
                goBack = false;
            }
            else{
                lampViewModel.setColorLamp(deviceId, colorValue).observe(this, new Observer<String>() {
                    @Override
                    public void onChanged(@Nullable String oldColorValue) {
                        if(lampState.getColor().equals(oldColorValue)){
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
            int brightnessValue = Integer.valueOf(brightnessEditText.getText().toString());
            if(brightnessValue < 0 || brightnessValue > 100){
                showToastError(getString(R.string.invalid_params));
                goBack = false;
            }
            else if(brightnessValue == lampState.getBrightness()){
                showToastError(getString(R.string.current_option));
                goBack = false;
            }
            else{
                lampViewModel.setBrightnessLamp(deviceId, brightnessValue).observe(this, new Observer<Integer>() {
                    @Override
                    public void onChanged(@Nullable Integer oldBrightnessValue) {
                        if(lampState.getBrightness().equals(oldBrightnessValue)){
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
