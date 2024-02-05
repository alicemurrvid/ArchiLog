package interfaces;

import java.util.ArrayList;

import character.Person;
import equipment.Item;

/**
 * ILoadData
 * @author mschneider
 */
public interface ILoadData {

	ArrayList<Person> initializeCharacters(ArrayList<Item> items);
	ArrayList<Item> initializeItems();
}
