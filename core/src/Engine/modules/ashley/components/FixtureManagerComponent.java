package Engine.modules.ashley.components;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.Texture;

import Engine.utils.Pair;
import Engine.modules.box2d.FixtureSettings;

public class FixtureManagerComponent implements PooledComponent {
	
	public Map<FixtureSettings, Pair<Texture, Boolean>> fixtureMap;
	
	public FixtureManagerComponent() {
		fixtureMap = new HashMap<FixtureSettings, Pair<Texture, Boolean>>();
	}
	
	public void addFixtures(FixtureSettings...fixtureSettings) {
		for(FixtureSettings settings : fixtureSettings)
			addFixture(settings, null);
	}
	
	public void addFixture(FixtureSettings fixtureSettings, Texture texture) {
		addFixture(fixtureSettings, texture, false);
	}
	
	public void addFixture(FixtureSettings fixtureSettings, Texture texture, boolean resizeTextureToFixture) {
		Pair<Texture, Boolean> textBoolpair = new Pair<Texture, Boolean>(texture, resizeTextureToFixture);
		
		fixtureMap.put(fixtureSettings, textBoolpair);
	}
	
	@Override
	public void reset() {
		fixtureMap.clear();
	}
	
}
