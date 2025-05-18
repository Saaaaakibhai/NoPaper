package com.example.nopaper.ui.gallery;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GalleryViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public GalleryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Welcome to No Paper app\n" +
                "" +
                "\nThis is gallery tab");
    }

    public LiveData<String> getText() {
        return mText;
    }
}