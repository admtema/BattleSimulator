package com.intellekta.generics.middleearth.warriors;

import com.intellekta.generics.middleearth.army.MiddleEarthUnit;


public class WoodenElf extends Infantryman implements MiddleEarthUnit {

    public WoodenElf(String name) {
        super(name, 6, 6);
    }
}
