package com.geek.rpg.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Monster extends AbstractUnit {
    public Monster() {
        this.texture = new Texture("charSkeleton.tga");
        this.textureHP = new Texture("hp.png");
        this.name = "Skelet";
        this.maxHp = 15;
        this.hp = this.maxHp;
        this.level = 1;
        this.strength = 8;
        this.dexterity = 50;
        this.endurance = 5;
        this.spellpower = 0;
        this.defence = 1;
        this.flip = true;
    }
}
