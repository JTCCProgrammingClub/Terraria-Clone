package tests.destructable_terrain;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Engine.game.Game;
import Engine.statesystem.GameStateManager;
import Engine.statesystem.BasicGameState;
import Engine.statesystem.States;

public class LoadingState extends BasicGameState {

	private static final float DONE = 1f;
	
	public LoadingState(int stateID) {
		super(stateID);
	}

	@Override
	public void init(GameStateManager gsm) {
            Game.assetManager.loadByShort("badlogic.jpg", "badlogic", Texture.class);
	}

	@Override
	public void render(SpriteBatch batch) {
		
	}

	@Override
	public void update(float delta) {
		System.out.println("Loading...");
		
		if(Game.assetManager.update() == true && Game.assetManager.getProgress() == DONE)
			gsm.enterState(States.TEST);
	}

	@Override
	public void dispose() {
		
	}
	
}
