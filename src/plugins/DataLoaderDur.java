package plugins;

import java.util.ArrayList;

import character.Person;
import equipment.Item;
import interfaces.ILoadData;
import stat.Stat;

/**
 * DataLoaderDur
 * initilize basics objects of the game
 * items and characters
 * 
 * @author mschneider
 */
public class DataLoaderDur implements ILoadData {

	
	public DataLoaderDur() {}
	
	/**
	 * init the list of characters
	 * @return characters : ArrayList<Person>
	 */
	public ArrayList<Person> initializeCharacters(ArrayList<Item> items) {
		//init stat
		Stat damage = new Stat("Damage", 2);

		//init stats list for person
		ArrayList<Stat> zombieStats = new ArrayList<Stat>();
		zombieStats.add(damage);
		
		ArrayList<Stat> pigOfWarStats = new ArrayList<Stat>();
		pigOfWarStats.add(damage);
		
		//init person
		Person zombie = new Person("Zombie", 5, zombieStats);
		Person pigOfWar = new Person("Pig Of War", 10, pigOfWarStats);

		//init inventories of person
		ArrayList<Item> zombieInventory = new ArrayList<Item>();
		ArrayList<Item> pigOfWarInventory = new ArrayList<Item>();
		
		for (Item item : items) {
			if (item.getNameItem().equals("Leather armor")) {
				zombieInventory.add(item);
			} else if (item.getNameItem().equals("Wooden shield")) {
				pigOfWarInventory.add(item);
			} else if (item.getNameItem().equals("Wooden sword")) {
				pigOfWarInventory.add(item);
			}
		}
		zombie.setInventory(zombieInventory);
		zombie.actualizeStats();
		pigOfWar.setInventory(pigOfWarInventory);
		pigOfWar.actualizeStats();
		
		//init return value
		ArrayList<Person> characters = new ArrayList<Person>();
		characters.add(zombie);
		characters.add(pigOfWar);
		
		return characters;
	}
	
	
	/**
	 * init the list of items
	 * @return items : ArrayList<Item>
	 */
	public ArrayList<Item> initializeItems(){
		
		//init stats
		Stat damage = new Stat("Damage", 2);
		Stat defense = new Stat("Defense", 1);
		
		//init stats lists for item 
		ArrayList<Stat> woodenSwordStats = new ArrayList<Stat>();
		woodenSwordStats.add(damage);
		
		ArrayList<Stat> leatherArmorStats = new ArrayList<Stat>();
		leatherArmorStats.add(defense);
		
		ArrayList<Stat> woodenShieldStats = new ArrayList<Stat>();
		woodenShieldStats.add(defense);
		woodenShieldStats.add(damage);
		
		//init items
		Item woodenSword = new Item("Wooden sword", woodenSwordStats);
		Item leatherArmor = new Item("Leather armor", leatherArmorStats);
		Item woodenShield = new Item("Wooden Shield", woodenShieldStats);
		
		//init return value
		ArrayList<Item> items = new ArrayList<Item>();
		items.add(woodenShield);
		items.add(leatherArmor);
		items.add(woodenSword);
		
		return items;
	}
}
