package com.patrickchristensen.simplecharacter.handlers;

import java.util.Stack;


import com.patrickchristensen.simplecharacter.Game;
import com.patrickchristensen.simplecharacter.states.GameState;
import com.patrickchristensen.simplecharacter.states.Play;

public class GameStateManager {

	private Game game;
	private Stack<GameState> gameStates;
	
	public static final int PLAY = 0;
	public static final int PAUSE = 1;
	
	public GameStateManager(Game game) {
		this.game = game;
		gameStates = new Stack<GameState>();
		pushState(PLAY);
	}
	
	public Game game(){
		return game;
	}
	
	public void update(float dt){
		gameStates.peek().update(dt);
	}
	
	public void render(){
		gameStates.peek().render();
	}
	
	public void setState(int state){
		popState();
		pushState(state);
	}
	
	public void pushState(int state){
		gameStates.push(getState(state));
	}
	
	public void popState(){
		GameState gs = gameStates.pop();
		gs.dispose();
	}
	
	private GameState getState(int state){
		switch(state){
		case PLAY:
			return new Play(this);
		default:
			return null;
		}
	}
	
}
