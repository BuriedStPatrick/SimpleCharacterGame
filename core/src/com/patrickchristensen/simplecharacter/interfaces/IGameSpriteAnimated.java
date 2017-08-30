package com.patrickchristensen.simplecharacter.interfaces;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public interface IGameSpriteAnimated extends IGameSprite {
    void setAnimation(TextureRegion[] reg, float delay);
    void update(float dt);
}
