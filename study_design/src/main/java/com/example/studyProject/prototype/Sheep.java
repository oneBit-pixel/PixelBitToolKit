package com.example.studyProject.prototype;

import android.graphics.drawable.shapes.Shape;

public class Sheep implements Cloneable {

    String name;

    public Sheep(String name) {
        this.name = name;
    }

    /**
     * 浅拷贝：
     * 只对当前引用对象创建一个拷贝，
     * 里面的内容引用的对象 还是一样的
     *
     * @create 2023/9/17
     **/

    public Object clone() {
        Object obj = null;
        try {
            obj = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * 深拷贝:
     * 对里面的值也从创建新的副本
     *
     * @create 2023/9/17
     **/

    public Sheep deepCopy() {
        String s = name;
        //如果其中是应用列表则创建新的对象
        /**
         *        Sheep newSheep = new Sheep(name);
         *         for (String friend : this.friends) {
         *             newSheep.addFriend(friend);
         *         }
         *
         * @create 2023/9/17
         **/

        Sheep newSheep = new Sheep(s);
        return newSheep;
    }
}

