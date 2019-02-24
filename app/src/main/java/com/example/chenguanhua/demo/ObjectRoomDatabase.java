package com.example.chenguanhua.demo;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

@Database(entities = {Object.class}, version = 6, exportSchema = false)
public abstract class ObjectRoomDatabase extends RoomDatabase {

    public abstract ObjectDao objectDao();
    private static ObjectRoomDatabase INSTANCE;

    static ObjectRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ObjectRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ObjectRoomDatabase.class, "object_database")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            // Migration is not part of this practical.
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final ObjectDao mDao;


        PopulateDbAsync(ObjectRoomDatabase db) {
            mDao = db.objectDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate the database
            // when it is first created
            mDao.deleteAll();
            Object o = new Object("Code","Name","Line");
            Object b = new Object("Code1","Name1","Line1");

            mDao.insert(o);
            Log.d("WEIRD", "name = " + o.getCode());
            Log.d("WEIRD", "name = " + o.getName());
            Log.d("WEIRD", "line = " + o.getLine());

            mDao.insert(b);
            return null;
        }
    }
}
