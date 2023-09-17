package com.example.studyProject.factory.abs;

public class WardrobeFactory {
    public static AbsFactory getFactory(String choice) {
        switch (choice) {
            case "clothes":
                return new ClothesFactory();
            case "Trousers":
                return new TrousersFactory();
        }
        return null;
    }
}
