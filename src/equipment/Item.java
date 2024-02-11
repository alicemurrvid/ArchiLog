package equipment;

import java.util.ArrayList;

import stat.Stat;

/**
 * Item
 * @author mschneider
 */
public class Item {
	
	// Attributs of a Item
	private String nameItem;
	private ArrayList<Stat> statsItem;
	private int lifeItem;
	
	/**
	 * Constructor of Item
	 * @param nameItem
	 * @param statsItem
	 */
	public Item(String nameItem, ArrayList<Stat> statsItem) {
		this.statsItem = statsItem;
		this.nameItem = nameItem;
		this.lifeItem = 2;
	}
	
	/**
	 * decrease the life of the item
	 * @return true if the item is destroyed
	 * 		   false else
	 */
	public boolean usedItem() {
		if(this.getLifeItem() == 2) {
			this.lifeItem = this.getLifeItem() - 1;
			return false;
		} else {
			// the life item will be 0
			return true;
		}
		//TODO Must be used after each fight
	}
	
	/************** Getters **************/
	/**
	 * getter of nameItem attribut
	 * @return the name of the Item
	 */
	public String getNameItem() {
		return nameItem;
	}

	/**
	 * getter of statsItem attribut
	 * @return the statsItem of the Item
	 */
	public ArrayList<Stat> getStatsItem() {
		return statsItem;
	}
	
	/**
	 * getter of lifeItem attribut
	 * @return the lifeItem of the Item
	 */
	public int getLifeItem() {
		return lifeItem;
	}
	
	/************** To String **************/

	@Override
	public String toString() {
		return "Item [nameItem=" + nameItem + ", statsItem=" + statsItem + ", lifeItem=" + lifeItem + "]";
	}
}
