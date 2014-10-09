package terrain;

import java.util.Random;

public enum Terrain {

	GRASS("Grass", true), STONE("Stone", false), TREES("Trees", false), WATER(
			"Water", true);

	private String type;
	private boolean passable;

	Terrain(String str, boolean pass) {

		setType(str);
		setPassable(pass);
	}

	public static Terrain randomTerrain() {
		int pick = new Random().nextInt(Terrain.values().length);
		return Terrain.values()[pick];
	}

	public boolean isPassable() {
		return passable;
	}

	public void setPassable(boolean passable) {
		this.passable = passable;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
