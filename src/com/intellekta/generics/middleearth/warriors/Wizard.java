package com.intellekta.generics.middleearth.warriors;

import com.intellekta.generics.middleearth.army.MiddleEarthUnit;

public class Wizard extends Cavalier implements MiddleEarthUnit {

    public Wizard(String name) {
        super(name, 20, 20);
        setMount(this.new Horse());
    }
}
