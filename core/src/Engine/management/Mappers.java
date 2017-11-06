package Engine.management;

import com.badlogic.ashley.core.ComponentMapper;

import Engine.modules.ashley.components.BodyComponent;
import Engine.modules.ashley.components.FixtureManagerComponent;
import Engine.modules.ashley.components.PositionComponent;
import Engine.modules.ashley.components.SpriteComponent;

public class Mappers {
	
	public static final ComponentMapper<PositionComponent> position = ComponentMapper.getFor(PositionComponent.class);
	public static final ComponentMapper<SpriteComponent> sprite = ComponentMapper.getFor(SpriteComponent.class);
	public static final ComponentMapper<BodyComponent> body = ComponentMapper.getFor(BodyComponent.class);
	public static final ComponentMapper<FixtureManagerComponent> fixtureManager = ComponentMapper.getFor(FixtureManagerComponent.class);
	
}
