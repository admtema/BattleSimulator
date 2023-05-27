package com.intellekta.generics.middleearth.warriors;

import com.intellekta.generics.middleearth.army.Infantry;
import com.intellekta.generics.middleearth.army.Unit;

public abstract class Infantryman implements Infantry {
    private int power;
    private String name;


    @Override
    public boolean IsAlive() {
        return this.power > 0;
    }


    public Infantryman(String name, int minPower, int maxPower) {
        this.power =(int)(Math.random()*(maxPower-minPower+1))+minPower;
        if(name==null || name.isBlank() ||  name.isEmpty())
            this.name = super.toString();
        else
            this.name = name;
    }

    @Override
    public <T extends Unit> void strike(T unit){
        if(unit instanceof Cavalier){
            ((Cavalier) unit).reducePower(this.getPower());
        } else
            ((Infantryman) unit).reducePower(this.getPower());
    }

    public void reducePower(int value) {
        if(this.power > 0)
            this.power -= value;
        if(this.power < 0)
            this.power = 0;
    }

    public void setPower(int power) {
        if(power < this.power)
            this.power = power;
    }

    public int getPower() {
        return power;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() +" " + name
       + " has power " + power;
    }
}
