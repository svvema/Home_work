package com.geek.rpg.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class RpgGame extends ApplicationAdapter {
    SpriteBatch batch;
    Background background;
    Hero hero;
    Monster monster;
    ArrayList<Monster> monsters;
    AbstractUnit currentUnit;

    @Override
    public void create() {
        monsters = new ArrayList<Monster>();
        batch = new SpriteBatch();
        background = new Background();
        hero = new Hero();
        hero.setPosition(new Vector2(400, 200));
        //monster = new Monster();
        //monster.setPosition(new Vector2(700, 200));
        monsters.add(new Monster());
        monsters.add(new Monster());
        monsters.add(new Monster());
        monsters.get(0).setPosition(new Vector2(700, 200));
        monsters.get(1).setPosition(new Vector2(800, 400));
        monsters.get(2).setPosition(new Vector2(900, 100));
        currentUnit = hero;
    }

    @Override
    public void render() {
        float dt = Gdx.graphics.getDeltaTime();
        update(dt);
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.render(batch);
        hero.renderHP(batch);
      //  monster.renderHP(batch);
        hero.render(batch);
        for (int i = 0; i < monsters.size(); i++) {
            monsters.get(i).render(batch);
            monsters.get(i).renderHP(batch);
        }

        batch.end();

    }

    public void update(float dt) {

        hero.update(dt);
        for (int i = 0; i < monsters.size(); i++) {
            monsters.get(i).update(dt);
        }
        if (currentUnit == hero) {

            if (InputHandler.checkClickInRect(monsters.get(0).rect) && monsters.get(0).hp > 0) {
                hero.meleeAttack(monsters.get(0));
                currentUnit = monsters.get(0);
            }
        }

            if (currentUnit instanceof Monster) {
                for (int i = 0; i <monsters.size(); i++) {
                    if (InputHandler.checkClickInRect(hero.rect)) {
                        monsters.get(i).meleeAttack(hero);
                        currentUnit = monsters.get(i);
                }
                  currentUnit = hero;
                }
            }
    }
    @Override
    public void dispose() {
        batch.dispose();
    }
}
