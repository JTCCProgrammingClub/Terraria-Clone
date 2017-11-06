package Engine.modules.ashley.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool.Poolable;

public interface PooledComponent extends Poolable, Component{
	// just something to help avoid typing implements Poolable, Component every time for every poolable component
}
