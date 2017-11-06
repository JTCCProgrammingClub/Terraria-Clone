package tests.destructable_terrain;

import Engine.game.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector3;
import worldspace.ModifierEvaluator;

/**
 *
 * @author roasstbeef (alex.s)
 */
public class SimpleInputProcessor implements InputProcessor{
        
   /* @Override
    public boolean touchDragged (int x, int y, int pointer) {
        int mX = Gdx.input.getX();
        int mY = Gdx.input.getY();
        Vector3 worldCoord = Game.mainCamera.unproject(new Vector3(mX, mY, 0));               
        System.out.println(worldCoord.x+", "+worldCoord.y);

        ModifierEvaluator erase = new ModifierEvaluator(
                new Circle((int) worldCoord.x, (int) worldCoord.y, 72));

        //chunk.fill(erase);
        return true;
    }*/
    @Override
    public boolean keyDown(int keycode){
        if(keycode == Input.Keys.W   ){
            Game.mainCamera.translate(1,0);
            Game.mainCamera.update();
        }else if(keycode == Input.Keys.A){
            Game.mainCamera.translate(0,-1);
            Game.mainCamera.update();
        }else if(keycode == Input.Keys.S   ){
        }else if(keycode == Input.Keys.D   ){
        }


        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean keyTyped(char character) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean scrolled(int amount) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
