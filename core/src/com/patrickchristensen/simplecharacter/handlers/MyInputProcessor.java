package com.patrickchristensen.simplecharacter.handlers;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input.Keys;

public class MyInputProcessor extends InputAdapter{

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		
		return super.touchDragged(screenX, screenY, pointer);
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		
		return super.touchDown(screenX, screenY, pointer, button);
	}
	
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		
		return super.touchUp(screenX, screenY, pointer, button);
	}
	
	@Override
	public boolean keyDown(int keycode) {
		switch(keycode){
		case Keys.SPACE:
			MyInput.setKey(MyInput.JUMP, true);
			break;
		case Keys.A:
			MyInput.setKey(MyInput.LEFT, true);
			break;
		case Keys.D:
			MyInput.setKey(MyInput.RIGHT, true);
			break;
		case Keys.S:
			MyInput.setKey(MyInput.DOWN, true);
			break;
		}
		return true;
	}
	
	@Override
	public boolean keyUp(int keycode) {
		switch(keycode){
		case Keys.SPACE:
			MyInput.setKey(MyInput.JUMP, false);
			break;
		case Keys.A:
			MyInput.setKey(MyInput.LEFT, false);
			break;
		case Keys.D:
			MyInput.setKey(MyInput.RIGHT, false);
			break;
		case Keys.S:
			MyInput.setKey(MyInput.DOWN, false);
			break;
		}
		return true;
	}
}
