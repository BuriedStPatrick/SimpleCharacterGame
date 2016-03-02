package com.patrickchristensen.simplecharacter.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.patrickchristensen.simplecharacter.Game;
import com.patrickchristensen.simplecharacter.objects.Player;

public class HUDSprite {
	
	private Player player;
	private TextureRegion[] blocks;
	
	public HUDSprite(Player player){
		this.player = player;
	}
	
	public void render(SpriteBatch sb){
		sb.begin();
		BitmapFont font = new BitmapFont();
		font.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		font.draw(sb, player.getCoins() + "", 25, 160);
		font.draw(sb, player.getState() + "", 25, 200);
		if(player.isFacingLeft())
			font.draw(sb, "Facing left", 25, 240);
		else
			font.draw(sb, "Facing right", 25, 240);
		sb.end();
	}
}
