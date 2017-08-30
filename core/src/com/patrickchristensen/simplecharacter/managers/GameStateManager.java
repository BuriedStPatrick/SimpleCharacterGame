package com.patrickchristensen.simplecharacter.managers;

import java.util.Stack;


import com.patrickchristensen.simplecharacter.interfaces.IGameStateManager;
import com.patrickchristensen.simplecharacter.SimpleCharacterGame;
import com.patrickchristensen.simplecharacter.interfaces.IGame;
import com.patrickchristensen.simplecharacter.abstraction.GameState;
import com.patrickchristensen.simplecharacter.gamestates.Play;

public class GameStateManager implements IGameStateManager{

    private SimpleCharacterGame game;
    private Stack<GameState> gameStates;

    public static final int PLAY = 0;
    public static final int PAUSE = 1;

    public GameStateManager(SimpleCharacterGame game) {
        this.game = game;
        gameStates = new Stack<GameState>();
        pushState(PLAY);
    }

    public void update(float dt){
        gameStates.peek().update(dt);
    }

    public void render(){
        gameStates.peek().render();
    }

    @Override
    public IGame getGame() {
        return game;
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
