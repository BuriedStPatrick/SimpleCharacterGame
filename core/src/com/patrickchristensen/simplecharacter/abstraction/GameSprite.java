package com.patrickchristensen.simplecharacter.abstraction;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.patrickchristensen.simplecharacter.interfaces.IGameSprite;

public abstract class GameSprite implements IGameSprite{

    Body body;
    float width, height;
    protected GameObject object;

    public GameSprite(Body body, GameObject object) {
        this.body = body;
        this.object = object;
    }

    public float getWidth(){
        return this.width;
    }

    public float getHeight(){
        return this.height;
    }

    public Vector2 getPosition(){ return body.getPosition(); }

}
