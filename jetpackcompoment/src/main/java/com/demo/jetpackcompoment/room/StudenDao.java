package com.demo.jetpackcompoment.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StudenDao {

    @Insert
    void insertStudent(Student... students);

    @Delete
    void deleteStudent(Student... students);

    @Update
    void updateStudent(Student... students);

    @Query("SELECT * FROM student WHERE id = :id")
    List<Student> getALLStudentById(int id);

    @Query("SELECT * FROM student")
    LiveData<List<Student>> getALLStudent();
}
