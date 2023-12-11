package game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.fasterxml.jackson.core.type.TypeReference;  
import com.fasterxml.jackson.databind.ObjectMapper;

import character.Person;
import equipment.Item;
import stat.Stat;

public class Game {
	
	private static final String CHARACTERSCONFIGPATH = "src/data/characters.json";
	private static final String ITEMSCONFIGPATH = "src/data/items.json";
	
	private static ArrayList<Item> items = new ArrayList<Item>();
	private static ArrayList<Person> people = new ArrayList<Person>();


	public Game() {
		try {
			initializeItems();
			initializeCharacters();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("List of Items :");
		for (Item item : items ) {
			System.out.println(item.toString());
		}
		
		System.out.println("List of People :");
		for (Person person : people ) {
			System.out.println(person.toString());
		}
	}

	@SuppressWarnings("unchecked")
	public void initializeCharacters() {
		
		ObjectMapper mapper = new ObjectMapper();  
		
		try {
			// Create a peoples Map with the JSON file
			/*File fileObj = new File(CHARACTERSCONFIGPATH);  
			Map<String, Object> peoplesObj = mapper.readValue(  
					fileObj, new TypeReference<Map<String, Object>>() {  
            });   
			
			// Get the keys of the peoples map
			String[] peoplesNameObj = (String[]) peoplesObj.keySet().toArray();
			ArrayList<String> peoplesName = new ArrayList<String>();
			
			for (Object peopleName : peoplesNameObj) {
				peoplesName.add(peopleName.toString());
			}
			for (String name : peoplesName) {
				
				// Get Stats of the items
				Field[] personProperties = peoplesObj.get(name).getClass().getDeclaredFields();
				int lifePoint = 0;
				ArrayList<Item> inventory = new ArrayList<Item>();

				for (Field property : personProperties) {
					property.setAccessible(true);

					if (property.getName().equals("LifePoint")) {
						lifePoint = (int) property.get("LifePoint");
					} else {
						inventory = (ArrayList<Item>) property.get("Items");
					}
				}
				Person person = new Person(name, lifePoint);
				person.setInventory(inventory);
				
				
				people.add(person);
			}*/
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	public void initializeItems() throws FileNotFoundException, IOException {
		
		JSONParser jsonParser = new JSONParser();
		
		try (FileReader reader = new FileReader(ITEMSCONFIGPATH)){
			JSONObject obj = (JSONObject) jsonParser.parse(reader);
			JSONArray array = obj.
			
		} 
		
			// Create a items Map with the JSON file
			/*File fileObj = new File(ITEMSCONFIGPATH);  
			Map<String, Object> itemsObj = mapper.readValue(  
					fileObj, new TypeReference<Map<String, Object>>() {  
            });   
			
			// Get the keys of the items map
			Object[] itemsNameObj = itemsObj.keySet().toArray();
			ArrayList<String> itemsName = new ArrayList<String>();
			
			for (Object itemName : itemsNameObj) {
				itemsName.add(itemName.toString());
			}
			
			for (String name : itemsName) {
				ArrayList<Stat> stats = new ArrayList<Stat>();
				
				// Get Stats of the items
				//Method getMethod =itemsObj.get(name).getClass().getMethod("get");
				Field[] itemStats = itemsObj.get(name).getClass().getDeclaredFields();
				
				for (Field stat : itemStats) {
					stat.setAccessible(true);
					stats.add(new Stat(stat.getName(),(int) stat.get(stat)));
				}
				
				items.add(new Item(name, stats));
			}*/
	
	}

	
}
