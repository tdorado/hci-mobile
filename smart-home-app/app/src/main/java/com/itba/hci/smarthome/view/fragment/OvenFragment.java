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
import com.itba.hci.smarthome.model.entities.OvenState;
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

public class OvenFragment extends SmartHomeFragment {

    private String deviceId;
    private OvenState ovenState;

    @Inject
    Navigator navigator;

    @BindView(R.id.oven_status)
    TextView ovenStatusText;

    @BindView(R.id.oven_temperature)
    TextView ovenTemperatureText;

    @BindView(R.id.oven_heat)
    TextView ovenHeatText;

    @BindView(R.id.oven_grill)
    TextView ovenGrillText;

    @BindView(R.id.oven_convection)
    TextView ovenConvectionText;

    @BindView(R.id.actions_spinner)
    Spinner actionSpinner;

    @BindView(R.id.temperature_layout)
    LinearLayout temperatureLayout;

    @BindView(R.id.temperature_edit_text)
    EditText temperatureEditText;

    @BindView(R.id.grill_layout)
    LinearLayout grillLayout;

    @BindView(R.id.oven_grill_spinner)
    Spinner grillSpinner;

    @BindView(R.id.heat_layout)
    LinearLayout heatLayout;

    @BindView(R.id.oven_heat_spinner)
    Spinner heatSpinner;

    @BindView(R.id.convection_layout)
    LinearLayout convectionLayout;

    @BindView(R.id.oven_convection_spinner)
    Spinner convectionSpinner;

    OvenViewModel ovenViewModel;

    public static Fragment newInstance(String deviceId) {
        OvenFragment fragment = new OvenFragment();
        fragment.setDeviceId(deviceId);
        return fragment;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        ovenViewModel = ViewModelProviders.of(this).get(OvenViewModel.class);
        super.onCreate(savedInstanceState);
        ovenViewModel.getOvenState(deviceId).observe(this, new Observer<OvenState>() {
            @Override
            public void onChanged(@Nullable OvenState oven) {
                ovenState = oven;
                ovenStatusText.setText(getString(CommonUtils.getResourceId(oven.getStatus())));
                ovenTemperatureText.setText(String.valueOf(oven.getTemperature()));
                ovenGrillText.setText(getString(CommonUtils.getResourceId(oven.getGrill())));
                ovenHeatText.setText(getString(CommonUtils.getResourceId(oven.getHeat())));
                ovenConvectionText.setText(getString(CommonUtils.getResourceId(oven.getConvection())));
            }
        });
    }

    @Override
    protected List<SmartHomeViewModel> getViewModels() {
        return new ArrayList<SmartHomeViewModel>(Collections.singletonList(ovenViewModel));
    }

    @Override
    protected int getViewId() {
        return R.layout.fragment_oven;
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
                heatLayout.setVisibility(View.VISIBLE);
                break;
            case 5:
                grillLayout.setVisibility(View.VISIBLE);
                break;
            case 6:
                convectionLayout.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void setAllGone(){
        temperatureLayout.setVisibility(View.GONE);
        heatLayout.setVisibility(View.GONE);
        grillLayout.setVisibility(View.GONE);
        convectionLayout.setVisibility(View.GONE);
    }

    @OnClick(R.id.button_accept)
    public void onAcceptClick(){
        boolean goBack = true;
        if(actionSpinner.getSelectedItemPosition() == 1){
            if(ovenState.getStatus().equals("on")){
                goBack = false;
                showToastError(getString(R.string.cant_turn_on));
            }
            else {
                ovenViewModel.turnOnOven(deviceId).observe(this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(@Nullable Boolean aBoolean) {
                        if (aBoolean != null && aBoolean) {
                            sendNotification(getContext(), getString(R.string.oven), getString(R.string.d_turned_on));
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
            if(ovenState.getStatus().equals("off")){
                goBack = false;
                showToastError(getString(R.string.cant_turn_off));
            }
            else {
                ovenViewModel.turnOffOven(deviceId).observe(this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(@Nullable Boolean aBoolean) {
                        if (aBoolean != null && aBoolean) {
                            sendNotification(getContext(), getString(R.string.oven), getString(R.string.d_turned_off));
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
            if(temperatureValue < 90 || temperatureValue > 230){
                goBack = false;
                showToastError(getString(R.string.invalid_params));
            }
            else if(temperatureValue == ovenState.getTemperature()){
                showToastError(getString(R.string.current_option));
                goBack = false;
            }
            else{
                ovenViewModel.setTemperatureOven(deviceId, temperatureValue).observe(this, new Observer<Integer>() {
                    @Override
                    public void onChanged(@Nullable Integer oldTemperatureValue) {
                        if(ovenState.getTemperature().equals(oldTemperatureValue)){
                            sendNotification(getContext(), getString(R.string.oven), getString(R.string.d_changed_configuration));
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
            String heatValue = CommonUtils.getHeatFromArray(heatSpinner.getSelectedItemPosition());
            if(heatValue.equals(ovenState.getHeat())){
                showToastError(getString(R.string.current_option));
                goBack = false;
            }
            else{
                ovenViewModel.setHeatOven(deviceId, heatValue).observe(this, new Observer<String>() {
                    @Override
                    public void onChanged(@Nullable String s) {
                        if(ovenState.getHeat().equals(s)){
                            sendNotification(getContext(), getString(R.string.oven), getString(R.string.d_changed_configuration));
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
            String grillValue = CommonUtils.getGrillFromArray(grillSpinner.getSelectedItemPosition());
            if(grillValue.equals(ovenState.getGrill())){
                showToastError(getString(R.string.current_option));
                goBack = false;
            }
            else{
                ovenViewModel.setGrillOven(deviceId, grillValue).observe(this, new Observer<String>() {
                    @Override
                    public void onChanged(@Nullable String s) {
                        if(ovenState.getGrill().equals(s)){
                            sendNotification(getContext(), getString(R.string.oven), getString(R.string.d_changed_configuration));
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
            String convectionValue = CommonUtils.getConvectionFromArray(convectionSpinner.getSelectedItemPosition());
            if(convectionValue.equals(ovenState.getConvection())){
                showToastError(getString(R.string.current_option));
                goBack = false;
            }
            else{
                ovenViewModel.setConvectionOven(deviceId, convectionValue).observe(this, new Observer<String>() {
                    @Override
                    public void onChanged(@Nullable String s) {
                        if(ovenState.getConvection().equals(s)){
                            sendNotification(getContext(), getString(R.string.oven), getString(R.string.d_changed_configuration));
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
