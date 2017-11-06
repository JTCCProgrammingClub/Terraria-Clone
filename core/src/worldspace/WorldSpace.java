package worldspace;

import java.util.HashMap;
import java.util.UUID;

/**
 *
 * @author roasstbeef (alex.s)
 */
public class WorldSpace {
    public static final int GRID_SIZE = 24;
    
    public static final int CHUNK_WIDTH = 32; //Units are grids
    public static final int CHUNK_HEIGHT = 32; //Units are grids
    public static HashMap<Coords, Chunk> ExistingChunks = 
            new HashMap<Coords, Chunk>();
    
    public static WorldSpaceRender render = new WorldSpaceRender();
    
    public static Coords[] getSimpleGridCoords(Coords topLeftCoords){
        return new Coords[]{topLeftCoords, 
            new Coords(topLeftCoords.x+WorldSpace.GRID_SIZE, 
            topLeftCoords.y), 
            new Coords(topLeftCoords.x+WorldSpace.GRID_SIZE, 
            topLeftCoords.y-WorldSpace.GRID_SIZE),
            new Coords(topLeftCoords.x, 
                topLeftCoords.y-WorldSpace.GRID_SIZE)};
    }
    public static Chunk chunkAt(Coords coords){
        //convertii
        int nearestChunkX = (int) (Math.floor( (double)coords.x/(double)(CHUNK_WIDTH*GRID_SIZE)) * (CHUNK_WIDTH*GRID_SIZE));
        int nearestChunkY = (int) (Math.ceil( (double) coords.y/(double) (CHUNK_HEIGHT*GRID_SIZE)) * (CHUNK_HEIGHT*GRID_SIZE));

        return ExistingChunks.get(new Coords(nearestChunkX, nearestChunkY));
    }
}
