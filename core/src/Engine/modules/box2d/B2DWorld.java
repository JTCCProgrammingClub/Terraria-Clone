package Engine.modules.box2d;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import Engine.game.Game;
import Engine.modules.ashley.systems.B2DWorldContactListenerSystem;

import com.badlogic.gdx.physics.box2d.Body;

public class B2DWorld {
	
	private static B2DWorld instance = null;

	// b2d world
	private World world;
	private B2DWorldContactListenerSystem contactListenerSystem;
	
	// world stats
	public static final float VERTICAL_GRAVITY = -9.8f, HORIZONTAL_GRAVITY = 0; // gravity forces of world
	public static final int VELOCITY_INTERATIONS = 6, POSITION_ITERATIONS = 2; // based on B2D recommendations
	public static final float PPM = 100; // pixels per meter
	
	private Box2DDebugRenderer b2dr;
	private OrthographicCamera b2dCam;
	
	private List<Body> markedBodies; // bodies that are marked to be deleted
	
	protected B2DWorld() {
		world = new World(new Vector2(HORIZONTAL_GRAVITY, VERTICAL_GRAVITY), true);
		
		contactListenerSystem = new B2DWorldContactListenerSystem();
		world.setContactListener(contactListenerSystem);
		
		b2dr = new Box2DDebugRenderer();
		
		b2dCam = new OrthographicCamera();
		b2dCam.setToOrtho(false, Game.settings.width, Game.settings.height);
		b2dCam.update();
		
		markedBodies = new ArrayList<Body>();
	}
	
	public static synchronized B2DWorld getInstance() {
		if(instance == null)
			instance = new B2DWorld();
		
		return instance;
	}
	
	public static float convertToB2D(float value) {
		return value / PPM;
	}
	
	public static float convertToPixels(float value) {
		return value * PPM;
	}
	
	public void markBodyForDelete(Body body) {
		markedBodies.add(body);
	}
	
	public World getB2DWorld() {
		return world;
	}
	
	public B2DWorldContactListenerSystem getContactListenerSystem() {
		return contactListenerSystem;
	}
	
	public OrthographicCamera getB2DCam() {
		return b2dCam;
	}
	
	public void render() {
		b2dr.render(world, b2dCam.combined);
	}
	
	public void update(float delta) {
		world.step(delta, VELOCITY_INTERATIONS, POSITION_ITERATIONS);
		
		if(!world.isLocked() && markedBodies.size() > 0) {
			for(Body body : markedBodies)
				world.destroyBody(body);
			
			markedBodies.clear();
		}
	}
	
	public void dispose() {
		world.dispose();
	}
	
}



