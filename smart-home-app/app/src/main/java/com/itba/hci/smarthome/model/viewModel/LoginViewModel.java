package com.itba.hci.smarthome.model.viewModel;

import android.arch.lifecycle.LiveData;

import com.itba.hci.smarthome.dagger.components.SmartHomeComponents;
import com.itba.hci.smarthome.db.LoginRepository;
import com.itba.hci.smarthome.model.request.LoginRequest;
import com.itba.hci.smarthome.service.payload.UserResponse;

import javax.inject.Inject;

public class LoginViewModel extends SmartHomeViewModel {

    @Inject
    LoginRepository loginRepository;

    @Override
    public void initialize(SmartHomeComponents component) {
        component.inject(this);
    }

    public LiveData<UserResponse> login(String usuario, String clave) {
        return loginRepository.login(new LoginRequest(usuario, clave));
    }
}
