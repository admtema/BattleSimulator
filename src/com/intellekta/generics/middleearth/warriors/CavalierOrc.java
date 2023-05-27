package com.intellekta.generics.middleearth.warriors;

public class CavalierOrc extends Cavalier implements Orc {
    public CavalierOrc(String name) {
        super(name, 8, 10);
        setMount(this.new Warg());
    }
}
