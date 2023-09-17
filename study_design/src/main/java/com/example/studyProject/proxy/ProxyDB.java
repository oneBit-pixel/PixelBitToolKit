package com.example.studyProject.proxy;

//代理模式
public class ProxyDB implements DataBase {

    RealImage realImage;

    public ProxyDB() {
        if (realImage == null) {
            realImage = new RealImage();
        }
    }

    @Override
    public void select() {
        realImage.select();
    }
}
