package com.example.studyProject.studyDesign.factory.normal;

/**
 * 普通工厂模式
 **/
public class WardrobeFactory {
    public final static String CLOTHE_JACK = "clothes_jack";
    public final static String CLOTHE_SWEATER = "clothe_sweater";

    public Clothes getShape(String shape) {
        switch (shape) {
            case CLOTHE_JACK:
                return new Jacket();
            case CLOTHE_SWEATER:
                return new Sweater();
        }
        return null;
    }

}
