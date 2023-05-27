package com.intellekta.generics.middleearth.warriors;

public class CavalierHuman extends Cavalier implements Human {

    public CavalierHuman(String name) {
        super(name, 7, 8);
        setMount(this.new Horse());
    }

}
