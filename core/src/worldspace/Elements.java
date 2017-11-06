package worldspace;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author roasstbeef (alex.s)
 */
public class Elements {
    public static Map<Integer,String> data = new HashMap();
    static {
        data.put(-1, "NULL_ELEM");
        data.put(0, "Stone");
        data.put(1, "Dirt");
    }
}
