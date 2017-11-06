package worldspace;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 *
 * @author roasstbeef (alex.s)
 */
public class Chunk extends WorldSpaceObject{
    
    private HashMap<Integer, Boolean> EvaluatedEvaluators;
    
    private GridUnit[][] EvalGrid = 
            new GridUnit[WorldSpace.CHUNK_WIDTH]
                    [WorldSpace.CHUNK_HEIGHT];
    
    public Chunk(Coords coords){
        super(coords);
        WorldSpace.ExistingChunks.put(offset, this);
        initEvalGridUnits();
    }
    public void initEvalGridUnits(){
        for(int y = 0; y < WorldSpace.CHUNK_HEIGHT; y++){
            for(int x = 0; x < WorldSpace.CHUNK_WIDTH; x++){                
                GridUnit unit = new GridUnit(new Coords(offset.x+
                        (x*WorldSpace.GRID_SIZE),
                        offset.y-y*WorldSpace.GRID_SIZE));
                //System.out.println(unit.offset.x+", "+unit.offset.y);
                getEvalGrid()[y][x] = unit;
            }
        }
    }                  
    
    public void fill(){
        BaseTerrainEvaluator terrain = new BaseTerrainEvaluator();
        terrain.eval(this);                   
    }

    public GridUnit[][] getEvalGrid() {
        return EvalGrid;
    }
    public GridUnit getGridUnit(Coords coord){
        int x= (coord.x-this.getOffsetX())/WorldSpace.GRID_SIZE;
        int y = ((coord.y-this.getOffsetY())/WorldSpace.GRID_SIZE)/-1; 
        
        System.out.println("World Coord: "+coord.x+", "+coord.y);
        System.out.println("Chunk Offset: "+this.getOffsetX()+", "+this.getOffsetY());
        System.out.println("local coord: "+x+", "+y);
        System.out.println("---");

        return EvalGrid[y][x];
    }

    public void setEvalGrid(GridUnit[][] EvalGrid) {
        this.EvalGrid = EvalGrid;
    }
}
