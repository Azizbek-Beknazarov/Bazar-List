package com.example.roomdemo2.Model;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


@Database(entities = {BazarData.class}, version = 1, exportSchema = false)
public abstract class BazarDB extends RoomDatabase {

    private static BazarDB database;

    public abstract BazarDao bazarDao();

    private static String DATABASE_NAME = "Bazar";

    public synchronized static BazarDB getInstance(Context context) {

        if (database == null) {
            database = Room.databaseBuilder(context.getApplicationContext()
                    , BazarDB.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .addCallback(sCallback)
                    .build();
        }
        return database;
    }

    private static RoomDatabase.Callback sCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDBAsyncTask(database);
        }
    };

    private static class PopulateDBAsyncTask extends AsyncTask<Void, Void, Void> {
        private BazarDao mDao;

        public PopulateDBAsyncTask(BazarDB bazarDB) {
            mDao = bazarDB.bazarDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
//            mDao.insert(new BazarData());
            return null;
        }
    }
}
