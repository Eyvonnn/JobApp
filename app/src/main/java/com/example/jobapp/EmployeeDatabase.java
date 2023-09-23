package com.example.jobapp;
import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {EmployeeEntity.class}, version = 1)
public abstract class EmployeeDatabase extends RoomDatabase {

    private static final String DB_NAME = "employee";
    private static EmployeeDatabase instance;

    public static synchronized EmployeeDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    EmployeeDatabase.class,
                    DB_NAME
            ).fallbackToDestructiveMigration().build();
        }
        return instance;
    }

    public abstract EmployeeDao employeeDao();
}

