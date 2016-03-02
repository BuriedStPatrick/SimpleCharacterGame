package com.patrickchristensen.simplecharacter.handlers;

public class MyInput {

	//keys pressed this loop
	public static boolean[] keys;
	//keys pressed previous loop
	public static boolean[] pkeys;
	
	public static final int NUM_KEYS = 6;
	public static final int JUMP = 0;
	public static final int LEFT = 1;
	public static final int RIGHT = 2;
	public static final int DOWN = 3;
	public static final int FIRE = 4;
	public static final int RELOAD = 5;
	
	static{
		keys = new boolean[NUM_KEYS];
		pkeys = new boolean[NUM_KEYS];
	}
	
	public static void update(){
		for(int i = 0; i < NUM_KEYS; i++){
			pkeys[i] = keys[i];
		}
	}
	
	public static boolean isDown(int i){
		return keys[i];
	}
	
	public static boolean isPressed(int i){
		return keys[i] && !pkeys[i];
	}
	
	public static boolean isReleased(int i){
		return keys[i] && !pkeys[i];
	}
	
	public static void setKey(int i, boolean b){
		keys[i] = b;
	}
}
