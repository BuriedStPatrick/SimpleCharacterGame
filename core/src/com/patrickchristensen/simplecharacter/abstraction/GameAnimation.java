package com.patrickchristensen.simplecharacter.abstraction;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.patrickchristensen.simplecharacter.interfaces.IGameAnimation;

public abstract class GameAnimation implements IGameAnimation {

    protected TextureRegion[] frames;
    protected float time;
    protected float delay;
    protected int currentFrame;

    public GameAnimation() {}

    public GameAnimation(TextureRegion[] frames){
        this(frames, 1 / 12f);
    }

    public GameAnimation(TextureRegion[] frames, float delay){
        setFrames(frames, delay);
    }

    public void setFrames(TextureRegion[] frames, float delay){
        this.frames = frames;
        this.delay = delay;
        time = 0;
        currentFrame = 0;
    }

    public void update(float dt){
        if(delay <= 0) return;
        time += dt;
        while(time >= delay){
            step();
        }
    }

    public abstract void step();

    public TextureRegion getFrame(){return frames[currentFrame];}
}
