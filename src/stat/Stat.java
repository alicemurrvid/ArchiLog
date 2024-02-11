package stat;

/**
 * Stat
 * @author mschneider
 */
public class Stat {
	
	// Attributs of a Stat
	private String nameStat;
	private int valueStat;
	
	/**
	 * Constructor of Stat
	 * @param nameStat
	 * @param valueStat
	 */
	public Stat(String nameStat, long valueStat) {
		this.nameStat = nameStat;
		this.valueStat = (int) valueStat;
	}

	/************** Setters **************/
	/**
	 * setters of valueStat attribut
	 * @param valueStat
	 */
	public void setValueStat(int valueStat) {
		this.valueStat = valueStat;
	}
	
	/************** Getters **************/
	/**
	 * getter of nameStat attribut
	 * @return the name of the Stat
	 */
	public String getNameStat() {
		return nameStat;
	}

	/**
	 * getter of valueStat attribut
	 * @return the valueStat of the Stat
	 */
	public int getValueStat() {
		return valueStat;
	}
	
	/************** Getters **************/

	@Override
	public String toString() {
		return "Stat [nameStat=" + nameStat + ", valueStat=" + valueStat + "]";
	}
}
