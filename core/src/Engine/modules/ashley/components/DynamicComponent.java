package Engine.modules.ashley.components;

/**
 * Entities that have the component will have there position updated each frame.
 * This should be attached to entities that contain a body component that will move either,
 * naturally (gravity) or manually (by code). This way the position is updated and sprites will,
 * "stick" to the box2d fixture(s).
 */
public class DynamicComponent implements PooledComponent{
	
	@Override
	public void reset() {
		
	}
	
}
