package com.intellekta.generics.middleearth.warriors;

import com.intellekta.generics.middleearth.army.MordorUnit;

public class Goblin extends Infantryman implements MordorUnit {
    public Goblin(String name) {
        super(name, 2, 5);
    }
}
