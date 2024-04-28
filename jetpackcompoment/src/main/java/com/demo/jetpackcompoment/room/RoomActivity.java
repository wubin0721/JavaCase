package com.demo.jetpackcompoment.room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.demo.jetpackcompoment.MyDatabase;
import com.demo.jetpackcompoment.MyViewModel;
import com.demo.jetpackcompoment.R;
import com.demo.jetpackcompoment.room.StudenDao;

import java.util.List;

public class RoomActivity extends AppCompatActivity {

    private StudenDao studenDao;
    private StudentViewModel studentViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        studentViewModel = new ViewModelProvider(this, new ViewModelProvider
                .AndroidViewModelFactory(getApplication()))
                .get(StudentViewModel.class);
        studentViewModel.getAllstudents().observe(this, new Observer<List<Student>>() {
            @Override
            public void onChanged(List<Student> students) {
                //发生变化的操作
            }
        });
    }

    //插入操作
    public void Insert(View view) {
        Student student1 = new Student("john",18);
        Student student2 = new Student("merry",22);
        studentViewModel.insertStudent(student1,student2);
    }

    //删除操作
    public void Delete(View view) {
        //删除student1
        Student student1 = new Student(1);
        studentViewModel.deleteStudent(student1);
    }

    //修改操作
    public void Update(View view) {
        //修改student1属性
        Student student1 = new Student("ben",28);
        studentViewModel.updateStudent(student1);
    }

    //查询操作
    public void Query(View view) {
        studentViewModel.getAllstudents();
    }








}