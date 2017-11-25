package com.geek.rpg.game;

import com.badlogic.gdx.Gdx;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by FlameXander on 16.11.2017.
 */

public class Hero implements Serializable {
    private Unit[][] units;

    public Unit[][] getUnits() {
        return units;
    }

    public Hero() {
        units = new Unit[2][3];
    }

    public void setArmy(Unit... inUnits) {
        int counter = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                units[j][i] = inUnits[counter];
                if(units[j][i] != null) {
                    units[j][i].setHero(this);
                }
                counter++;
            }
        }
    }
}
