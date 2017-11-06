package worldspace;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import tools.noise.Noise1d;

/**
 *
 * @author roasstbeef (alex.s)
 */
public class BaseTerrainEvaluator extends Evaluator {
    
    //evaluation input, how evaluator determines if point is filled or not
    private Noise1d noise = new Noise1d();
    
    //provide a transform matrix in Eval class for the evaluation inputs
    private UUID UID;
    
    public BaseTerrainEvaluator() {
        this.UID= UUID.randomUUID();
    }

    @Override
    public void eval(Chunk chunk) {
        for(int y = 0; y < WorldSpace.CHUNK_HEIGHT; y++){
            for(int x = 0; x < WorldSpace.CHUNK_WIDTH; x++){                
                GridUnit gridUnit = chunk.getEvalGrid()[y][x];

                EvalPointsSet newPointsSet = 
                        new EvalPointsSet(gridUnit.getOffset(),UID);
                               
                for(ElementalPoint point : newPointsSet.getData()){
                    double total = noise.perlinNoise(6, .2, 5, point.offset.x+.01);
                    if(total > point.offset.y)
                        point.setEUID(1);                    
                }
                PolyPointsSet newPolyPointsSet = 
                        new PolyPointsSet(gridUnit.getOffset(),
                                newPointsSet.getCase());

                gridUnit.addPointSet(newPointsSet);
                gridUnit.addPointSet(newPolyPointsSet);
                
                WorldSpace.render.addPolySet(newPolyPointsSet);

            }      
        }
    }

    @Override
    public void eval(Coords coord) {
    }

    @Override
    public void eval() {
    }
}
