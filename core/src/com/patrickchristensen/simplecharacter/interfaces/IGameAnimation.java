package com.patrickchristensen.simplecharacter.interfaces;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public interface IGameAnimation {
    void setFrames(TextureRegion[] frames, float delay);
    void update(float dt);
    void step();
    TextureRegion getFrame();

}
