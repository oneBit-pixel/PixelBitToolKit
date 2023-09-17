package com.example.studyProject.single;

//双重锁
public class Singleton{

    /**
     * 1.volatile 保证给instance的可见性
     *    确保所有线程看到的instance值都是最新的
     * 2.防止命令重排
     *      1.分配内存
     *      2.初始化对象
     *      3.将内存地址赋给引用变量
     *  如果没有则可能导致 2，3位置调换
     */
    private volatile static Singleton instance;
    private Singleton(){

    }

    public static Singleton getInstance(){
        if(instance==null){
            synchronized (Singleton.class){
                if(instance==null)
                    instance=new Singleton();
            }
        }
        return instance;
    }
}

