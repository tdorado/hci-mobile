package com.itba.hci.smarthome.dagger.components;

import com.itba.hci.smarthome.core.SmartHomeApplication;
import com.itba.hci.smarthome.dagger.modules.ActivityModule;
import com.itba.hci.smarthome.dagger.modules.ApplicationModule;
import com.itba.hci.smarthome.dagger.modules.DataModule;
import com.itba.hci.smarthome.dagger.modules.FragmentModule;
import com.itba.hci.smarthome.dagger.modules.ServiceModule;
import com.itba.hci.smarthome.model.viewModel.DeviceViewModel;
import com.itba.hci.smarthome.view.activity.DevicesActivity;
import com.itba.hci.smarthome.view.fragment.DevicesFragment;

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

    void inject(DevicesActivity devicesActivity);

    void inject(DevicesFragment devicesFragment);

    void inject(DeviceViewModel deviceViewModel);

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
