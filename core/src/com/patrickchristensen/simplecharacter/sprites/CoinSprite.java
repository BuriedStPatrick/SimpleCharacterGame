package com.patrickchristensen.simplecharacter.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.patrickchristensen.simplecharacter.SimpleCharacterGame;
import com.patrickchristensen.simplecharacter.abstraction.GameSpriteAnimated;
import com.patrickchristensen.simplecharacter.gameobjects.Coin;

public class CoinSprite extends GameSpriteAnimated {

    public CoinSprite(Body body, Coin coin) {
        super(body, coin);

        Texture tex = SimpleCharacterGame.res.getTexture("coin");
        TextureRegion[] sprites = TextureRegion.split(tex, 6, 6)[0];

        setAnimation(sprites, 1 / 24f);
    }
}
