package com.patrickchristensen.simplecharacter.abstraction;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.patrickchristensen.simplecharacter.animations.GameAnimationLooped;
import com.patrickchristensen.simplecharacter.utils.Constants;
import com.patrickchristensen.simplecharacter.interfaces.IGameSpriteAnimated;

public abstract class GameSpriteAnimated extends GameSprite implements IGameSpriteAnimated {

    private GameAnimation animation;

    public GameSpriteAnimated(Body body, GameObject object) {
        super(body, object);
        animation = new GameAnimationLooped();
    }

    public void setAnimation(TextureRegion[] reg, float delay){
        animation.setFrames(reg, delay);
        width = reg[0].getRegionWidth();
        height = reg[0].getRegionHeight();
    }

    public void render(SpriteBatch sb){
        sb.begin();
        sb.draw(animation.getFrame(),
                body.getPosition().x * Constants.PPM - width / 2,
                body.getPosition().y * Constants.PPM - height / 2);
        sb.end();
    }

    public void update(float dt){
        animation.update(dt);
    }

    public Body getBody(){ return body; }
    public float getWidth(){ return width; }
    public float getHeight(){ return height; }
    public GameObject getObject(){ return object; }

}
