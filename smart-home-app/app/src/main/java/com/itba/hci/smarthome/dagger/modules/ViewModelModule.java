package com.itba.hci.smarthome.dagger.modules;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

public class ViewModelModule implements ViewModelProvider.Factory {

    private Map<Class<ViewModel>, Provider<ViewModel>> creators;

    @Inject
    public ViewModelModule(Map<Class<ViewModel>, Provider<ViewModel>> creators) {
        this.creators = creators;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (!creators.containsKey(modelClass))
            throw new IllegalArgumentException("unknown model class $modelClass");

        return (T) creators.get(modelClass);
    }

}