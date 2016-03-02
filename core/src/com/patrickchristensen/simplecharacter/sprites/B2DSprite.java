package com.patrickchristensen.simplecharacter.sprites;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.patrickchristensen.simplecharacter.handlers.Animation;
import com.patrickchristensen.simplecharacter.handlers.B2DVars;
import com.patrickchristensen.simplecharacter.objects.Entity;

public abstract class B2DSprite {
	
	protected Body body;
	protected Animation animation;
	protected float width, height;
	protected Entity object;
	
	public B2DSprite(Body body, Entity object) {
		this.body = body;
		animation = new Animation();
		this.object = object;
	}
	
	public void setAnimation(TextureRegion[] reg, float delay){
		animation.setFrames(reg, delay);
		width = reg[0].getRegionWidth();
		height = reg[0].getRegionHeight();
	}
	
	public void update(float dt){
		animation.update(dt);
	}
	
	public void render(SpriteBatch sb){
		sb.begin();
		sb.draw(animation.getFrame(),
				body.getPosition().x * B2DVars.PPM - width / 2,
				body.getPosition().y * B2DVars.PPM - height / 2);
		sb.end();
	}
	
	public Body getBody(){ return body; }
	public Vector2 getPosition(){ return body.getPosition(); }
	public float getWidth(){ return width; }
	public float getHeight(){ return height; }
	public Entity getObject(){ return object; }
	
}
