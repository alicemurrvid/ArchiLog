package plugins;

import java.awt.BorderLayout;
import java.util.Random;

import javax.swing.JPanel;

import character.Person;
import game.Run;
import interfaces.IEvenement;

public class CombatEvent implements IEvenement {

	@Override
	public JPanel eventInterface() {
		JPanel panel = new JPanel();
		BorderLayout layout = new BorderLayout();
		panel.setLayout(layout);
		
		int nbPerson = Run.getInstance().getPeople().size();
		System.out.println(nbPerson);
		Person opponent = Run.getInstance().getPeople().get( new Random().nextInt(nbPerson));
		JPanel statsOpponentPanel = Run.getInstance().diplayPerson(opponent);
		// TODO ajouter boutton action

		

		panel.add(statsOpponentPanel, BorderLayout.EAST);
		
		return panel;
	}

}
