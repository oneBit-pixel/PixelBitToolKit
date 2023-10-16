package com.example.studyProject.studyDesign.factory.abs;

import com.example.studyProject.studyDesign.factory.normal.Clothes;
import com.example.studyProject.studyDesign.factory.normal.Jacket;
import com.example.studyProject.studyDesign.factory.normal.Sweater;

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
