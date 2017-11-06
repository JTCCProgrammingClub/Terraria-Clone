package Engine.modules.ashley.listeners;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;

public interface CollisionListener {
	
	public void onContact(Contact contact, Fixture ownsFixture, Fixture otherFixture, Entity entity);
	
}
