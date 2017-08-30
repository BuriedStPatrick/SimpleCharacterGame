package com.patrickchristensen.simplecharacter.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.patrickchristensen.simplecharacter.SimpleCharacterGame;
import com.patrickchristensen.simplecharacter.abstraction.GameSpriteAnimated;
import com.patrickchristensen.simplecharacter.gameobjects.Player;
import com.patrickchristensen.simplecharacter.gameobjects.Player.PlayerState;

public class PlayerSprite extends GameSpriteAnimated {

    // Animation speeds
    public static final float WALK_SPEED = 1 / 24f;
    public static final float IDLE_SPEED = 1 / 6f;
    public static final float FALL_SPEED = 1 / 12f;
    public static final float JUMP_SPEED = 1 / 12f;

    private Player player;

    //Textures needed for this sprite
    private Texture walktex, idletex, jumptex, falltex;

    private TextureRegion[] leftWalkSprites;
    private TextureRegion[] rightWalkSprites;

    private TextureRegion[] leftIdleSprites;
    private TextureRegion[] rightIdleSprites;

    private TextureRegion[] leftJumpSprites;
    private TextureRegion[] rightJumpSprites;

    private TextureRegion[] leftFallSprites;
    private TextureRegion[] rightFallSprites;


    public PlayerSprite(Body body, Player player){
        super(body, player);
        this.player = (Player) getObject();

        configureFrames();
        updateAnimation();
    }

    private void configureFrames(){
        walktex = SimpleCharacterGame.res.getTexture("player-walk");
        idletex = SimpleCharacterGame.res.getTexture("player-idle");
        jumptex = SimpleCharacterGame.res.getTexture("player-jump");
        falltex = SimpleCharacterGame.res.getTexture("player-fall");

        leftWalkSprites = TextureRegion.split(walktex, 16, 16)[0];
        rightWalkSprites = new TextureRegion[leftWalkSprites.length];
        for(int i = 0; i < leftWalkSprites.length; i++){
            rightWalkSprites[i] = new TextureRegion(leftWalkSprites[i]);
            rightWalkSprites[i].flip(true, false);
        }
        leftIdleSprites = TextureRegion.split(idletex, 16, 16)[0];
        rightIdleSprites = new TextureRegion[leftIdleSprites.length];
        for(int i = 0; i < leftIdleSprites.length; i++){
            rightIdleSprites[i] = new TextureRegion(leftIdleSprites[i]);
            rightIdleSprites[i].flip(true, false);
        }
        leftFallSprites = TextureRegion.split(falltex, 16, 16)[0];
        rightFallSprites = new TextureRegion[leftFallSprites.length];
        for(int i = 0; i < leftFallSprites.length; i++){
            rightFallSprites[i] = new TextureRegion(leftFallSprites[i]);
            rightFallSprites[i].flip(true, false);
        }
        leftJumpSprites = TextureRegion.split(jumptex, 16, 16)[0];
        rightJumpSprites = new TextureRegion[leftJumpSprites.length];
        for(int i = 0; i < leftJumpSprites.length; i++){
            rightJumpSprites[i] = new TextureRegion(leftJumpSprites[i]);
            rightJumpSprites[i].flip(true, false);
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);
    }

    @Override
    public void update(float dt) {
        if(player.isDirtyState()){
            updateAnimation();
        }
        super.update(dt);
    }

    private void updateAnimation(){
        if(player.getState().equals(PlayerState.WALKING) && player.isFacingLeft()) {
            setAnimation(leftWalkSprites, WALK_SPEED);
        } else if(player.getState().equals(PlayerState.WALKING) && !player.isFacingLeft()) {
            setAnimation(rightWalkSprites, WALK_SPEED);
        } else if(player.getState().equals(PlayerState.IDLE) && player.isFacingLeft()) {
            setAnimation(leftIdleSprites, IDLE_SPEED);
        } else if(player.getState().equals(PlayerState.IDLE) && !player.isFacingLeft()) {
            setAnimation(rightIdleSprites, IDLE_SPEED);
        } else if(player.getState().equals(PlayerState.FALLING) && player.isFacingLeft()) {
            setAnimation(leftFallSprites, FALL_SPEED);
        } else if(player.getState().equals(PlayerState.FALLING) && !player.isFacingLeft()) {
            setAnimation(rightFallSprites, FALL_SPEED);
        } else if(player.getState().equals(PlayerState.JUMPING) && player.isFacingLeft()) {
            setAnimation(leftJumpSprites, JUMP_SPEED);
        } else if(player.getState().equals(PlayerState.JUMPING) && !player.isFacingLeft()) {
            setAnimation(rightJumpSprites, JUMP_SPEED);
        }

        // State has been resolved with animation
        player.setDirtyState(false);
    }
}
