package com.patrickchristensen.simplecharacter.states;

import static com.patrickchristensen.simplecharacter.handlers.B2DVars.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.patrickchristensen.simplecharacter.Game;
import com.patrickchristensen.simplecharacter.cameras.BoundedCamera;
import com.patrickchristensen.simplecharacter.entities.HUDSprite;
import com.patrickchristensen.simplecharacter.handlers.B2DVars;
import com.patrickchristensen.simplecharacter.handlers.GameStateManager;
import com.patrickchristensen.simplecharacter.handlers.MyContactListener;
import com.patrickchristensen.simplecharacter.handlers.MyInput;
import com.patrickchristensen.simplecharacter.objects.Coin;
import com.patrickchristensen.simplecharacter.objects.Player;
import com.patrickchristensen.simplecharacter.objects.Player.PlayerState;
import com.patrickchristensen.simplecharacter.sprites.CoinSprite;
import com.patrickchristensen.simplecharacter.sprites.PlayerSprite;

public class Play extends GameState{
	
	// 1 = normal speed
	public static final float PHYSICS_SPEED = 1f;
	
	
	private float tileSize;
	private boolean DEBUG = false;
	
	private World world;
	private Box2DDebugRenderer b2dr;
	private BoundedCamera b2dCam;
	private MyContactListener cl;
	private TiledMap tileMap;
	private int tileMapWidth, tileMapHeight;
	private OrthogonalTiledMapRenderer tmr;
	
	private PlayerSprite playerSprite;
	private Player player;
	private Array<CoinSprite> coinSprites;
	private HUDSprite hud;
	
	public Play(GameStateManager gsm) {
		super(gsm);
		
		// set up box2d stuff
		world = new World(new Vector2(0, -9.81f * PHYSICS_SPEED), true);
		cl = new MyContactListener();
		world.setContactListener(cl);
		b2dr = new Box2DDebugRenderer();
		
		// create player
		createPlayer();
		
		// create tiles
		createTiles();
		cam.setBounds(0, tileMapWidth * tileSize, 0, tileMapHeight * tileSize);
		
		// create coins
		createCoins();
		
		// set up box2d cam
		b2dCam = new BoundedCamera();
		b2dCam.setToOrtho(false, Game.V_WIDTH / PPM, Game.V_HEIGHT / PPM);
		b2dCam.setBounds(0, (tileMapWidth * tileSize) / PPM, 0, (tileMapHeight * tileSize) / PPM);
		
		// set up hud
		hud = new HUDSprite(player);
	}
	
	@Override
	public void update(float dt) {
		handleInput();
		world.step(Game.STEP, 6, 1);
		
		// remove coins
		Array<Body> bodies = cl.getBodiesToRemove();
		for(Body b : bodies){
			coinSprites.removeValue((CoinSprite) b.getUserData(), true);
			world.destroyBody(b);
			player.collectCoin();
		}
		bodies.clear();
		
		playerSprite.update(dt);
		
		for(CoinSprite c : coinSprites){
			c.update(dt);
		}
		
	}

	@Override
	public void render() {
		// clear screen
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//set blue background for testing 9fe3ff
		Gdx.gl.glClearColor(0f, 156f, 255f, 255f);
		
		// set camera to follow player
		cam.setPosition(playerSprite.getPosition().x * PPM + Game.V_WIDTH / 4,
				Game.V_HEIGHT / Game.SCALE);
		hudCam.position.set(cam.position.x, cam.position.y, cam.position.z);
		cam.update();
		
		// draw tile map
		tmr.setView(cam);
		tmr.render();
		
		// draw player
		sb.setProjectionMatrix(cam.combined);
		playerSprite.render(sb);
		
		// draw coins
		for(CoinSprite c : coinSprites){
			c.render(sb);
		}
		
		// draw hud
		sb.setProjectionMatrix(hudCam.combined);
		hud.render(sb);
		
		// draw box2D world
		if(DEBUG){
			b2dCam.setPosition(playerSprite.getPosition().x + Game.V_WIDTH / 4 / PPM, Game.V_HEIGHT / Game.SCALE / PPM);
			b2dCam.update();
			b2dr.render(world, b2dCam.combined);
		}
	}
	
