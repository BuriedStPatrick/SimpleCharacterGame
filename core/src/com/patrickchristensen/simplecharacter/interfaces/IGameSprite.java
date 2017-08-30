package com.patrickchristensen.simplecharacter.interfaces;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public interface IGameSprite {
    void render(SpriteBatch sb);
    Vector2 getPosition();
    float getWidth();
    float getHeight();
}
