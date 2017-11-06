package Engine.modules.ashley.entities;

import com.badlogic.ashley.core.Entity;

import Engine.game.Game;
import Engine.modules.ashley.AshleyEngine;

public abstract class PoolableEntity {
	
	protected AshleyEngine ashley;
	protected Entity entity;
	
	public PoolableEntity() {
		this.ashley = Game.ashley.engine;
		entity = ashley.createEntity();
	}
	
	public abstract void initComponents();
	
	public void initAndAdd() {
		initComponents();
		ashley.addEntity(entity);
	}
	
	public Entity getEntity() {
		return entity;
	}
	
}
