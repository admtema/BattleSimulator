package com.intellekta.generics.middleearth.warriors;


import com.intellekta.generics.middleearth.army.MiddleEarthUnit;

public class Elf extends Infantryman implements MiddleEarthUnit {

    public Elf(String name) {
        super(name, 4, 7);
    }
}
