package Engine.modules.ashley.components;

import java.awt.Dimension;

import com.badlogic.gdx.graphics.Texture;

public class SpriteComponent implements PooledComponent{
	
	public Texture texture;
	public Dimension resizeDimension;
	
	@Override
	public void reset() {
		texture = null;
		resizeDimension = null;
	}
	
}
