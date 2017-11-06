package Engine.game;

import com.badlogic.gdx.Gdx;

public abstract class RunnableGame extends Game {
	
	private float elapsedTime;
	
	public RunnableGame() {
		super();
		
		elapsedTime = 0;
	}
	
	@Override
	public void render() {
		elapsedTime += Gdx.graphics.getDeltaTime();
	}
	
	public float getElapsedTime() {
		return elapsedTime;
	}
	
}
