package Engine.modules.ashley.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.utils.ImmutableArray;

public abstract class EntitySystemLayout extends EntitySystem{
	
	protected ImmutableArray<Entity> entities;
	
	public abstract void addedToEngine(Engine engine);
	public abstract void update(float delta);
	public abstract void dispose();
	
}
