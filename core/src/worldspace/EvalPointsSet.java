package worldspace;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 *
 * @author roasstbeef (alex.s)
 */
public class EvalPointsSet extends PointsSet{    
    public EvalPointsSet(Coords coords) {
        super(coords);
        initPointsSet();
    }    
    public EvalPointsSet(Coords coords, UUID UID) {
        super(coords);
        initPointsSet();
    }

    public int getCase(){
        if (getData().get(0).getEUID() == -1 && 
                getData().get(1).getEUID() == -1 &&
                getData().get(2).getEUID() == -1 &&
                getData().get(3).getEUID() == -1){
            return 0;
        }
        else if (getData().get(0).getEUID() != -1 && 
                getData().get(1).getEUID() != -1 &&
                getData().get(2).getEUID() != -1 &&
                getData().get(3).getEUID() != -1){
            return 15;
        }
        else if (getData().get(0).getEUID() != -1 && 
                getData().get(1).getEUID() == -1 &&
                getData().get(2).getEUID() == -1 &&
                getData().get(3).getEUID() == -1){
            return 1;
        }
        else if (getData().get(0).getEUID() == -1 && 
                getData().get(1).getEUID() != -1 &&
                getData().get(2).getEUID() == -1 &&
                getData().get(3).getEUID() == -1){
            return 2;
        }
        else if (getData().get(0).getEUID() != -1 && 
                getData().get(1).getEUID() != -1 &&
                getData().get(2).getEUID() == -1 &&
                getData().get(3).getEUID() == -1){
            return 3;
        }  
        else if (getData().get(0).getEUID() == -1 && 
                getData().get(1).getEUID() == -1 &&
                getData().get(2).getEUID() != -1 &&
                getData().get(3).getEUID() == -1){
            return 4;
        }
        else if (getData().get(0).getEUID() != -1 && 
                getData().get(1).getEUID() == -1 &&
                getData().get(2).getEUID() != -1 &&
                getData().get(3).getEUID() == -1){
            return 5;
        }
        else if (getData().get(0).getEUID() == -1 && 
                getData().get(1).getEUID() != -1 &&
                getData().get(2).getEUID() != -1 &&
                getData().get(3).getEUID() == -1){
            return 6;
        }
        else if (getData().get(0).getEUID() != -1 && 
                getData().get(1).getEUID() != -1 &&
                getData().get(2).getEUID() != -1 &&
                getData().get(3).getEUID() == -1){
            return 7;
        }
        else if (getData().get(0).getEUID() == -1 && 
                getData().get(1).getEUID() == -1 &&
                getData().get(2).getEUID() == -1 &&
                getData().get(3).getEUID() != -1){
            return 8;
        }
        else if (getData().get(0).getEUID() != -1 && 
                getData().get(1).getEUID() == -1 &&
                getData().get(2).getEUID() == -1 &&
                getData().get(3).getEUID() != -1){
            return 9;
        }
        else if (getData().get(0).getEUID() == -1 && 
                getData().get(1).getEUID() != -1 &&
                getData().get(2).getEUID() == -1 &&
                getData().get(3).getEUID() != -1){
            return 10;
        }
        else if (getData().get(0).getEUID() != -1 && 
                getData().get(1).getEUID() != -1 &&
                getData().get(2).getEUID() == -1 &&
                getData().get(3).getEUID() != -1){
            return 11;
        }
        else if (getData().get(0).getEUID() == -1 && 
                getData().get(1).getEUID() == -1 &&
                getData().get(2).getEUID() != -1 &&
                getData().get(3).getEUID() != -1){
            return 12;
        }
        else if (getData().get(0).getEUID() != -1 && 
                getData().get(1).getEUID() == -1 &&
                getData().get(2).getEUID() != -1 &&
                getData().get(3).getEUID() != -1){
            return 13;
        }
        else if (getData().get(0).getEUID() == -1 && 
                getData().get(1).getEUID() != -1 &&
                getData().get(2).getEUID() != -1 &&
                getData().get(3).getEUID() != -1){
            return 14;
        }
        return 0;
    }
    public void initPointsSet(){
        //this.EUID = -1;
        Coords[] nodes = WorldSpace.getSimpleGridCoords(offset);
        getData().add(0, new ElementalPoint(nodes[0],-1));
        getData().add(1, new ElementalPoint(nodes[1],-1));
        getData().add(2, new ElementalPoint(nodes[2],-1));
        getData().add(3, new ElementalPoint(nodes[3],-1));
        
    }
    
}
