package worldspace;

/**
 *
 * @author roasstbeef (alex.s)
 */
public class Coords {
    public int x, y;
    
    
    public boolean equals(Object o) {
        Coords c = (Coords) o;
        return c.x == x && c.y == y;
    }

    public Coords(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int hashCode() {
      int tmp = ( y +  ((x+1)/2));
               return x +  ( tmp * tmp);
    }
}
