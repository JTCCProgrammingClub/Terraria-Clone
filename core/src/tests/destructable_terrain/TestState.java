package tests.destructable_terrain;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import Engine.game.Game;
import Engine.statesystem.GameStateManager;
import Engine.statesystem.BasicGameState;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector3;
import worldspace.Chunk;
import worldspace.Coords;
import worldspace.ModifierEvaluator;
import worldspace.WorldSpace;

public class TestState extends BasicGameState{
    private FPSLogger fps = new FPSLogger();
    private Chunk[][] chunks;
    private int speed = 3;
    private ModifierEvaluator erase = new ModifierEvaluator();
    private ShapeRenderer shapeRenderer = new ShapeRenderer();
    
    public TestState(int stateID) {
            super(stateID);
    }

    @Override
    public void init(GameStateManager gsm) {
        super.init(gsm);
        
        
        WorldSpace.render.setCam(Game.mainCamera);
        chunks = new Chunk[6][10];
        
        for(int y=0; y < chunks.length; y++){
            for(int x=0; x< chunks[0].length; x++){
                //System.out.println(y+", "+x);
                chunks[y][x] = new Chunk(
                        new Coords(
                                WorldSpace.CHUNK_WIDTH*WorldSpace.GRID_SIZE*x-3072,
                        (WorldSpace.CHUNK_HEIGHT*WorldSpace.GRID_SIZE)-WorldSpace.CHUNK_HEIGHT*WorldSpace.GRID_SIZE*y));
                
                System.out.println(WorldSpace.CHUNK_WIDTH*WorldSpace.GRID_SIZE*x-768);
                chunks[y][x].fill();
            }
        }

        Gdx.input.setInputProcessor(new InputAdapter () {
            @Override
            public boolean touchDragged (int x, int y, int pointer) {
                int mX = Gdx.input.getX();
                int mY = Gdx.input.getY();
                Vector3 worldCoord = Game.mainCamera.unproject(new Vector3(mX, mY, 0));               
                Coords coords = new Coords((int) worldCoord.x, 
                        (int) worldCoord.y);
                
                if(erase.getModifier() == 0){
                    erase.setModifier(new Circle((int) worldCoord.x, 
                           (int) worldCoord.y, 72));
                }

                //erase.setChunk(WorldSpace.chunkAt(coords));
                
                //erase.eval();
                erase.eval(coords );               
                
                return true;
            }
           
        });
    }

    @Override
    public void render(SpriteBatch batch) {
        //fps.log();
        Gdx.gl.glClearColor(135/255f, 206/255f, 235/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Game.mainCamera.update();
        
        WorldSpace.render.render();

    }

    private void handleInput() {
            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                    Game.mainCamera.zoom += 0.02;
                    speed += .99;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
                    Game.mainCamera.zoom -= 0.02;
                    if(speed>3)
                        speed -= .99;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                    Game.mainCamera.translate(-speed, 0, 0);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                    Game.mainCamera.translate(speed, 0, 0);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                    Game.mainCamera.translate(0, -speed, 0);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                    Game.mainCamera.translate(0, speed, 0);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.NUM_0)) {
                    erase.setIsErase(false);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.NUM_1)) {
                    erase.setIsErase(true);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.NUM_2)) {
                    erase.setModifier(0);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.NUM_3)) {
                    erase.setModifier(1);
            }            
    }
    @Override
    public void dispose() {
    }

    @Override
    public void update(float delta) {
        handleInput();
    }

}
