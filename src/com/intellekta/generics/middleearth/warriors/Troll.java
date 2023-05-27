package com.intellekta.generics.middleearth.warriors;

import com.intellekta.generics.middleearth.army.MordorUnit;


public class Troll extends Infantryman implements MordorUnit {

    public Troll(String name) {
        super(name, 11, 15);
    }
}
