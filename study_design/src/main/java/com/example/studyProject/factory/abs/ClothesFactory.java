package com.example.studyProject.factory.abs;

import com.example.studyProject.factory.normal.Clothes;
import com.example.studyProject.factory.normal.Jacket;
import com.example.studyProject.factory.normal.Sweater;

public class ClothesFactory extends AbsFactory {
    @Override
    public Trousers getTrousers(String trousers) {
        return null;
    }

    @Override
    public Clothes getClothes(String clothes) {
        switch (clothes) {
            case "Jacket":
                return new Jacket();
            case "Sweater":
                return new Sweater();
        }
        return null;
    }

}
