package com.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import Engine.game.Game;
import tests.destructable_terrain.DestructableTerrain;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		//DestructableTerrain game = new DestructableTerrain();
		DestructableTerrain game = new DestructableTerrain();
                /*
		config.title = Game.settings.title;
		config.width = Game.settings.width;
		config.height = Game.settings.height;
                */
		config.fullscreen = false;
		config.resizable = false;
                config.vSyncEnabled = false; // Setting to false disables vertical sync
                config.foregroundFPS = 0; // Setting to 0 disables foreground fps throttling
                config.backgroundFPS = 0; // Setting to 0 disables background fps throttling		
		new LwjglApplication(game, config);
	}
}
