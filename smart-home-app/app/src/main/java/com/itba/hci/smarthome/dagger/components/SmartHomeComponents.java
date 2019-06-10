package com.itba.hci.smarthome.dagger.components;

import com.itba.hci.smarthome.core.SmartHomeApplication;
import com.itba.hci.smarthome.dagger.modules.ActivityModule;
import com.itba.hci.smarthome.dagger.modules.ApplicationModule;
import com.itba.hci.smarthome.dagger.modules.DataModule;
import com.itba.hci.smarthome.dagger.modules.FragmentModule;
import com.itba.hci.smarthome.dagger.modules.ServiceModule;
import com.itba.hci.smarthome.model.viewModel.LoginViewModel;
import com.itba.hci.smarthome.view.activity.LoginActivity;
import com.itba.hci.smarthome.view.fragment.LoginFragment;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(
        modules = {
                AndroidInjectionModule.class,
                ServiceModule.class,
                DataModule.class,
                ApplicationModule.class,
                ActivityModule.class,
                FragmentModule.class,

        }
)

public interface SmartHomeComponents {
    void inject(SmartHomeApplication smartHomeApplication);

    void inject(LoginFragment loginFragment);

    void inject(LoginActivity loginActivity);

    void inject(LoginViewModel loginViewModel);

    final class Initializer {
        public static SmartHomeComponents init(ApplicationModule applicationModule,
                                          ServiceModule serviceModule,
                                          DataModule dataModule) {
            return DaggerSmartHomeComponents
                    .builder()
                    .applicationModule(applicationModule)
                    .dataModule(dataModule)
                    .serviceModule(serviceModule)
                    .build();
        }
    }


}
