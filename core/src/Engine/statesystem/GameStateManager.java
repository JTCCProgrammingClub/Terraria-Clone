package Engine.statesystem;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import Engine.game.Game;
import Engine.statesystem.BasicGameState;

public class GameStateManager{
	
	private Map<Integer, BasicGameState> states;
	private int currentStateID;
	
	public GameStateManager() {
		states = new HashMap<Integer, BasicGameState>();
		currentStateID = 0;
	}
	
	public void addState(BasicGameState state) {
		states.put(state.getID(), state);
	}
	
	public BasicGameState getCurrentState() {
		return states.get(currentStateID);
	}
	
	public void enterState(int stateID) {
		currentStateID = stateID;
		init();
	}
	
	public void init() {
		getCurrentState().init(this);
	}
	
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		getCurrentState().render(Game.batch);
	}
	
	public void update() {
		getCurrentState().update(Gdx.graphics.getDeltaTime());
	}
	
	public void dispose() {
		getCurrentState().dispose();
	}
	
}
