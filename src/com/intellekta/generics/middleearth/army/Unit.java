package com.intellekta.generics.middleearth.army;


public interface Unit {

boolean IsAlive();

<T extends Unit> void strike(T unit);
}


