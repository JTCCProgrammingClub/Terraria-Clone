package Engine.modules.ashley.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Engine.modules.ashley.components.PositionComponent;
import Engine.modules.ashley.components.RenderableComponent;
import Engine.modules.ashley.components.SpriteComponent;
import Engine.management.Mappers;

public class SimpleSpriteRenderSystem extends EntitySystemLayout{
	
	private SpriteBatch batch;
	
	public SimpleSpriteRenderSystem(SpriteBatch batch) {
		this.batch = batch;
	}
	
	@Override
	public void addedToEngine(Engine engine) {
		entities = engine.getEntitiesFor(Family.all(SpriteComponent.class, PositionComponent.class, RenderableComponent.class).get());
	}

	@Override
	public void update(float delta) {
		batch.begin();
		
		for(Entity entity : entities) {
			SpriteComponent spriteCom = Mappers.sprite.get(entity);
			PositionComponent positionCom = Mappers.position.get(entity);
			
			if(spriteCom.resizeDimension != null)
				batch.draw(spriteCom.texture, positionCom.x, positionCom.y, spriteCom.resizeDimension.width, spriteCom.resizeDimension.height);
			else
				batch.draw(spriteCom.texture, positionCom.x, positionCom.y);
		}
		
		batch.end();
	}

	@Override
	public void dispose() {
		
	}
	
}
