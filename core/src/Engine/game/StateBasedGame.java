package Engine.game;

import Engine.statesystem.GameStateManager;

public abstract class StateBasedGame extends RunnableGame {
	
	private GameStateManager gsm;

	public abstract void initGameStates();
	public GameStateManager getStateManager(){
            return gsm;
        }
	@Override
	public void create() {
		super.create();		
		gsm = new GameStateManager();		
		initGameStates();
	}
	
	@Override
	public void render() {
		super.render();
		
		gsm.update();
		gsm.render();
	}
	
	@Override
	public void dispose() {
		super.dispose();
		
		gsm.dispose();
	}
	
}
