package Engine.modules.ashley;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;

import Engine.modules.ashley.components.BodyComponent;
import Engine.modules.ashley.components.FixtureManagerComponent;
import Engine.modules.ashley.components.PositionComponent;
import Engine.game.Game;
import Engine.modules.ashley.listeners.BodyComponentListener;
import Engine.modules.ashley.listeners.FixtureManagerComponentListener;
import Engine.modules.ashley.systems.DynamicUpdaterSystem;
import Engine.modules.ashley.systems.EntitySystemLayout;
import Engine.modules.ashley.systems.FixtureSpriteRenderSystem;
import Engine.modules.ashley.systems.SimpleSpriteRenderSystem;

/**
 * AshleyEngine
 * Called it Ashley Engine to help avoid confusion
 * @author Tyrant's Fire
 *
 */
public class AshleyEngine extends PooledEngine{
	
	private static AshleyEngine instance = null;
	
	private List<EntitySystemLayout> systems;
	
	// engine limits
	private static final int ENTITY_POOL_INITIAL_SIZE = 100;
	private static final int ENTITY_POOL_MAX_SIZE = 500;
	private static final int COMPONENT_POOL_INITIAL_SIZE = 50;
	private static final int COMPONENT_POOL_MAX_SIZE = 500;
	
	public AshleyEngine() {
		super(ENTITY_POOL_INITIAL_SIZE, ENTITY_POOL_MAX_SIZE, COMPONENT_POOL_INITIAL_SIZE, COMPONENT_POOL_MAX_SIZE);
		
		systems = new ArrayList<EntitySystemLayout>();
	}
	
	public static synchronized AshleyEngine getInstance() {
		if(instance == null)
			instance = new AshleyEngine();
		
		return instance;
	}
	
	public void initDefaultListeners() {
		addEntityListener(Family.all(BodyComponent.class, PositionComponent.class).get(), new BodyComponentListener(Game.b2d.world));
		addEntityListener(Family.all(BodyComponent.class, FixtureManagerComponent.class, PositionComponent.class).get(), new FixtureManagerComponentListener());
	}
	
	public void initDefaultSystems() {
		// init systems - the order in which the systems are added to Ashley actually matter
		SimpleSpriteRenderSystem simpleSpriteRenderSys = new SimpleSpriteRenderSystem(Game.batch);
		// this should always be added before FixtureSpriteRenderSystem.  This updates the position it uses to render moving box2d bodies.
		DynamicUpdaterSystem dynamicUpdaterSys = new DynamicUpdaterSystem();
		FixtureSpriteRenderSystem fixtureSpriteRenderSys = new FixtureSpriteRenderSystem(Game.batch);
		
		// add systems to Ashley
		addSystem(simpleSpriteRenderSys);
		addSystem(dynamicUpdaterSys);
		addSystem(fixtureSpriteRenderSys);
		addSystem(Game.b2d.world.getContactListenerSystem());
		
		// add to systems list for easy clean up
		systems.add(simpleSpriteRenderSys);
		systems.add(dynamicUpdaterSys);
		systems.add(fixtureSpriteRenderSys);
		systems.add(Game.b2d.world.getContactListenerSystem());
	}
	
	public EntitySystemLayout[] getDefaultLoadedSystems() {
		return systems.toArray(new EntitySystemLayout[systems.size()]);
	}
	
	public void dispose() { 
		for(EntitySystemLayout system : systems)
			system.dispose();
		
		// remove all unused components and entities from their pools
		clearPools();
	}
	
}
