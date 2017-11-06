package Engine.modules.box2d;

import Engine.modules.box2d.B2DBits;
import java.awt.Dimension;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;

import Engine.utils.Copier;
import Engine.modules.ashley.listeners.CollisionListener;

public class FixtureSettings extends Copier{
	private static final long serialVersionUID = 1L;

	public enum Shapes{
		Circle, Box, Custom
	}
	
	public Dimension dimension;
	public List<Vector2> customShapeData; // needs to be set to custom shape
	public Shapes shapeType;
	
	public short categoryBits;
	public short maskBits;
	
	public Vector2 offset;
	public String userData;
	public boolean isSensor;
	
	// this allows collision listener to not by copied
	public transient CollisionListener collisionListener;
	
	public FixtureSettings() {
		dimension = new Dimension(0, 0);
		customShapeData = new ArrayList<Vector2>();
		shapeType = Shapes.Box;
		
		categoryBits = B2DBits.DEFAULT;
		maskBits = B2DBits.DEFAULT;
		
		offset = new Vector2(0, 0);
		userData = "";
		isSensor = false;
		
		collisionListener = null;
	}
	
	public FixtureSettings copy() {
		return (FixtureSettings) deepCopy(this);
	}
	
}
