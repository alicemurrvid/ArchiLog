package plugins;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import character.Person;
import equipment.Item;
import game.Run;
import interfaces.IContent;
import stat.Stat;

public class PersonPlus implements IContent {

	private static final String CHARACTERSCONFIGPATH = "src/data/person+.json";
	public ArrayList<Person> contentToAdd;
	
	public void add() {
		Run.getInstance().addPeopleOnList(this.contentToAdd);
	}
	
	public void remove() {
		Run.getInstance().removePeopleOnList(this.contentToAdd);
	}
	
	/**
	 * initialize the characters list of the game by a JSON file
	 */
	@SuppressWarnings("rawtypes")
	public void initialize() {
		ArrayList<Item> items = Run.getInstance().getItems();
		ArrayList<Person> personList = new ArrayList<Person>();
		JSONParser jsonParser = new JSONParser();

		try (FileReader reader = new FileReader(CHARACTERSCONFIGPATH)){
			JSONObject obj = (JSONObject) jsonParser.parse(reader);
			if (! obj.isEmpty()) {
				@SuppressWarnings("unchecked")
				Set<String> keys = obj.keySet();
				for (String key : keys) {
					ArrayList<Stat> stats = new ArrayList<Stat>();
					ArrayList<Item> inventory = new ArrayList<Item>();
					long lp = 0;
					if (((HashMap) obj.get(key)).get("Defense") != null) {
						stats.add(new Stat("Defense",(long)((HashMap) obj.get(key)).get("Defense")));
					}
					if (((HashMap) obj.get(key)).get("Damage") != null) {
						stats.add(new Stat("Damage",(long)((HashMap) obj.get(key)).get("Damage")));
					}
					if (((HashMap) obj.get(key)).get("Items") != null) {
						JSONArray personItems = (JSONArray) ((HashMap) obj.get(key)).get("Items");
						for (Object itemName : personItems) {
							Iterator i = items.iterator();
							boolean find = false;
							while (i.hasNext() && !find ) {
								Item item = (Item) i.next();
								if(item.getNameItem().equals(itemName)) {
									inventory.add(items.get(items.indexOf(item)));
									find = true;
								}
							}
						}
					}
					if (((HashMap) obj.get(key)).get("LifePoint") != null) {
						lp = (long)((HashMap) obj.get(key)).get("LifePoint");
					}
					Person person = new Person(key,lp,stats);
					person.setInventory(inventory);
					person.actualizeStats();
					personList.add(person);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.contentToAdd = personList;
	}
}
