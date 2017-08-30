package com.patrickchristensen.simplecharacter;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.patrickchristensen.simplecharacter.gamecameras.GameCameraBounded;
import com.patrickchristensen.simplecharacter.managers.GameTextureManager;
import com.patrickchristensen.simplecharacter.managers.GameStateManager;
import com.patrickchristensen.simplecharacter.handlers.MyInput;
import com.patrickchristensen.simplecharacter.handlers.GameInputProcessor;
import com.patrickchristensen.simplecharacter.interfaces.IGame;

public class SimpleCharacterGame implements IGame {

    public static final String TITLE = "Simple Character";
    public static final int V_WIDTH = 1280;
    public static final int V_HEIGHT = 720;
    public static final int SCALE = 2;
    public static final float STEP = 1 / 60f;

    private SpriteBatch sb;
    private GameCameraBounded cam;
    private OrthographicCamera hudCam;
    private GameStateManager gsm;

    public static GameTextureManager res;

    @Override
    public void create() {
        Gdx.input.setInputProcessor(new GameInputProcessor());

        res = new GameTextureManager();

        res.loadTexture("core\\assets\\sprites\\anim-walk.png", "player-walk");
        res.loadTexture("core\\assets\\sprites\\anim-idle.png", "player-idle");
        res.loadTexture("core\\assets\\sprites\\anim-fall.png", "player-fall");
        res.loadTexture("core\\assets\\sprites\\anim-jump.png", "player-jump");
        res.loadTexture("core\\assets\\sprites\\anim-coin.png", "coin");

        cam = new GameCameraBounded();
        cam.setToOrtho(false, V_WIDTH, V_HEIGHT);
        hudCam = new OrthographicCamera();
        hudCam.setToOrtho(false, V_WIDTH, V_HEIGHT);

        sb = new SpriteBatch();
        gsm = new GameStateManager(this);
    }


    @Override
    public void render() {
        Gdx.graphics.setTitle(TITLE + " -- FPS: " + Gdx.graphics.getFramesPerSecond());
        gsm.update(Gdx.graphics.getDeltaTime());
        gsm.render();
        MyInput.update();
    }

    @Override
    public void dispose() {
        res.disposeAllTextures();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    public SpriteBatch getSpriteBatch(){
        return sb;
    }

    @Override
    public Camera getGameCamera() {
        return cam;
    }

    public OrthographicCamera getHudCamera(){
        return cam;
    }
}
