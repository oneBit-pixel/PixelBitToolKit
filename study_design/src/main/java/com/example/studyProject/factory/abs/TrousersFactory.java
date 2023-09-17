package com.example.studyProject.factory.abs;

import com.example.studyProject.factory.normal.Clothes;

public class TrousersFactory extends AbsFactory {
    public final static String TROUSERS_JEANS = "trousers_jeans";
    public final static String TROUSERS_SHORTS = "trousers_shorts";

    @Override
    public Trousers getTrousers(String trousers) {
        switch (trousers) {
            case TROUSERS_JEANS:
                return new Jeans();
            case TROUSERS_SHORTS:
                return new Shorts();
        }
        //没有则则报错...
        return null;
    }

    @Override
    public Clothes getClothes(String clothes) {

        return null;
    }
}
