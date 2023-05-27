package com.intellekta.generics.middleearth.warriors;

import com.intellekta.generics.middleearth.army.MordorUnit;

public class InfantryOrc extends Infantryman implements MordorUnit {

    public InfantryOrc(String name) {
        super(name, 8, 10);
    }
}
