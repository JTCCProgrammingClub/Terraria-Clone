package worldspace;

/**
 *
 * @author roasstbeef (alex.s)
 */
public abstract class WorldSpaceObject {
        public final Coords offset;
        
        public WorldSpaceObject(Coords coords){
            offset = coords;
        }
        
        public Coords getOffset(){
            return offset;
        }
        public int getOffsetX(){
            return offset.x;
        }
        public int getOffsetY(){
            return offset.y;
        }
            

}
