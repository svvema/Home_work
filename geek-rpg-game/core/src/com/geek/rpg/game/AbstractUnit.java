package com.geek.rpg.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;

public abstract class AbstractUnit {
    protected Texture texture;
    protected Texture textureHP;

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
    protected Label.LabelStyle labelStyle = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
    protected Label hpLabel = new Label("",labelStyle);
    protected Label missLabel = new Label("",labelStyle);
    protected boolean dodge;
    public Rectangle getRect() {
        return rect;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
        this.rect = new Rectangle(position.x, position.y, texture.getWidth(), texture.getHeight());
    }

    public void takeDamage(int dmg) {
        this.dodge = false;
        this.takeDamageAction = 1.0f;
        if (dexterity*1f/100>Math.random()) {
            //hpLabel.setScale(1,1);
            this.dodge = true;
        }
            else
        if (hp-dmg>0){
        hp -= dmg;}
        else hp =0;
    }

    public void render(SpriteBatch batch) {
        if (hp>0){
        if (takeDamageAction > 0 && !dodge) {
            batch.setColor(1f, 1f - takeDamageAction, 1f - takeDamageAction, 1f);
        }
        else {
            missLabel.setText("MISS");
            missLabel.setFontScale(3f,3f);
            missLabel.setPosition(position.x,position.y+texture.getHeight()+ 20f);

            missLabel.draw(batch,(float) Math.sin((1f - takeDamageAction) * 3.14f));
        }
        float dx = (50f * (float) Math.sin((1f - attackAction) * 3.14f));
        if (flip) dx *= -1;
        batch.draw(texture, position.x + dx, position.y, 0, 0, texture.getWidth(), texture.getHeight(), 1, 1, 0, 0, 0, texture.getWidth(), texture.getHeight(), flip, false);
        batch.setColor(1f, 1f, 1f, 1f);}
        else        batch.draw(texture, position.x, position.y-texture.getWidth()/4, texture.getWidth()/2, texture.getHeight()/2, texture.getWidth(), texture.getHeight(), 1, 1, 270, 0, 0, texture.getWidth(), texture.getHeight(), flip, false);

    }
    public void renderHP(SpriteBatch batch){

        String s = Integer.toString(hp);
        hpLabel.setText(s);

        float dx = (50f * (float) Math.sin((1f - attackAction) * 3.14f));
        if (flip) dx *= -1;
        hpLabel.setPosition(position.x+dx + textureHP.getWidth()/2,position.y+ texture.getHeight() + textureHP.getHeight()/2+10);

        if (hp>0){
        batch.draw(new Texture("hpBack.png"),position.x+dx, position.y+texture.getHeight()+10,0,0,textureHP.getWidth(),textureHP.getHeight(),1,1,0,0,0,textureHP.getWidth(),textureHP.getHeight(),flip,false);



        batch.draw(textureHP,position.x+dx, position.y+texture.getHeight()+10,0,0,textureHP.getWidth(),textureHP.getHeight(),(1.0f*hp)/(1.0f*maxHp),1f,0,0,0,textureHP.getWidth(),textureHP.getHeight(),flip,false);

        batch.setColor(1f, 1f, 1f, 1f);

        hpLabel.draw(batch,1);

        }

        if(hp<=0) {hpLabel.setText("DEAD");
        hpLabel.setFontScale(5f,5f);
        hpLabel.draw(batch,takeDamageAction);}


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
