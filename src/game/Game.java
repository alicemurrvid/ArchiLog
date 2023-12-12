package game;

import java.util.ArrayList;

import character.Person;
import equipment.Item;
import plugins.DataLoader;

public class Game {
	

	
	private ArrayList<Item> items = new ArrayList<Item>();
	private ArrayList<Person> people = new ArrayList<Person>();


	public Game() {
		DataLoader dl = new DataLoader();
		
		this.items = dl.initializeItems();
		this.people = dl.initializeCharacters(this.items);
		
		System.out.println("List of Items :");
		for (Item item : items ) {
			System.out.println(item.toString());
		}
		
		System.out.println("List of People :");
		for (Person person : people ) {
			System.out.println(person.toString());
		}
	}
	
}
