package com.intellekta.generics.middleearth.warriors;

import com.intellekta.generics.middleearth.army.Cavalry;
import com.intellekta.generics.middleearth.army.Unit;

public abstract class Cavalier<T1 extends Cavalier.Mount> extends Infantryman implements Cavalry {

    private T1 mount;

    @Override
    public boolean IsAlive() {
        return this.getPower() > 0;
    }
    @Override
    public int getPower() {
        return super.getPower()+this.mount.getPower();
    }

    public Cavalier(String name, int minPower, int maxPower) {
        super(name, minPower, maxPower);
    }

    @Override
    public <T extends Unit> void strike(T unit){
        if(unit instanceof Cavalier){
            ((Cavalier) unit).reducePower(this.getPower() + this.getMount().getPower());
        } else
            ((Infantryman) unit).reducePower(this.getPower() + this.getMount().getPower());
    }

    @Override
    public void reducePower(int value) {
        if (this.getMount().getPower() > 0) {
            this.getMount().reducePower(value);
        } else {
            super.reducePower(value);
        }
    }

    public void setMount(T1 mount) {
            this.mount = mount;
    }

    public T1 getMount() {
            return this.mount;
    }

    public abstract class Mount implements Unit{
        private int maxPower;
        private int minPower;
        private int power;
        @Override
        public boolean IsAlive() {
            return false;
        }
        public Mount(int maxPower, int minPower) {
            this.power = (int)(Math.random()*(maxPower-minPower+1))+minPower;
        }
        public int getPower() {
            return power;
        }
        public void reducePower(int value) {
            this.power -= value;
        }
    }

    public class Horse extends Mount {
        public Horse() {
            super(4, 6);
        }

        @Override
        public <T extends Unit> void strike(T unit) {

        }
    }

    public class Warg extends Mount {
        public Warg() {
            super(4, 7);
        }

        @Override
        public <T extends Unit> void strike(T unit) {

        }
    }

}
