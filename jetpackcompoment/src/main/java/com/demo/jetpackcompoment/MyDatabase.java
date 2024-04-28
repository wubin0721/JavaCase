package com.demo.jetpackcompoment;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.demo.jetpackcompoment.room.StudenDao;
import com.demo.jetpackcompoment.room.Student;

@Database(entities = {Student.class},version = 1,exportSchema = false)
public abstract class MyDatabase extends RoomDatabase{
    //此处要构造单例模式
    public static final String DATABASE_NAME = "student.db";

    private static MyDatabase mInstance;

    public static synchronized MyDatabase getInstance(Context context){
        if(mInstance == null){
            mInstance = Room.databaseBuilder(context.getApplicationContext(),
                    MyDatabase.class,
                    DATABASE_NAME).build();
        }
        return  mInstance;
    }


    public abstract StudenDao getStudentDao();
}
