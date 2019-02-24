package com.example.chenguanhua.demo;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class ObjectRepository {

    private ObjectDao mObjectDao;
    private LiveData<List<Object>> mAllObjects;


    ObjectRepository(Application application) {
        ObjectRoomDatabase db = ObjectRoomDatabase.getDatabase(application);
        mObjectDao = db.objectDao();
        mAllObjects = mObjectDao.getAllObjects();
    }

    LiveData<List<Object>> getAllObjects() {
        return mAllObjects;
    }

    public void insert (Object object) {
        new insertAsyncTask(mObjectDao).execute(object);
    }

    private static class insertAsyncTask extends AsyncTask<Object, Void, Void> {

        private ObjectDao mAsyncTaskDao;

        insertAsyncTask(ObjectDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Object... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

}
