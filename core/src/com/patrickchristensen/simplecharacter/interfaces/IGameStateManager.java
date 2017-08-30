package com.patrickchristensen.simplecharacter.interfaces;

public interface IGameStateManager {
    void pushState(int state);
    void popState();
    void update(float d);
    void render();
    IGame getGame();
}
