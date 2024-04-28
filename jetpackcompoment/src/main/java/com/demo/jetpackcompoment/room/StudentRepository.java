package com.demo.jetpackcompoment.room;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;

import androidx.lifecycle.LiveData;

import com.demo.jetpackcompoment.MyDatabase;

import java.util.List;

public class StudentRepository {

    private StudenDao studenDao;

    public StudentRepository(Context context) {
        MyDatabase myDatabase =MyDatabase.getInstance(context);
        this.studenDao = myDatabase.getStudentDao();
    }

    //插入操作
    public void Insert(Student... students) {
        Student student1 = new Student("john",18);
        Student student2 = new Student("merry",22);
        new InsertStudentTask(studenDao).execute(student1,student2);
    }

    class InsertStudentTask extends AsyncTask<Student,Void,Void> {

        private StudenDao studenDao;

        public InsertStudentTask(StudenDao studenDao) {
            this.studenDao = studenDao;
        }

        @Override
        protected Void doInBackground(Student... students) {
            studenDao.insertStudent(students);
            return null;
        }
    }


    //修改操作
    public void Update(Student... students) {
        //修改student1属性
        Student student1 = new Student("ben",28);
        new UpdateStudentTask(studenDao).execute(student1);
    }

    class UpdateStudentTask extends AsyncTask<Student,Void,Void>{

        private StudenDao studenDao;

        public UpdateStudentTask(StudenDao studenDao) {
            this.studenDao = studenDao;
        }

        @Override
        protected Void doInBackground(Student... students) {
            studenDao.updateStudent(students);
            return null;
        }
    }


    //删除操作
    public void Delete(Student... students) {
        //删除student1
        Student student1 = new Student(1);
        new DeleteStudentTask(studenDao).execute(student1);
    }

    class DeleteStudentTask extends AsyncTask<Student,Void,Void>{

        private StudenDao studenDao;

        public DeleteStudentTask(StudenDao studenDao) {
            this.studenDao = studenDao;
        }

        @Override
        protected Void doInBackground(Student... students) {
            studenDao.deleteStudent(students);
            return null;
        }
    }

    //查询操作
    public LiveData<List<Student>> getAllstudents() {
        return studenDao.getALLStudent();
    }
}
