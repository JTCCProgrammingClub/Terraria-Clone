package Engine.statesystem;

import Engine.game.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public abstract class BasicGameState {
	
	private int id;
        protected GameStateManager gsm; //Why is this necessary?
	public BasicGameState(int stateID) {
		id = stateID;
	}
	
	public void init(GameStateManager gsm){
            this.gsm=gsm;
        }
	public abstract void render(SpriteBatch batch);
	public abstract void update(float delta);
	public abstract void dispose();
	
	public int getID() {
		return id;
	}
	
}
