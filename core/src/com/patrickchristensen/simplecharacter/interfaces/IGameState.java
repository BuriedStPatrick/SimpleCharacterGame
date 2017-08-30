package com.patrickchristensen.simplecharacter.interfaces;

public interface IGameState {
    void handleInput();
    void update(float dt);
    void render();
    void dispose();
}
