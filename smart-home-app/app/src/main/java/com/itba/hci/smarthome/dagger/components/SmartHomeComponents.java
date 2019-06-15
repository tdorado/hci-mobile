package com.itba.hci.smarthome.dagger.components;

import com.itba.hci.smarthome.core.SmartHomeApplication;
import com.itba.hci.smarthome.dagger.modules.ActivityModule;
import com.itba.hci.smarthome.dagger.modules.ApplicationModule;
import com.itba.hci.smarthome.dagger.modules.DataModule;
import com.itba.hci.smarthome.dagger.modules.FragmentModule;
import com.itba.hci.smarthome.dagger.modules.ServiceModule;
import com.itba.hci.smarthome.db.AcActionsRepository;
import com.itba.hci.smarthome.db.AlarmActionsRepository;
import com.itba.hci.smarthome.db.BlindsActionsRepository;
import com.itba.hci.smarthome.db.DoorActionsRepository;
import com.itba.hci.smarthome.db.LampActionsRepository;
import com.itba.hci.smarthome.db.OvenActionsRepository;
import com.itba.hci.smarthome.model.viewModel.DeviceViewModel;
import com.itba.hci.smarthome.model.viewModel.EditDeviceViewModel;
import com.itba.hci.smarthome.model.viewModel.NewDeviceViewModel;
import com.itba.hci.smarthome.model.viewModel.RoutinesViewModel;
import com.itba.hci.smarthome.view.activity.DevicesActivity;
import com.itba.hci.smarthome.view.activity.EditDeviceActivity;
import com.itba.hci.smarthome.view.activity.NewDeviceActivity;
import com.itba.hci.smarthome.view.activity.RoutinesActivity;
import com.itba.hci.smarthome.view.fragment.DevicesFragment;
import com.itba.hci.smarthome.view.fragment.NewDeviceFragment;
import com.itba.hci.smarthome.view.fragment.RoutinesFragment;

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

    void inject(NewDeviceActivity newDeviceActivity);

    void inject(NewDeviceFragment newDeviceFragment);

    void inject(NewDeviceViewModel newDeviceViewModel);

    void inject(RoutinesActivity routinesActivity);

    void inject(RoutinesFragment routinesFragment);

    void inject(RoutinesViewModel routinesViewModel);

    void inject(EditDeviceViewModel editDeviceViewModel);

    void inject(EditDeviceActivity editDeviceActivity);

    void inject(AcActionsRepository acActionsRepository);

    void inject(AlarmActionsRepository alarmActionsRepository);

    void inject(BlindsActionsRepository blindsActionsRepository);

    void inject(OvenActionsRepository ovenActionsRepository);

    void inject(DoorActionsRepository doorActionsRepository);

    void inject(LampActionsRepository lampActionsRepository);


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
