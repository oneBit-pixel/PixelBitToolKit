package com.example.studyProject.observer;

import java.util.Observable;

//被观察者
//添加 addobserver() 观察值
public class Student extends Observable {
    private String data = "";

    public void setData(String data) {
        if (!this.data.equals(data)) {
            this.data = data;
            setChanged(); // 标记状态已改变
            notifyObservers(data); // 通知所有观察者
        }
    }

}