	@Override
	public void handleInput() {
		float velX = playerSprite.getBody().getLinearVelocity().x;
		float velY = playerSprite.getBody().getLinearVelocity().y;
		
		// JUMP
		if(MyInput.isPressed(MyInput.JUMP)){
			
			if(cl.isPlayerGrounded()){
				player.setState(PlayerState.JUMPING);
				playerSprite.getBody().applyForceToCenter(0, 200, true);
			}
		}
		// LEFT
		else if(MyInput.isDown(MyInput.LEFT)){
			player.setFacingLeft(true);
			if(cl.isPlayerGrounded()){
				playerSprite.getBody().applyForceToCenter(-10, 0, true);
				player.setState(PlayerState.WALKING);
			}else{
				playerSprite.getBody().applyForceToCenter(-5, 0, true);
				if(velY > 0)
					player.setState(PlayerState.JUMPING);
				else
					player.setState(PlayerState.FALLING);
			}
		}
		
		// RIGHT
		else if(MyInput.isDown(MyInput.RIGHT)){
			player.setFacingLeft(false);
			if(cl.isPlayerGrounded()){
				playerSprite.getBody().applyForceToCenter(10, 0, true);
				player.setState(PlayerState.WALKING);
			}else{
				playerSprite.getBody().applyForceToCenter(5, 0, true);
				if(velY > 0)
					player.setState(PlayerState.JUMPING);
				else
					player.setState(PlayerState.FALLING);
			}
		}
		
		
		else{
			if(velY > 0)
				player.setState(PlayerState.JUMPING);
			else if(velY < 0)
				player.setState(PlayerState.FALLING);
			else if(cl.isPlayerGrounded())
				player.setState(PlayerState.IDLE);
		}
		
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
	private void createPlayer(){
		
		BodyDef bdef = new BodyDef();
		bdef.position.set(160 / PPM, 100 / PPM);
		bdef.type = BodyType.DynamicBody;
		Body body = world.createBody(bdef);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(6 / PPM, 6 / PPM);
		
		FixtureDef fdef = new FixtureDef();
		fdef.shape = shape;
		fdef.filter.categoryBits = B2DVars.BIT_PLAYER;
		fdef.filter.maskBits = B2DVars.BIT_STONE | B2DVars.BIT_METAL | B2DVars.BIT_SAND | B2DVars.BIT_COIN;
		
		body.createFixture(fdef).setUserData("player");
		shape.dispose();
		
		//create foot sensor
		shape = new PolygonShape();
		shape.setAsBox(5 / PPM, Game.SCALE / PPM, new Vector2(0, -5 / PPM), 0);
		fdef.shape = shape;
		fdef.filter.categoryBits = B2DVars.BIT_PLAYER;
		fdef.filter.maskBits = B2DVars.BIT_STONE | B2DVars.BIT_METAL | B2DVars.BIT_SAND;
		fdef.isSensor = true;
		body.createFixture(fdef).setUserData("foot");
		
		// create player with 100 health, 100 max health
		player = new Player(100, 100, PlayerState.FALLING);
		playerSprite = new PlayerSprite(body, player);
		body.setUserData("player");
		shape.dispose();
	}
	
	private void createTiles(){
		// load tile map
		tileMap = new TmxMapLoader().load("maps/level01.tmx");
		tmr = new OrthogonalTiledMapRenderer(tileMap);
		tileSize = tileMap.getProperties().get("tilewidth", Integer.class);
		tileMapWidth = (int)tileMap.getProperties().get("width", Integer.class);
		tileMapHeight = (int)tileMap.getProperties().get("height", Integer.class);
		
		TiledMapTileLayer layer;
		MapLayer collisions;
		
//		layer = (TiledMapTileLayer) tileMap.getLayers().get("stones");
		collisions = (MapLayer) tileMap.getLayers().get("collisions");
		createLayer(collisions, B2DVars.BIT_STONE);
		
		
	}
	
	private void createLayer(MapLayer layer, short bits){
		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		
		for(MapObject mo : layer.getObjects()){
			float x = 0, y = 0;
			Rectangle r = null;
			//Rectangle collisions
			if(mo instanceof RectangleMapObject){
				r = ((RectangleMapObject) mo).getRectangle();
				x = r.x / PPM;
				y = r.y / PPM;
			}
			bdef.position.set(
				((r.x+r.width/2)/PPM),
				((r.y+r.height/2)/PPM)
			);
			PolygonShape ps = new PolygonShape();
			ps.setAsBox((r.width/2) / PPM, (r.height/2) / PPM);
			
			fdef.friction = 0.5f;
			fdef.shape = ps;
			fdef.filter.categoryBits = bits;
			fdef.filter.maskBits = B2DVars.BIT_PLAYER;
			fdef.isSensor = false;
			world.createBody(bdef).createFixture(fdef);
			ps.dispose();
		}
	}
	
	private void createCoins(){
		
		coinSprites = new Array<CoinSprite>();
		
		MapLayer layer = tileMap.getLayers().get("coins");
		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		
		for(MapObject mo : layer.getObjects()){
			
			float x = 0f, y = 0f;
			bdef.type = BodyType.StaticBody;
			
			// map object is an ellipse
			if(mo instanceof EllipseMapObject){
				Ellipse e = ((EllipseMapObject) mo).getEllipse();
				x = e.x / PPM;
				y = e.y / PPM;
			}
			
			// map object is a rectangle
			if(mo instanceof RectangleMapObject){
				Rectangle r = ((RectangleMapObject) mo).getRectangle();
				x = r.x / PPM;
				y = r.y / PPM;
			}
			
			// ... other subtypes can then be added as needed
			
			bdef.position.set(x, y);
			
			CircleShape cshape = new CircleShape();
			cshape.setRadius(3 / PPM);
			
			fdef.shape = cshape;
			fdef.isSensor = true;
			fdef.filter.categoryBits = B2DVars.BIT_COIN;
			fdef.filter.maskBits = B2DVars.BIT_PLAYER;
			
			Body body = world.createBody(bdef);
			body.createFixture(fdef).setUserData("coin");
			Coin coin = new Coin();
			CoinSprite coinSprite = new CoinSprite(body, coin);
			coinSprites.add(coinSprite);
			
			body.setUserData(coinSprite);
			cshape.dispose();	
		}	
	}
}
