package com.patrickchristensen.simplecharacter.states;


import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.patrickchristensen.simplecharacter.Game;
import com.patrickchristensen.simplecharacter.cameras.BoundedCamera;
import com.patrickchristensen.simplecharacter.handlers.GameStateManager;

public abstract class GameState {

	protected GameStateManager gsm;
	protected Game game;
	
	protected SpriteBatch sb;
	protected BoundedCamera cam;
	protected OrthographicCamera hudCam;
	
	public GameState(GameStateManager gsm) {
		this.gsm = gsm;
		game = gsm.game();
		sb = game.getSpriteBatch();
		cam = game.getCamera();
		hudCam = game.getHudCamera();
	}
	
	public abstract void handleInput();
	public abstract void update(float dt);
	public abstract void render();
	public abstract void dispose();
}
