package characters;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

public class Race {
	private Set<String> specialAbilities, statBonuses, skillBonuses,
			statPenalties, desires, capabilities;

	private String terrainPref, climatePref;
	private double birthRate, deathRate;

	private Map<String, Double> hate;
	private static Map<String, Race> racesOfGame = new HashMap<String, Race>();

	private final String[] abilities = { "firebreathing", "evasion" };
	private final String[] stats = { "str", "dex", "con", "int", "wis", "cha" };
	private final String[] skills = { "fighting", "magic", "dodge" };
	private final String[] raceNames = { "hab", "ram", "dool", "arg", "bal",
			"fron", "cas", "mong", "welt", "lop", "bolk", "yon", "gorp",
			"kuld", "prang", "rab", "jor", "wert", "forl", "vang" };
	private String name;
	private int min = 0;
	private int maxAbil = 1;
	private int maxStat = 5;
	private int maxSkill = 2;
	private int maxRaces = 19;

	public Race() {
		String fearName, penalty;
		boolean notNewName = true;
		do {
			name = this.raceNames[randInt(min, maxRaces)];
			notNewName = racesOfGame.containsKey(name);
		} while (notNewName);

		this.specialAbilities = new HashSet<String>();
		this.statBonuses = new HashSet<String>();
		this.statPenalties = new HashSet<String>();
		this.skillBonuses = new HashSet<String>();
		this.hate = new HashMap<String, Double>();
		this.specialAbilities.add(abilities[randInt(min, maxAbil)]);
		this.statBonuses.add(stats[randInt(min, maxStat)]);
		this.skillBonuses.add(skills[randInt(min, maxSkill)]);
		boolean sameStat = true;
		do {
			penalty = stats[randInt(min, maxStat)];
			sameStat = statBonuses.contains(penalty);
		} while (sameStat);
		this.statPenalties.add(penalty);
		boolean sameName = true;
		do {
			fearName = raceNames[randInt(min, maxRaces)];
			sameName = fearName.equals(name);
		} while (sameName);
		Iterator<Entry<String, Race>> it = racesOfGame.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Race> pairs = (Map.Entry<String, Race>) it.next();
			this.hate.put(pairs.getValue().name, randDouble(-100.00, 100.00));
		}
		racesOfGame.put(name, this);
	}

	public Race(String string, String name2) {
		this.name = string + name2;
	}

	public static int randInt(int min, int max) {

		// NOTE: Usually this should be a field rather than a method
		// variable so that it is not re-seeded every call.
		Random rand = new Random();

		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}

	public static double randDouble(double min, double max) {
		Random r = new Random();
		return (min + (max - min) * r.nextDouble());
	}

	public void combineRaces(Race race2) {
		if (!racesOfGame.containsKey((this.name + race2.name))) {
			// combine races
			racesOfGame.put((this.name + race2.name), new Race(this.name,
					race2.name));
		}
	}

	public void PrintRace() {
		System.out.println(this.name);
		System.out.println(this.specialAbilities);
		System.out.println("Bonus = " + this.statBonuses);
		System.out.println("Penalty = " + this.statPenalties);
		System.out.println(this.skillBonuses);
		Iterator<Entry<String, Double>> it = hate.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Double> pairs = (Map.Entry<String, Double>) it
					.next();
			System.out.println(pairs.getKey() + ": " + pairs.getValue());
		}

	}

	public static Map<String, Race> getRacesOfGame() {
		return racesOfGame;
	}

	public static void setRacesOfGame(Map<String, Race> racesOfGame) {
		Race.racesOfGame = racesOfGame;
	}

	public Set<String> getSpecialAbilities() {
		return specialAbilities;
	}

	public void setSpecialAbilities(Set<String> specialAbilities) {
		this.specialAbilities = specialAbilities;
	}

	public Set<String> getStatBonuses() {
		return statBonuses;
	}

	public void setStatBonuses(Set<String> statBonuses) {
		this.statBonuses = statBonuses;
	}

	public Set<String> getSkillBonuses() {
		return skillBonuses;
	}

	public void setSkillBonuses(Set<String> skillBonuses) {
		this.skillBonuses = skillBonuses;
	}

	public Set<String> getStatPenalties() {
		return statPenalties;
	}

	public void setStatPenalties(Set<String> statPenalties) {
		this.statPenalties = statPenalties;
	}

	public Set<String> getDesires() {
		return desires;
	}

	public void setDesires(Set<String> desires) {
		this.desires = desires;
	}

	public Set<String> getCapabilities() {
		return capabilities;
	}

	public void setCapabilities(Set<String> capabilities) {
		this.capabilities = capabilities;
	}

	public String getTerrainPref() {
		return terrainPref;
	}

	public void setTerrainPref(String terrainPref) {
		this.terrainPref = terrainPref;
	}

	public String getClimatePref() {
		return climatePref;
	}

	public void setClimatePref(String climatePref) {
		this.climatePref = climatePref;
	}

	public double getBirthRate() {
		return birthRate;
	}

	public void setBirthRate(double birthRate) {
		this.birthRate = birthRate;
	}

	public double getDeathRate() {
		return deathRate;
	}

	public void setDeathRate(double deathRate) {
		this.deathRate = deathRate;
	}

	public Map<String, Double> getHate() {
		return hate;
	}

	public void setHate(Map<String, Double> hate) {
		this.hate = hate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getAbilities() {
		return abilities;
	}

	public String[] getStats() {
		return stats;
	}

	public String[] getSkills() {
		return skills;
	}

	public String[] getRaceNames() {
		return raceNames;
	}
}
