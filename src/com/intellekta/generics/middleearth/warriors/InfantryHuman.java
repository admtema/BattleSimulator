package com.intellekta.generics.middleearth.warriors;

import com.intellekta.generics.middleearth.army.MiddleEarthUnit;

public class InfantryHuman extends Infantryman implements MiddleEarthUnit {

    public InfantryHuman(String name) {
        super(name, 7, 8);
    }
}
