package com.geek.rpg.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Hero extends AbstractUnit {
    public Hero() {
        this.texture = new Texture("charTank.tga");
        this.textureHP = new Texture("hp.png");

        this.name = "Alexander";
        this.maxHp = 50;
        this.hp = this.maxHp;
        this.level = 1;
        this.strength = 7;
        this.dexterity = 50;
        this.endurance = 10;
        this.spellpower = 10;
        this.defence = 5;
        this.flip = false;
    }
}
