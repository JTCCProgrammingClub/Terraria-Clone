package Engine.modules.ashley.components;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class BodyComponent implements PooledComponent{
	
	public Body body;
	public String userData;
	public BodyType type;
	
	@Override
	public void reset() {
		body = null;
		userData = "";
		type = BodyType.StaticBody;
	}
	
}
