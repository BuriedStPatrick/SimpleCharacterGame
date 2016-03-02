package com.patrickchristensen.simplecharacter.objects;

public class Player extends Entity{
	
	public enum PlayerState{
		WALKING,
		IDLE,
		JUMPING,
		FALLING
	}
	
	public PlayerState state;
	private boolean facingLeft = false;
	private boolean dirtyState = true;
	private int health;
	private int maxHealth;
	private int ammo;
	private int coins;
	
	public Player(int health, int maxHealth, PlayerState state) {
		this.health = health;
		this.maxHealth = maxHealth;
		this.state = state;
	}
	
	public int getHealth() {
		return health;
	}
	
	public int getMaxHealth() {
		return maxHealth;
	}
	
	public int getCoins() {
		return coins;
	}
	
	public int getAmmo() {
		return ammo;
	}
	
	public PlayerState getState() {
		return state;
	}
	
	public void setState(PlayerState newstate) {
		
		if(newstate.equals(state)){ // the state is either the exact same, or the mirrored state
			if(!dirtyState)			//if it's the exact same state
				return;
		}else
			dirtyState = true;
		state = newstate;
	}
	
	public void collectCoin(){
		coins++;
	}
	
	public boolean isDirtyState(){
		return dirtyState;
	}
	
	public void setDirtyState(boolean dirtyState){
		this.dirtyState = dirtyState;
	}
	
	public void setFacingLeft(boolean facingLeft){
		if(facingLeft != this.facingLeft) dirtyState = true;
		this.facingLeft = facingLeft;
	}
	
	public boolean isFacingLeft(){
		return facingLeft;
	}
}
