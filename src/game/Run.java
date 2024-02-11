package game;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import character.Person;
import equipment.Item;
import stat.Stat;

public class Run {

	public static Run getInstance() {
		return LazyRun.instance;
	}
	
	private static class LazyRun {
		private static final Run instance = new Run();
	}

	private ArrayList<Item> items = new ArrayList<Item>();
	private ArrayList<Person> people = new ArrayList<Person>();
	
	private Person currentPerson;

	/**
	 * get the people list
	 * @return people : ArrayList<Person>
	 */
	public ArrayList<Person> getPeople() {
		return people;
	}

	/**
	 * get the items list
	 * @return items : ArrayList<Item>
	 */
	public ArrayList<Item> getItems() {
		return items;
	}

	/**
	 * get the current person
	 * @return currentPerson : Person
	 */
	public Person getCurrentPerson() {
		return currentPerson;
	}

	/**
	 * set the items list
	 * @param items : ArrayList<Item>
	 */
	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

	/**
	 * set the people list
	 * @param people : ArrayList<Person>
	 */
	public void setPeople(ArrayList<Person> people) {
		this.people = people;
	}

	/**
	 * set the current person
	 * @param currentPerson : Person
	 */
	public void setCurrentPerson(Person currentPerson) {
		this.currentPerson = currentPerson;
	}
	
	/**
	 * Add the people list given in parameter to the actual 
	 * people list
	 * @param newList : ArrayList<Person>
	 */
	public void addPeopleOnList(ArrayList<Person> newList) {
		this.people.addAll(newList);
	}
	
	/**
	 * Remove the people list given in parameter to the actual 
	 * people list
	 * @param newList : ArrayList<Person>
	 */
	public void removePeopleOnList(ArrayList<Person> newList) {
		for (Person p : newList ) {
			Iterator<Person> it =  people.iterator();
	        while ( it.hasNext()) {
	            Person p1 = it.next();
	            if (p1.getName().equals(p.getName())) {
	                it.remove();
	            }
	        }
		}
	}

	/**
	 * Manage the all run
	 *
	 * @return
	 */
	public JPanel game() {
		JPanel panel = new JPanel();
		BorderLayout layout = new BorderLayout();
		panel.setLayout(layout);
		JPanel panelCurrentPersonInfo = this.diplayPerson(this.currentPerson);

		JPanel startPanel = new JPanel();
		startPanel.setLayout(new BorderLayout());
		
		JPanel secondPanel = new JPanel();
		CardLayout cardLayout = new CardLayout();
		secondPanel.setLayout(cardLayout);
		
		startPanel.add(secondPanel, BorderLayout.CENTER);
		
		JButton nextBtn = new JButton("Start the fight");
		JButton nextBtn2 = new JButton("Start the rest");
		nextBtn2.setVisible(false);
		
		nextBtn.addActionListener(e-> {
			if (this.currentPerson.getLifePoint() > 0) {
				nextBtn.setVisible(false);
				JPanel combatPanel = Menu.getInstance().getEvenements().get("CombatEvent").eventInterface();
				secondPanel.add("combatPanel", combatPanel);
				cardLayout.show(secondPanel, "combatPanel");
				nextBtn2.setVisible(true);
			}
		});
		
		nextBtn2.addActionListener(e-> {
			if (this.currentPerson.getLifePoint() > 0) {
				nextBtn2.setVisible(false);
				JPanel horsCombatPanel = Menu.getInstance().getEvenements().get("HorsCombatEvent").eventInterface();
				secondPanel.add("horsCombatPanel", horsCombatPanel);
				cardLayout.show(secondPanel, "horsCombatPanel");
				nextBtn.setVisible(true);
			}
		});
		
		JPanel panelButton = new JPanel();
		panelButton.add(nextBtn);
		panelButton.add(nextBtn2);
		startPanel.add(panelButton, BorderLayout.SOUTH);
		
		panel.add(panelCurrentPersonInfo, BorderLayout.WEST);
		panel.add(startPanel, BorderLayout.CENTER);

		
		
		
		return panel;
	}
	
	/**
	 * Display informations on the person given
	 * @param p
	 * @return panel : JPanel
	 */
	public JPanel diplayPerson(Person p) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JLabel name = new JLabel(p.getName());
		JTextArea infos = new JTextArea();
		infos.setText(infos.getText() + "LifePoint : " + p.getLifePoint() + "\n");
		infos.setText(infos.getText() + "Inventory : \n");
		for(Item i : p.getInventory()) {
			infos.setText(infos.getText() + "	- " + i.getNameItem() + " (" + i.getLifeItem() + ")\n");
		}
		infos.setText(infos.getText() + "Stats : \n");
		for(Stat s : p.getStats()) {
			infos.setText(infos.getText() + "	- " +  s.getNameStat() + " (" + s.getValueStat() + ")\n");
		}

		panel.add(name);
		panel.add(infos);
		
		return panel;
	}
}
