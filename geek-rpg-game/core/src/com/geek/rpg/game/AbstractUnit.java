package com.geek.rpg.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class AbstractUnit {
    protected Texture texture;
    protected String name;
    protected int hp;
    protected int maxHp;

    protected int level;

    protected Rectangle rect;

    // Primary Stats
    protected int strength;
    protected int dexterity;
    protected int endurance;
    protected int spellpower;

    // Secondary Stats
    protected int defence;

    protected Vector2 position;

    protected boolean flip;

    protected float attackAction;
    protected float takeDamageAction;

    public Rectangle getRect() {
        return rect;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
        this.rect = new Rectangle(position.x, position.y, texture.getWidth(), texture.getHeight());
    }

    public void takeDamage(int dmg) {
        this.takeDamageAction = 1.0f;
        hp -= dmg;
    }

    public void render(SpriteBatch batch) {
        if (takeDamageAction > 0) {
            batch.setColor(1f, 1f - takeDamageAction, 1f - takeDamageAction, 1f);
        }
        float dx = (50f * (float) Math.sin((1f - attackAction) * 3.14f));
        if (flip) dx *= -1;
        batch.draw(texture, position.x + dx, position.y, 0, 0, texture.getWidth(), texture.getHeight(), 1, 1, 0, 0, 0, texture.getWidth(), texture.getHeight(), flip, false);
        batch.setColor(1f, 1f, 1f, 1f);
    }

    public void update(float dt) {
        if (takeDamageAction > 0) {
            takeDamageAction -= dt;
        }
        if (attackAction > 0) {
            attackAction -= dt;
        }
    }

    public void meleeAttack(AbstractUnit enemy) {
        int dmg = this.strength - enemy.defence;
        if (dmg < 0) {
            dmg = 0;
        }
        this.attackAction = 1.0f;
        enemy.takeDamage(dmg);
    }
}
