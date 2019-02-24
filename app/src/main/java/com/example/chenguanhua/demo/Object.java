package com.example.chenguanhua.demo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "object_table")
public class Object {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String code;

    private String name;

    private String line;

    public Object(String code, String name, String line)
    {
        this.code = code;
        this.name = name;
        this.name = line;
    }
    public int getId()
    {
        return id;
    }

    public String getCode()
    {
        return code;
    }

    public String getName()
    {
        return name;
    }

    public String getLine()
    {
        return line;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setLine(String line)
    {
        this.line = line;
    }
}
