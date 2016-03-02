package com.patrickchristensen.simplecharacter;


import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.patrickchristensen.simplecharacter.cameras.BoundedCamera;
import com.patrickchristensen.simplecharacter.handlers.Content;
import com.patrickchristensen.simplecharacter.handlers.GameStateManager;
import com.patrickchristensen.simplecharacter.handlers.MyInput;
import com.patrickchristensen.simplecharacter.handlers.MyInputProcessor;

public class Game implements ApplicationListener {
	
	public static final String TITLE = "Simple Character";
	public static final int V_WIDTH = 640;
	public static final int V_HEIGHT = 360;
	public static final int SCALE = 2;
	public static final float STEP = 1 / 60f;
	public float accum;
	
	private SpriteBatch sb;
	private BoundedCamera cam;
	private OrthographicCamera hudCam;
	private GameStateManager gsm;
	
	public static Content res;
	
	@Override
	public void create() {
//		Texture.setEnforcePotImages(false);
		Gdx.input.setInputProcessor(new MyInputProcessor());
		
		res = new Content();
		
		res.loadTexture("sprites/anim-walk.png", "player-walk");
		res.loadTexture("sprites/anim-idle.png", "player-idle");
		res.loadTexture("sprites/anim-fall.png", "player-fall");
		res.loadTexture("sprites/anim-jump.png", "player-jump");
        res.loadTexture("sprites/anim-coin.png", "coin");
		
		cam = new BoundedCamera();
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
		res.removeAll();
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
	
	public BoundedCamera getCamera(){
		return cam;
	}
	
	public OrthographicCamera getHudCamera(){
		return cam;
	}
}
