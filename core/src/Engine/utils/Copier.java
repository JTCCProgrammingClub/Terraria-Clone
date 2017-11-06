package Engine.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.ObjectInputStream;

/**
 * Created this because the clone() from the Cloneable interface is apparently
 * not recommended. Due to having to override the clone method and possible due
 * to odd problems occuring.  Clone() only does shallow cloning.
 *
 */
public abstract class Copier implements Serializable{
	private static final long serialVersionUID = 8063703631709857853L;

	public Object deepCopy(Object object) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			
			oos.writeObject(object);
			
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			
			return ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
