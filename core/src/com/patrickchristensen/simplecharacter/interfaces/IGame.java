package com.patrickchristensen.simplecharacter.interfaces;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface IGame extends ApplicationListener {
    SpriteBatch getSpriteBatch();
    Camera getGameCamera();
    Camera getHudCamera();
}
