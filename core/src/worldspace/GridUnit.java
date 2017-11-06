package worldspace;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class GridUnit extends WorldSpaceObject{
           
    ArrayList<EvalPointsSet> evalPointsSets = new ArrayList<EvalPointsSet>();
    ArrayList<PolyPointsSet> polyPointsSet = new ArrayList<PolyPointsSet>();

    public GridUnit(Coords coords){
        super(coords);      
        //WorldSpace.evalPointsSets.put(offset, new HashMap<UUID, EvalPointsSet>());
    }    

    public void addPointSet(EvalPointsSet pointsSet){
       evalPointsSets.add(pointsSet);
    }

    public void addPointSet(PolyPointsSet pointsSet){
       polyPointsSet.add(pointsSet);
    }
    public ArrayList<EvalPointsSet> getEvalData() {
        return evalPointsSets; 
    }
    public ArrayList<PolyPointsSet> getPolyData() {
        return polyPointsSet; 
    }    
}
