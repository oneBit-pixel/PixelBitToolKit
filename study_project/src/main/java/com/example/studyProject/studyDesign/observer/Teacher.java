package com.example.studyProject.studyDesign.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

//抽象类
public class Teacher implements Observer {
    private final String name;
    private List<Student> mStudents = new ArrayList<>();

    private int index;

    public Teacher(String name) {
        this.name = name;
    }

    //添加观察者
    public void attach(Student student) {
        this.mStudents.add(student);
    }

    public void dispatchHomework(int index) {
        this.index = index;

    }

    public void dispatchHomeWork(int index) {
        this.index = index;

    }

    public void notifyAllStudent() {
        for (Student stu : mStudents) {
//            stu.doHomeWork(index);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        String string = (String) arg;
        System.out.println(name+"==> change ==> " + string);
    }
}
