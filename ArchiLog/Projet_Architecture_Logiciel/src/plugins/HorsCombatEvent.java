package plugins;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import interfaces.IEvenement;

public class HorsCombatEvent implements IEvenement {

	@Override
	public JPanel eventInterface() {
		JPanel panel = new JPanel();
		BorderLayout layout = new BorderLayout();
		panel.setLayout(layout);
		
		JLabel text = new JLabel("You won the fight");
		// TODO ajouter boutton action

		

		panel.add(text, BorderLayout.CENTER);
		
		return panel;
	}

}
