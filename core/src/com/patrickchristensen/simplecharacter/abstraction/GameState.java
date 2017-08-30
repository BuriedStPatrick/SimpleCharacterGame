package com.patrickchristensen.simplecharacter.abstraction;


import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.patrickchristensen.simplecharacter.gamecameras.GameCameraBounded;
import com.patrickchristensen.simplecharacter.interfaces.IGame;
import com.patrickchristensen.simplecharacter.interfaces.IGameState;
import com.patrickchristensen.simplecharacter.interfaces.IGameStateManager;

public abstract class GameState implements IGameState {

    protected IGameStateManager gsm;
    protected IGame game;

    protected SpriteBatch spriteBatch;
    protected GameCameraBounded gameCamera;
    protected OrthographicCamera hudCam;

    public GameState(IGameStateManager gsm) {
        this.gsm = gsm;
        this.game = gsm.getGame();
        this.spriteBatch = game.getSpriteBatch();
        this.gameCamera = (GameCameraBounded) game.getGameCamera(); //TODO: generalize instead
        this.hudCam = (OrthographicCamera) game.getHudCamera(); //TODO: generalize instead
    }
}
