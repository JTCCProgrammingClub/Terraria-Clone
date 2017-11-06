package Engine.modules.ashley.listeners;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import Engine.modules.ashley.components.BodyComponent;
import Engine.modules.ashley.components.FixtureManagerComponent;
import Engine.modules.box2d.B2DWorld;
import Engine.modules.box2d.FixtureSettings;
import Engine.management.Mappers;
import Engine.modules.box2d.FixtureSettings.Shapes;

public class FixtureManagerComponentListener implements EntityListener{

	@Override
	public void entityAdded(Entity entity) {
		// Don't need position component, but requires it to make sure that the body
		// was created.  The BodyComponentListener requires position.
		BodyComponent bodyCom = Mappers.body.get(entity);
		FixtureManagerComponent fixtureManagerCom = Mappers.fixtureManager.get(entity);
		
		// make sure body exist before trying to add fixtures
		if(bodyCom.body != null) {
			
			// cycle through fixture settings and create fixtures
			for(FixtureSettings fixtureSetting : fixtureManagerCom.fixtureMap.keySet()) {
				
				// create shape
				PolygonShape shape = new PolygonShape();
				
				if(fixtureSetting.shapeType.equals(Shapes.Box)) {
					// create box
					Vector2 offset = new Vector2((fixtureSetting.offset.x + ((float)fixtureSetting.dimension.width / 2)) / B2DWorld.PPM,
							(fixtureSetting.offset.y + ((float)fixtureSetting.dimension.height / 2)) / B2DWorld.PPM);
					
					shape.setAsBox((float)fixtureSetting.dimension.getWidth() / 2 / B2DWorld.PPM,
							(float)fixtureSetting.dimension.getHeight() / 2 / B2DWorld.PPM,
							offset, 0);
					
				}else if(fixtureSetting.shapeType.equals(Shapes.Circle)) {
					// create circle
					shape.setRadius((float)fixtureSetting.dimension.getHeight() / 2 / B2DWorld.PPM);
				}else {
					// create custom shape
					shape.set(fixtureSetting.customShapeData.toArray(new Vector2[fixtureSetting.customShapeData.size()]));
				}
				
				// create fixture def
				FixtureDef fixtureDef = new FixtureDef();
				
				fixtureDef.shape = shape;
				fixtureDef.filter.categoryBits = fixtureSetting.categoryBits;
				fixtureDef.filter.maskBits = fixtureSetting.maskBits;
				fixtureDef.isSensor = fixtureSetting.isSensor;
				
				// create fixture and set user data
				bodyCom.body.createFixture(fixtureDef).setUserData(fixtureSetting.userData);
				
				// remove shape - don't need it anymore
				shape.dispose();
			}
		}
		
	}

	@Override
	public void entityRemoved(Entity entity) {
		
	}
	
}
