package worldspace;

import com.badlogic.gdx.math.Circle;
import java.util.Map;
import java.util.UUID;


/**
 *
 * @author roasstbeef (alex.s)
 */
public class ModifierEvaluator extends Evaluator {
    private Circle circle;
    private boolean erase = false;
    
    public ModifierEvaluator(){
        
    }
    public ModifierEvaluator(Chunk chunk, Circle circle) {
        this(chunk);
        this.circle = circle;
        setModifier(0);
    }
    public ModifierEvaluator(Chunk chunk){
        super(chunk);
    }

    public boolean modifier(int x, int y){
        if(modifier == 1){     
            for (EvalPointsSet set : chunk.getGridUnit(new Coords(x,y))
                    .getEvalData()) {
                for (ElementalPoint point : set.getData()) {

                    if(point.getEUID() != getFillCheck())
                            point.setEUID(getFillVal());
                }              
                chunk.getGridUnit(new Coords(x,y)).getPolyData().get(0)
                        .setGridCase(set.getCase() );
            }                     
        }
        else if(modifier == 0){
            return this.circle.contains(x, y);        
        }
        return false;
    }
    
    public void setModifier(Circle circle){
        this.circle = circle;
        this.modifier= 0;
    }
    
    public void setIsErase(boolean erase){
        this.erase = erase;
    }
    public int getFillCheck(){
        return (erase) ? -1:1;
    }
    public int getFillVal(){
        return (erase) ? -1:1;
    }
    
    @Override
    public void eval() {     
        if(modifier == 0){
            for(int y = 0; y < WorldSpace.CHUNK_HEIGHT; y++){
                for(int x = 0; x < WorldSpace.CHUNK_WIDTH; x++){                

                    for (EvalPointsSet set : chunk.getEvalGrid()[y][x]
                            .getEvalData()) {
                        for (ElementalPoint point : set.getData()) {

                            if(point.getEUID() != getFillCheck()){
                                if(modifier(point.offset.x, point.offset.y))
                                    point.setEUID(getFillVal());
                            }
                            chunk.getEvalGrid()[y][x].getPolyData().get(0)
                                    .setGridCase(set.getCase() );
                        }
                    }
                }
            }     
        }
    }  

    @Override
    public void eval(Coords coord) {
        setChunk(WorldSpace.chunkAt(coord));
        System.out.println( WorldSpace.chunkAt(coord) );
        if(modifier==0){
            this.eval();
        }else{
            modifier(coord.x, coord.y);
        }
    }

    @Override
    public void eval(Chunk chunk) {
    }

    
}
