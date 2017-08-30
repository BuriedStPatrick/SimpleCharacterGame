package com.patrickchristensen.simplecharacter.interfaces;

import com.badlogic.gdx.graphics.Texture;

public interface IGameTextureManager {
    void loadTexture(String path, String key);
    Texture getTexture(String key);
    void disposeTexture(String key);
    void disposeAllTextures();
}
