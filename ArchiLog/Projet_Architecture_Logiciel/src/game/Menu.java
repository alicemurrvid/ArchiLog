package game;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import character.Person;
import interfaces.IContent;
import interfaces.IEvenement;
import loader.Loader;
import loader.PluginDescriptor;
import stat.Stat;


/**
 * All the JPanel to manage the menu
 * @author mschneider
 *
 */
public class Menu {

	public static Menu getInstance() {
		return LazyMenu.instance;
	}
	
	private static class LazyMenu {
		private static final Menu instance = new Menu();
	}
	
	private Run run = Run.getInstance();
	private Map<String, IEvenement> evenements = new HashMap<String, IEvenement>(); 
	

	public Map<String, IEvenement> getEvenements() {
		return evenements;
	}

	/**
	 * Create the plugin panel
	 * @param plugInsDescription
	 * @return panel : JPanel
	 */
	@SuppressWarnings("static-access")
	public JPanel createPlugInPanel(List<List<PluginDescriptor>> pluginsType) {
		JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(300, 200));
		JLabel titreLabel = new JLabel("Chose the plugin you want active");
		panel.setLayout(new BorderLayout());
		panel.add(titreLabel, BorderLayout.NORTH);
		
		JPanel panelBis = new JPanel();
		panelBis.setLayout(new BoxLayout(panelBis, BoxLayout.Y_AXIS));
				
		JTextArea poorDataRepresentation = new JTextArea("Select a button", 10, 60);
		
