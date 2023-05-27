package com.intellekta.generics.middleearth.army;

import java.util.ArrayList;

public class Army<T extends Unit> {
    private ArrayList<Cavalry> cavalry = new ArrayList<>();
    private ArrayList<Infantry> infantry = new ArrayList<>();

    public ArrayList<Cavalry> getCavalry() {
        return cavalry;
    }

    public ArrayList<Infantry> getInfantry() {
        return infantry;
    }
    public ArrayList<Unit> getArmy(){
        ArrayList<Unit> army = new ArrayList<>();
        army.addAll(cavalry);
        army.addAll(infantry);
        return army;
    }
    public boolean recruit(T unit){
        if(unit instanceof Cavalry){
            cavalry.add((Cavalry)unit);
            return true;
        } else if(unit instanceof Infantry){
            infantry.add((Infantry)unit);
            return true;
        } else return false;
    }

    public void print(){
       ArrayList<Unit> printList = getArmy();
       for(Unit unit : printList){
           System.out.println(unit.toString());
       }
    }

    public boolean release(T warrior){
        if(warrior instanceof Cavalry){
            cavalry.remove(warrior);
            return true;
        } else if(warrior instanceof Infantry){
            infantry.remove(warrior);
            return true;
        } else return false;
    }

    public Unit getRandomUnit(){
        int randInd = (int)(Math.random()*(getArmy().size()-1));
        try {
            return getArmy().get(randInd);
        } catch (Exception e){
            return null;
        }
    }

    public Unit getRandomUnit(T unit){
        int randInd;
        if(unit instanceof Cavalry){
            randInd = (int)(Math.random()*(getCavalry().size()-1));
            return getCavalry().get(randInd);
        } else if (unit instanceof Infantry){
            randInd = (int)(Math.random()*(getInfantry().size()-1));
            return getInfantry().get(randInd);
        } else return null;
    }

}
