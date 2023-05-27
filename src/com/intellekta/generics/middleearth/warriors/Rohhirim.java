package com.intellekta.generics.middleearth.warriors;


public class Rohhirim extends Cavalier implements Human {
    public Rohhirim(String name) {
        super(name, 7, 8);
        setMount(this.new Horse());
    }
}
