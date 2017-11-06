package Engine.modules.ashley.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;

import Engine.modules.ashley.components.BodyComponent;
import Engine.modules.ashley.components.DynamicComponent;
import Engine.modules.ashley.components.PositionComponent;
import Engine.modules.box2d.B2DWorld;
import Engine.management.Mappers;

/**
 * 
 * DynamicUpdaterSystem
 * This updates the position component every frame based on the body component's current position.
 * This was created to allow the position to be attained by the position component alone.
 * Instead of accessing the body component and converting the position to pixels.
 * This allows the ability of removing the body component and not worrying about the position of the sprite (if applied with one).
 * @author Tyrant's Fire
 *
 */
public class DynamicUpdaterSystem extends EntitySystemLayout{

	@Override
	public void addedToEngine(Engine engine) {
		entities = engine.getEntitiesFor(Family.all(BodyComponent.class, PositionComponent.class, DynamicComponent.class).get());
	}

	@Override
	public void update(float delta) {
		
		for(Entity entity : entities) {
			BodyComponent bodyCom = Mappers.body.get(entity);
			PositionComponent positionCom = Mappers.position.get(entity);
			
			// update the position based on the body's position
			positionCom.x = bodyCom.body.getPosition().x * B2DWorld.PPM;
			positionCom.y = bodyCom.body.getPosition().y * B2DWorld.PPM;
		}
		
	}

	@Override
	public void dispose() {
		
	}
	
}
