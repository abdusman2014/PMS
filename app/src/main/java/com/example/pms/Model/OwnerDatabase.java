package com.example.pms.Model;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(entities = {Owner.class,Pot.class}, version  = 5)
public abstract class OwnerDatabase extends RoomDatabase {
    public abstract OwnerDao userDao();
    public abstract PotDao potDao();

    private static OwnerDatabase INSTANCE;

    public static OwnerDatabase getDbInstance(Context context) {

        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, OwnerDatabase.class, "DB_NAME")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();

        }
        return INSTANCE;
    }
}
