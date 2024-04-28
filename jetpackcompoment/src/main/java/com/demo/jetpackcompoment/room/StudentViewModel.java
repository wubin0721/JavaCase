package com.demo.jetpackcompoment.room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class StudentViewModel extends AndroidViewModel {

    private StudentRepository studentRepository;

    public StudentViewModel(@NonNull Application application) {
        super(application);

        studentRepository = new StudentRepository(application);
    }

    public void insertStudent(Student... students){
        studentRepository.Insert(students);
    }

    public void deleteStudent(Student... students){
       studentRepository.Delete(students);
    }


    public LiveData<List<Student>> getAllstudents(){
        return studentRepository.getAllstudents();
    }

    public void updateStudent(Student... students){
        studentRepository.Update(students);
    }
}
