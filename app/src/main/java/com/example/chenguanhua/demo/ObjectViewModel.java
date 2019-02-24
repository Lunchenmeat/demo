package com.example.chenguanhua.demo;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class ObjectViewModel extends AndroidViewModel {


    private ObjectRepository mRepository;
    private LiveData<List<Object>> mAllObjects;

    public ObjectViewModel (Application application) {
        super(application);
        mRepository = new ObjectRepository(application);
        mAllObjects = mRepository.getAllObjects();
    }

    LiveData<List<Object>> getAllObjects() { return mAllObjects; }

    public void insert(Object object) { mRepository.insert(object); }
}
