package com.example.studyProject.proxy;

public class RealImage implements DataBase {

    @Override
    public void select() {
        System.out.println("查询");
    }
}


