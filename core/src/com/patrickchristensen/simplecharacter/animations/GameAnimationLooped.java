package com.patrickchristensen.simplecharacter.animations;

import com.patrickchristensen.simplecharacter.abstraction.GameAnimation;

public class GameAnimationLooped extends GameAnimation {

    @Override
    public void step(){
        time -= delay;
        currentFrame++;
        if(currentFrame == frames.length){
            currentFrame = 0;
        }
    }
}
