package com.patrickchristensen.simplecharacter.sprites;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.patrickchristensen.simplecharacter.abstraction.GameSprite;
import com.patrickchristensen.simplecharacter.gameobjects.Player;

public class HUDSprite extends GameSprite {

    public HUDSprite(Body body, Player player){
        super(body, player);
    }

    public void render(SpriteBatch spriteBatch){
        spriteBatch.begin();
        Player player = (Player) object;

        BitmapFont font = new BitmapFont();
        font.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        font.draw(spriteBatch, player.getCoins() + "", 25, 160);
        font.draw(spriteBatch, player.getState() + "", 25, 200);
        if(player.isFacingLeft()) {
            font.draw(spriteBatch, "Facing left", 25, 240);
        } else {
            font.draw(spriteBatch, "Facing right", 25, 240);
        }
        spriteBatch.end();
    }
}
