package com.mygdx.game.desktop;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import terrain.Terrain;
import terrain.WorldMap;
import characters.Race;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGdxGame;

public class DesktopLauncher {

	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new MyGdxGame(), config);

	}
}
