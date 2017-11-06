package Engine.modules.ashley.components;

/**
 * Entities that contain this component will have the collision listener from the fixture settings
 * called when collision is made.  This is supposed to help performance.
 *
 */
public class CollisionListenerComponent implements PooledComponent{
	
	@Override
	public void reset() {
		
	}
	
}
