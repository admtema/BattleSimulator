package com.intellekta.generics.middleearth.battle;

import com.intellekta.generics.middleearth.army.*;
import com.intellekta.generics.middleearth.warriors.*;

import java.util.ArrayList;

public class Battle {
    private static final int minArmyNumber = 10;
    private static final int maxArmyNumber = 20;

    public static void main(String[] args) {
        fight();
    }

    public static void fight(){
        Army<Unit> mordor = new Army<>();
        Army<Unit> middleEarth = new Army<>();
        // Определение численности армий
        int mordorNum = (int)(Math.random()*(maxArmyNumber-minArmyNumber)+1)+minArmyNumber;
        int mEarthNum = 0;
        while(mEarthNum < 0.8*mordorNum || mEarthNum > 1.2*mordorNum) {
            mEarthNum = (int)(Math.random()*(maxArmyNumber-minArmyNumber)+1)+minArmyNumber;
        }

        //Заполнение армий случайными юнитами
        recruitRandomMordor(mordor,mordorNum);
        recruitRandomMEarth(middleEarth,mEarthNum);

        fight(mordor,middleEarth);
    }


    public static void fight(Army<Unit> mordor, Army<Unit> middleEarth){
        // Объявление состава армий
        int phase = 1;
        System.out.println("Army of MordorUnit consists of: " );
        mordor.print();
        System.out.println("Army of MiddleEarthUnit consists of: ");
        middleEarth.print();

        //Фаза дуэлей кавалерии
        System.out.printf("\n Phase %d \n\n", phase);
        while(!phaseWinnersCheck(mordor.getCavalry(), middleEarth.getCavalry(), phase)){
           Cavalier mordorCav = (Cavalier)mordor.getRandomUnit(new CavalierOrc("MordorCavalierType"));
           Cavalier mEarthCav = (Cavalier)middleEarth.getRandomUnit(new CavalierHuman("MEarthCavalierType"));
           if (Math.random() < 0.5)
                duel(mordorCav,mEarthCav);
             else duel(mEarthCav, mordorCav);
            removeDeads(mordorCav, mordor);
            removeDeads(mEarthCav, middleEarth);
        }

        phase++;

        //Фаза дуэлей пехоты
        System.out.printf("\nPhase %d \n\n", phase);
        while(!phaseWinnersCheck(mordor.getInfantry(), middleEarth.getInfantry(), phase)) {
            Infantryman mordorInf = (Infantryman)mordor.getRandomUnit(new InfantryOrc("MordorInfantryType"));
            Infantryman mEarthInf = (Infantryman) middleEarth.getRandomUnit(new InfantryHuman("MEarthInfantryType"));
            if (Math.random() < 0.5)
                duel(mordorInf, mEarthInf);
            else duel(mEarthInf, mordorInf);
            removeDeads(mordorInf, mordor);
            removeDeads(mEarthInf, middleEarth);
        }

        /*Если в армиях остались юниты, и победитель не определен,
        тогда 3 фаза дуэлей c юнитами любого типа, и снова проверка победителя */
        if (!battleWinnersCheck(mordor,middleEarth)){
            phase++;
            System.out.printf("\n Phase %d \n\n", phase);
            while(!battleWinnersCheck(mordor,middleEarth)){
                Unit mordorUnit = mordor.getRandomUnit();
                Unit mEarthUnit = middleEarth.getRandomUnit();
                if (Math.random() < 0.5)
                    duel(mordorUnit, mEarthUnit);
                else duel(mEarthUnit, mordorUnit);
                removeDeads(mordorUnit, mordor);
                removeDeads(mEarthUnit, middleEarth);
            }
        }
    }

    public static<T extends Unit> void duel(T fighter1, T fighter2){

        //Метод производит обмен ударами юнитов
        String f1 = fighter1.toString();
        String f2 = fighter2.toString();
        String kill = " and kills him";
        String alive = " and does not kill him";
            fighter1.strike(fighter2);
            if(!fighter2.IsAlive()){
                System.out.println(f1 + " strikes " + f2 + kill);
            } else {
                System.out.println(f1 + " strikes " + f2 + alive);
                fighter2.strike(fighter1);
                if(!fighter1.IsAlive()){
                    System.out.println(f2 + " strikes " + f1 + kill);
                } else System.out.println(f2 + " strikes " + f1 + alive);
            }
    }


    public static void removeDeads(Unit unit, Army<Unit> army){
        //Метод удаляет из армии юнита, если тот погиб
        if(!unit.IsAlive()){
            army.release(unit);
        }
    }

    public static boolean phaseWinnersCheck(ArrayList <? extends Unit> mordor,
                                            ArrayList<? extends Unit> middleEarth, int phase) {
        //Метод проверяет опустевшие кавалерии/пехоты и выявляет победителя фазы битвы
        if(mordor.isEmpty()){
            System.out.println("\nArmy of MiddleEarth has won the "+ phase + " phase. The winners list:" );
            for(Unit unit : middleEarth){
                System.out.println(unit.toString());
            }
            return true;
        } else if (middleEarth.isEmpty()){
            System.out.println("\nArmy of Mordor has won the "+ phase + " phase. The winners list:" );
            for(Unit unit : mordor){
                System.out.println(unit.toString());
            }
            return true;
        } else return false;
    }


    public static boolean battleWinnersCheck(Army<Unit> mordor, Army<Unit> middleEarth){
        //Метод проверяет опустевшие армии и выявляет победителя всей битвы
        if(mordor.getArmy().isEmpty()){
            System.out.println("\nArmy of MiddleEarth has won the battle. The winners list:");
            middleEarth.print();
            return true;
        } else if (middleEarth.getArmy().isEmpty()){
            System.out.println("\nArmy of Mordor has won the battle. The winners list:");
            mordor.print();
            return true;
        } else return false;
    }

    public static void recruitRandomMordor (Army<Unit> army, int armyNum) {
        //Метод набирает армию Мордора
        int ind = 1;
        for(int i = 0; i < armyNum; i++){
            int unitTypeNum = (int)((Math.random()*5)+1);
            switch(unitTypeNum){
                case 1: army.recruit(new CavalierOrc("Orc cavalier"+ind));
                    break;
                case 2: army.recruit(new InfantryOrc("Orc infantryman"+ind));
                    break;
                case 3: army.recruit(new Goblin("Goblin"+ind));
                    break;
                case 4: army.recruit(new Troll("Troll"+ind));
                    break;
                case 5: army.recruit(new UrukHai("Urukhai"+ind));
                    break;
            }
            ind++;
        }
    }

    public static void recruitRandomMEarth (Army<Unit> army, int armyNum) {
        //Метод набирает армию Средиземья
        int ind = 1;
        if (Math.random() > 0.5){
            army.recruit(new Wizard("Mighty wizard"+ ind));
            armyNum--;
            ind++;
        }
        for(int i = 0; i < armyNum; i++){
            int unitTypeNum = (int)((Math.random()*5)+1);
            switch(unitTypeNum){
                case 1: army.recruit(new CavalierHuman("Human cavalier"+ ind));
                    break;
                case 2: army.recruit(new InfantryHuman("Human infantryman" + ind));
                    break;
                case 3: army.recruit(new Elf("Elf"+ind));
                    break;
                case 4: army.recruit(new Rohhirim("Rohhirim"+ind));
                    break;
                case 5: army.recruit(new WoodenElf("WoodenElf"+ind));
                    break;
            }
            ind++;
        }
    }
}
