package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class MyGdxGame extends ApplicationAdapter {


	private static class Obj {
	private Texture texture;
	private Vector2 position;
	private float angle;
	private float scale;
	private Obj2 obj2;
		public Obj(Texture texture,float x, float y, Obj2 obj2) {
			this.texture = texture;
			this.position = new Vector2(x,y);
			this.angle = 0;
			this.scale = 1.0f;
			this.obj2 = obj2;
		}
		public void render(SpriteBatch batch){
			batch.draw(texture, position.x - 64, position.y - 64,64,64,128,128,1,1,angle,0,0,128,128,false,false);

		}
		Vector2 v = new Vector2(300,-500);//init vector
		Vector2 vinit = new Vector2(500,-700);//init vector
		public void update(float dt){
		//	Vector2 m = new Vector2(Gdx.input.getX(),720 - Gdx.input.getY());
			//Vector2 m = move;
			//Vector2 v = m.cpy().sub(position).nor().scl(360.0f);
			if (v.len()>vinit.len()){

				float n = v.len()/vinit.len();
				v.set(v.x/n,v.y/n);
			}





			if ((int)position.y<=64 ){
				v.set(v.x,-v.y);
			}
			if ((int)position.y>=720-64){
				v.set(v.x,-v.y);
			}
			if ((int) position.x>=1280-64 ){
				v.set(-v.x,v.y);
			}
			if ((int)position.x<=64){
				v.set(-v.x,v.y);
			}
			if ((int)position.x - 64 < 0)position.x = 64;
			if ((int)position.x + 64 > 1280)position.x = 1280-64;
			if ((int)position.y - 64 < 0)position.y = 64;
			if ((int)position.y + 64 > 720)position.y = 720-64;
//			if ((int)position.y<=128 && (int)position.x <=(int) obj2.position.x+64 &&(int) position.x >=(int)obj2.position.x -64){
//				v.set(v.x +obj2.v.x ,-v.y + 500);
//			}

			if ((int)position.y<=128 && ((int)position.x >=(int)obj2.position.x-64 && (int) position.x <=(int)obj2.position.x +64)){
				v.set(v.x +obj2.v.x ,-v.y + 1000);
			}
			if ((int)position.y<=128-6 && ((int)position.x + 32 >=(int)obj2.position.x-64 && (int) position.x-32 <=(int)obj2.position.x +64)){
				v.set(v.x +obj2.v.x ,-v.y + 1000);
			}
			if ((int)position.y<=128-18 && ((int)position.x + 44 >=(int)obj2.position.x-64 && (int) position.x-44 <=(int)obj2.position.x +64)){
				v.set(v.x +obj2.v.x ,-v.y + 1000);
			}
			if ((int)position.y<=128-30 && ((int)position.x + 54 >=(int)obj2.position.x-64 && (int) position.x-54 <=(int)obj2.position.x +64)){
				v.set(v.x +obj2.v.x ,-v.y + 1000);
			}
			if ((int)position.y<=128-64 && ((int)position.x + 64 >=(int)obj2.position.x-64 && (int) position.x-64 <=(int)obj2.position.x +64)){
				v.set(v.x +obj2.v.x ,-v.y + 1000);
			}
			position.mulAdd(v,dt);


//
//			angle += dt;
//			if (Gdx.input.isKeyPressed(Input.Keys.D)){
//				x += vx *dt;
//			}
//			if (Gdx.input.isKeyPressed(Input.Keys.A)){
//				x -= vx *dt;
//			}
//			if (Gdx.input.isKeyPressed(Input.Keys.W)){
//				y += vy *dt;
//			}
//			if (Gdx.input.isKeyPressed(Input.Keys.S)){
//				y -= vy *dt;
//			}
		}
	}
	private class Obj2 {
	private Texture texture;
	private Vector2 position;
	private Vector2 v;
	private float angle;
	private float scale;

		public Obj2(Texture texture,float x, float y) {
			this.texture = texture;
			this.position = new Vector2(x,y);
			this.angle = 0;
			this.scale = 1.0f;
		}
		public void render(SpriteBatch batch){
			batch.draw(texture, position.x - 64, position.y - 64,64,32,128,64,1,1,angle,0,0,128,64,false,false);

		}

		public void update(float dt){
			Vector2 m = new Vector2(Gdx.input.getX(),position.y);
			v = m.cpy().sub(position).nor().scl(1000.0f);

			if ((int)m.cpy().sub(position).len()>10)
			position.mulAdd(v,dt);


		}

		public Vector2 getPosition() {
			return position;
		}
	}
	SpriteBatch batch;
	Obj obj;
	Obj2 obj2;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		obj2 = new Obj2(new Texture("block.png"),1280/2,64);

		obj = new Obj(new Texture("ball.png"),200,360, obj2);

	}
	float angle;
	float x;
	@Override
	public void render () {
		float dt = Gdx.graphics.getDeltaTime();
		update(dt);
		Gdx.gl.glClearColor(0.2f, 0.64f, 0.53f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		obj.render(batch);
		obj2.render(batch);
		batch.end();
	}
	public void update(float dt){
		obj.update(dt);
		obj2.update(dt);
	}
	@Override
	public void dispose () {
		batch.dispose();

	}
}
