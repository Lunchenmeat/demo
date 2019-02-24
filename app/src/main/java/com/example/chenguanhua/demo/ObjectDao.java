package com.example.chenguanhua.demo;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface ObjectDao {

    @Insert
    void insert(Object object);

    @Delete
    void delete(Object object);

    @Update
    void update(Object object);

    @Query("Delete FROM object_table")
    void deleteAll();

    @Query("SELECT * FROM object_table")
    LiveData<List<Object>> getAllObjects();

}
