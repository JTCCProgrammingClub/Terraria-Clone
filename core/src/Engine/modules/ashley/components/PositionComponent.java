package Engine.modules.ashley.components;

public class PositionComponent implements PooledComponent{
	
	public float x, y;
	
	@Override
	public void reset() {
		x = 0;
		y = 0;
	}
	
}
