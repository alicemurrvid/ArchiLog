package character;

import java.util.ArrayList;

import action.Action;
import action.Attack;
import action.Defend;
import action.Flee;
import action.Heal;
import action.Rummage;
import action.Take;
import equipment.Item;
import stat.Stat;

public class Person {

	// Attributs of a Character
	private String nameCharacter;
	private ArrayList<Item> inventory;
	private ArrayList<Action> actions;
	private ArrayList<Stat> stats;
	private int lifePoint;
	
	/**
	 * Constructor of Character
	 * @param nameCharacter
	 */
	public Person(String nameCharacter, int lp) {
		this.inventory = new ArrayList<Item>();
		this.actions = new ArrayList<Action>();
		this.stats = new ArrayList<Stat>();

		if (nameCharacter != null && nameCharacter.length() > 0 && nameCharacter.length() < 20) {
			this.nameCharacter = nameCharacter;
		} else if (nameCharacter == null) {
			// Error to lauch "Saisir un nom de personnage"
		} else {
			// Error to lauch "Le nom du personnage doit comporter entre 1 et 20 caractères"
		}
		this.setActions();
		this.lifePoint = lp;
	}
	
	/**
	 * remove all Stats of the Character
	 * recalculate all of them in function is item
	 */
	public void actualizeStats() {
		this.resetStats();
		for (Item item : this.inventory) {
			for(Stat stat : item.getStatsItem()) {
				if(this.stats.contains(stat)) {
					int currentValue = this.stats.get(this.stats.indexOf(stat)).getValueStat();
					int itemValue = stat.getValueStat();
					this.stats.get(this.stats.indexOf(stat)).setValueStat(currentValue + itemValue);
				} else {
					this.stats.add(stat);
				}
			}
		}
	}
	
	/**
	 * recover the basic Stats of the Character
	 */
	private void resetStats() {
		this.stats.clear();
		Stat strength = new Stat("Strength", 1);
		Stat resistance = new Stat("Resistance", 1);
		ArrayList<Stat> resetStats = new ArrayList<Stat>();
		resetStats.add(strength);
		resetStats.add(resistance);
		this.stats = resetStats;
	}
	
	/**
	 * inflict damage to the lifePoint Character
	 * @param damage
	 * @return true if the Charactere is dead
	 * 		   false else
	 */
	public boolean takeDamage(int damage) {
		if(this.getLifePoint() <= damage) {
			return true;
		} else {
			this.lifePoint -= damage;
			return false;
		}
	}

	/************** Setters **************/

	public void setInventory(ArrayList<Item> items) {
		
		for (Item item : items) {
			this.inventory.add(item);
		}
	}

	private void setActions() {
		this.actions.add(new Attack());
		this.actions.add(new Defend());
		this.actions.add(new Flee());
		this.actions.add(new Heal());
		this.actions.add(new Rummage());
		this.actions.add(new Take());
	}

	/************** Getters **************/
	public String getName() {
		return this.nameCharacter;
	}

	public ArrayList<Item> getInventory() {
		return this.inventory;
	}

	public ArrayList<Action> getActions() {
		return this.actions;
	}

	public int getLifePoint() {
		return this.lifePoint;
	}
	
	/************** To String **************/

	@Override
	public String toString() {
		return "Person [nameCharacter=" + nameCharacter + ", inventory=" + inventory + ", actions=" + actions
				+ ", stats=" + stats + ", lifePoint=" + lifePoint + "]";
	}
}
