package com.example.roomwordsample;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private Application mApplication;

    public ViewModelFactory(Application mApplication) {
        this.mApplication = mApplication;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(WordViewModel.class)) {
            return (T) new WordViewModel(mApplication);
        }
        throw new IllegalArgumentException("Unable to create viewmodel");
    }
}
