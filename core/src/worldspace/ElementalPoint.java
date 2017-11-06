package worldspace;

/**
 *
 * @author roasstbeef (alex.s)
 */
public class ElementalPoint extends WorldSpaceObject{
    private int EUID;
    public ElementalPoint(Coords coords) {
        super(coords);
    }
    public ElementalPoint(Coords coords, int EUID) {
        super(coords);
        this.EUID = EUID;
    }

    public int getEUID() {
        return EUID;
    }

    public void setEUID(int EUID) {
        this.EUID = EUID;
    }


}
