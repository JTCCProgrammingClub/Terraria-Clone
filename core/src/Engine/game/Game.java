package Engine.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Engine.management.AdvanceAssetManager;
import Engine.management.AshleyWrapper;
import Engine.management.B2DWrapper;
import Engine.management.GameSettings;

/**
 * This helps organize essential aspects of the mini-lib/min-framework
 * @author Tyrant's Fire
 *
 */
public abstract class Game extends ApplicationAdapter{
	
	// window properties
	public static GameSettings settings;
		
	public static SpriteBatch batch;
	public static OrthographicCamera mainCamera;        
        public static AdvanceAssetManager assetManager = AdvanceAssetManager.getInstance();
        
        //wrappers for your modules
        public static B2DWrapper b2d;
	public static AshleyWrapper ashley;
	
	protected Game() {
		settings = new GameSettings();
		settings();
	}
	
	public abstract void settings();
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		
		mainCamera = new OrthographicCamera();
		mainCamera.setToOrtho(false, settings.width, settings.height);
		mainCamera.update();
	}
	
	public SpriteBatch getSpriteBatch() {
		return batch;
	}
	
	@Override
	public void dispose() {
		batch.dispose();
                
		b2d.world.dispose();
		ashley.engine.dispose();
                
		assetManager.dispose();
	}
}