		for (List<PluginDescriptor> plugInsDescription : pluginsType) {
			for (PluginDescriptor descr : plugInsDescription) {			
				if (! descr.autorun ) {
				
					poorDataRepresentation.setText(poorDataRepresentation.getText()+"\n"+descr.name+" : "+descr.description);
					
					JPanel panelTer = new JPanel();
					
					Image icon = new ImageIcon("src/data/coche-verte.png").getImage();
					icon = icon.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
					
					JLabel image = new JLabel(new ImageIcon(icon));
					image.setVisible(false);
		
					JButton jb = new JButton(descr.name);
					panelTer.add(jb);
					panelTer.add(image);
		
					if (descr.getType().equals("Content")) {
						jb.addActionListener(e -> { 
							IContent plugin  = (IContent) Loader.getInstance().getPlugin(descr);
							plugin.initialize();
							if (panelTer.getComponent(1).isVisible()) {
								panelTer.getComponent(1).setVisible(false);
								plugin.remove();			
							} else {
								panelTer.getComponent(1).setVisible(true);
								plugin.add();
							}
						});
					} else if (descr.getType().equals("GamePlay")) {
						jb.addActionListener(e -> { 
							if (panelTer.getComponent(1).isVisible()) {
								panelTer.getComponent(1).setVisible(false);
								//Run.getInstance().removePeopleOnList(list);
							} else {
								panelTer.getComponent(1).setVisible(true);
								//Run.getInstance().addPeopleOnList(list);
							}
						});
					} else if (descr.getType().equals("Evenement")) {
						jb.addActionListener(e -> { 
							IEvenement plugin  = (IEvenement) Loader.getInstance().getPlugin(descr);
							if (panelTer.getComponent(1).isVisible()) {
								panelTer.getComponent(1).setVisible(false);
								this.evenements.remove(descr.name);			
							} else {
								panelTer.getComponent(1).setVisible(true);
								this.evenements.put(descr.name, plugin);
							}
						});
					} else if (descr.getType().equals("Action")) {
						
					}
					
					panel.add(poorDataRepresentation, BorderLayout.CENTER);
					panelBis.add(panelTer);
				} else {
					if (descr.getType().equals("Content")) {
						IContent plugin  = (IContent) Loader.getInstance().getPlugin(descr);
						plugin.initialize();
						plugin.add();
					} else if (descr.getType().equals("GamePlay")) {
					 
					} else if (descr.getType().equals("Evenement")) {
						IEvenement plugin  = (IEvenement) Loader.getInstance().getPlugin(descr);
						this.evenements.put(descr.name, plugin);
					} else if (descr.getType().equals("Action")) {
						
					}
				}
			} 
		}
		panel.add(panelBis, BorderLayout.SOUTH);
		return panel;
	}
	
	/**
	 * Create the main panel of the game
	 * Contain all panel of the menu of the game
	 * @return panel : JPanel
	 */
	public JPanel createGamePanel() {
		JPanel panel = new JPanel();
		CardLayout layout = new CardLayout();
		panel.setLayout(layout);

		JPanel menuPanel = this.menuPanel(panel,layout);
		JPanel playPanel = this.playPanel(panel,layout);
		JPanel newGamePanel = this.newGamePanel(panel, layout);
		
		panel.add("menu", menuPanel);
		panel.add("play", playPanel);
		panel.add("newGame", newGamePanel);
		
		layout.show(panel, "menu");
		
		return panel;
	}
	
	/**
	 * The first manu panel
	 * Display the button "Play"
	 * Display the button "Exit"
	 * @param parentPanel
	 * @param layout
	 * @return panel : JPanel
	 */
	private JPanel menuPanel(JPanel parentPanel, CardLayout layout) {
		JPanel panel = new JPanel();
		JLabel titreLabel = new JLabel("Lord of the Plugin");
		panel.setLayout(new BorderLayout());
		panel.add(titreLabel, BorderLayout.NORTH);

		JPanel panelBis = new JPanel();
		panelBis.setLayout(new BoxLayout(panelBis, BoxLayout.Y_AXIS));
		JButton play = new JButton("Play");
		play.addActionListener(e -> layout.show(parentPanel, "play"));
		
		JButton exit = new JButton("exit");
		exit.addActionListener(e -> System.exit(0) );
		
		Image icon = new ImageIcon("src/data/logo.jpg").getImage();
		icon = icon.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		
		JLabel image = new JLabel(new ImageIcon(icon));
		panelBis.add(image);
		panelBis.add(play);
		panelBis.add(exit);
		panel.add(panelBis, BorderLayout.CENTER);
		
		JTextArea poorDataRepresentation = new JTextArea("Made by: Audiger Paul, Murray-Vidal Alice, Schneider Mickael");
		panel.add(poorDataRepresentation, BorderLayout.SOUTH);
		
		return panel;
	}
	
	/**
	 * The play panel
	 * Display the button "New Game"
	 * Display the button "Charge Game"
	 * Display the button "Return"
	 * @param parentPanel
	 * @param parentLayout
	 * @return panel : JPanel
	 */
	private JPanel playPanel(JPanel parentPanel, CardLayout parentLayout) {
		
		JPanel panel = new JPanel();
		BorderLayout layout = new BorderLayout();
		panel.setLayout(layout);

		JPanel panelBis = new JPanel();
		panelBis.setLayout(new BoxLayout(panelBis, BoxLayout.Y_AXIS));
		
		JButton newGame = new JButton("New game");
		newGame.addActionListener(e -> parentLayout.show(parentPanel, "newGame"));
		
		JButton returnButton = new JButton("Return");
		returnButton.addActionListener(e -> parentLayout.show(parentPanel, "menu"));
		
		panelBis.add(newGame);
		panelBis.add(returnButton);

		panel.add(panelBis, BorderLayout.CENTER);

		return panel;

	}
	
	
	/**
	 * The newGame Panel
	 * Create a new instance of game
	 * Launch the new run
	 * Display the button "Return"
	 * @param parentPanel
	 * @param parentLayout
	 * @return panel : JPanel
	 */
	private JPanel newGamePanel(JPanel parentPanel, CardLayout parentLayout) {
		JPanel panel = new JPanel();
		BorderLayout layout = new BorderLayout();
		panel.setLayout(layout);
		
		JPanel panelBis = new JPanel();
		panelBis.setLayout(new BoxLayout(panelBis, BoxLayout.Y_AXIS));
		
		JPanel panelTer = new JPanel();
		
		JLabel name = new JLabel("Your Pseudo :");
		JTextField pseudo = new JTextField(30);
		
		panelTer.add(name);
		panelTer.add(pseudo);

		JButton startGame = new JButton("StartGame");
		startGame.addActionListener(e -> {
			ArrayList<Stat> stats = new ArrayList<Stat>();
			Stat basicDamage = new Stat("Damage", 4);
			Stat basicDefence = new Stat("Defence", 1);
			stats.add(basicDefence);
			stats.add(basicDamage);
			run.setCurrentPerson(new Person(pseudo.getText(),20,stats));
			run.getCurrentPerson().actualizeStats();
			JPanel game = run.game();
			parentPanel.add("game", game);

			parentLayout.show(parentPanel, "game");
		});
		
		JButton returnButton = new JButton("Return");
		returnButton.addActionListener(e -> parentLayout.show(parentPanel, "play"));
		
		panelBis.add(startGame);
		panelBis.add(returnButton);

		panel.add(panelTer, BorderLayout.CENTER);
		panel.add(panelBis, BorderLayout.SOUTH);

		return panel;
	}
	
}
