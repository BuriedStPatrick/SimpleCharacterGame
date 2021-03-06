package com.patrickchristensen.simplecharacter.managers;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.patrickchristensen.simplecharacter.interfaces.IGameTextureManager;

public class GameTextureManager implements IGameTextureManager {

    private HashMap<String, Texture> textures;

    public GameTextureManager() {
        textures = new HashMap<String, Texture>();
    }

    public void loadTexture(String path, String key){
        Texture texture = new Texture(Gdx.files.internal(path));
        textures.put(key, texture);
    }

    public Texture getTexture(String key){
        return textures.get(key);
    }

    public void disposeTexture(String key){
        Texture texture = textures.get(key);
        if(texture != null) texture.dispose();
    }

    public void disposeAllTextures(){
        for(Texture tex : textures.values()){
            tex.dispose();
        }
        textures.clear();
    }
}
