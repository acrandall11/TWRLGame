package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import terrain.Terrain;
import terrain.WorldMap;
import characters.Race;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.math.Vector2;

public class MyGdxGame extends ApplicationAdapter implements InputProcessor {
	SpriteBatch sb;
	Sprite sprite;
	Texture texture, img, stone, grass;
	TiledMap tiledMap;
	OrthographicCamera camera;
	TiledMapRenderer tiledMapRenderer;
	float h, w;

	int tileSize = 32;

	private static Map<Vector2, Terrain> worldMap;

	private static int terrainMax = 32;

	@Override
	public void create() {
		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, w, h);
		camera.update();
		Gdx.input.setInputProcessor(this);

		sb = new SpriteBatch();

		texture = new Texture(
				Gdx.files
						.internal("A:\\Game Folder\\android\\assets\\pikachu.png"));
		stone = new Texture(
				Gdx.files
						.internal("A:\\Game Folder\\android\\assets\\Stone.png"));
		grass = new Texture(
				Gdx.files
						.internal("A:\\Game Folder\\android\\assets\\Grass.png"));
		sprite = new Sprite(texture);
		sprite.setScale((float) 0.5);
		sprite.setPosition(w / 2 - texture.getWidth() / 2,
				h / 2 - texture.getHeight() / 2);

		initWorld();
		List<Race> list = new ArrayList<Race>();
		int raceNumber = 8;
		for (int i = 0; i < raceNumber; i++) {
			list.add(new Race());
		}

		for (Race rc : list) {
			// rc.PrintRace();
		}
		for (int i = 0; i < terrainMax; i++) {
			for (int j = 0; j < terrainMax; j++) {
				System.out.print(getWorldMap().get(new Vector2(i, j)));
				if (j == terrainMax - 1) {
					System.out.println();
				}
			}
		}

	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		sb.begin();
		Map<Vector2, Terrain> dungeonMap = getWorldMap();
		for (Entry<Vector2, Terrain> entry : dungeonMap.entrySet()) {
			Vector2 coord = entry.getKey();
			Terrain tile = entry.getValue();

			switch (tile) {
			case GRASS:
				sb.draw(grass, coord.x * tileSize, coord.y * tileSize);
				break;
			case STONE:
				sb.draw(stone, coord.x * tileSize, coord.y * tileSize);
				break;
			default:
				break;
			}

		}

		sprite.setPosition(w / 2 - texture.getWidth() / 2,
				h / 2 - texture.getHeight() / 2);

		sprite.draw(sb);
		sb.end();
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Input.Keys.LEFT)
			camera.translate(-32, 0);
		if (keycode == Input.Keys.RIGHT)
			camera.translate(32, 0);
		if (keycode == Input.Keys.UP)
			camera.translate(0, 32);
		if (keycode == Input.Keys.DOWN)
			camera.translate(0, -32);
		return false;
	}

	@Override
	public boolean keyTyped(char character) {

		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

	private static void initWorld() {
		setWorldMap(new WorldMap<Terrain>());
		for (int i = 0; i < terrainMax; i++) {
			for (int j = 0; j < terrainMax; j++) {
				getWorldMap().put(new Vector2(i, j), Terrain.randomTerrain());
			}
		}

	}

	public static Map<Vector2, Terrain> getWorldMap() {
		return worldMap;
	}

	public static void setWorldMap(Map<Vector2, Terrain> worldMap) {
		MyGdxGame.worldMap = worldMap;
	}
	
	

}
