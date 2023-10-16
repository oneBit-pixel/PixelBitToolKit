package com.example.studyProject.studyDesign.factory.abs;

import com.example.studyProject.studyDesign.factory.normal.Clothes;

/**
 * 抽象工厂模式
 * 该模式实现衣服、裤子都可以实现创建
 * <p>
 * 抽象工厂：是对 工厂的封装 抽象工厂——>多个子工厂——>工厂——>具体类
 * 可有由很多个工厂一起实现
 * 选择对应的工厂创建，再由工厂
 *
 * @create 2023/9/17
 **/

public abstract class AbsFactory {
    public abstract Trousers getTrousers(String trousers);

    public abstract Clothes getClothes(String clothes);

}
