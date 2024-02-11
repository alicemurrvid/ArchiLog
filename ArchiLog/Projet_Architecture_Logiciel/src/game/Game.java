package game;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import interfaces.ILoadData;
import loader.Loader;
import loader.PluginDescriptor;

/**
 * 
 * @author mschneider, paudiger, amurrayvidal
 */
public class Game {
	
	private JFrame frame;
	private JPanel mainPanel;
    private CardLayout cardLayout;
    
    private Menu menu = Menu.getInstance();
    private Run run = Run.getInstance();
	
	public Game(){
		
		
		frame = new JFrame("Lord of the PLugin");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Image icon = new javax.swing.ImageIcon("src/data/logo.jpg").getImage();
		frame.setIconImage(icon);
		frame.setPreferredSize(new Dimension(1200, 750));

		
		mainPanel = new JPanel();
		cardLayout = new CardLayout();
		mainPanel.setLayout(cardLayout);
		
		List<PluginDescriptor> plugInsDescription = Loader.getInstance().getDescriptionFor("DataLoad");
		JPanel firstPanel =  this.createFirstPanel(plugInsDescription);
		
		List<List<PluginDescriptor>> plugInsType = Loader.getInstance().getTypeFor("Plugin");
		JPanel secondPanel = this.createSecondPanel(plugInsType);
		
		mainPanel.add("first",firstPanel);
		mainPanel.add("second",secondPanel);
		cardLayout.show(mainPanel, "first");
        
		frame.setLayout(new BorderLayout());
		frame.add(mainPanel, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
	}

	
	/**
	 * Create the first panel
	 * the choice of dataload to make
	 * @param plugInsDescription
	 * @return panel : JPanel
	 */
	private JPanel createFirstPanel(List<PluginDescriptor> plugInsDescription) {

		JPanel panel = new JPanel();
		JLabel titreLabel = new JLabel("Welcome");
		panel.setLayout(new BorderLayout());
		panel.add(titreLabel,BorderLayout.NORTH);
		
		JPanel panelBis = new JPanel();
		
		JTextArea poorDataRepresentation = new JTextArea("Select a method of loading data to start Game :", 10, 60);

		for (PluginDescriptor descr : plugInsDescription) {
			poorDataRepresentation.setText(poorDataRepresentation.getText()+"\n"+descr.name+" : "+descr.description);
			JButton jb = new JButton(descr.name);
			
			jb.addActionListener(e -> {
				this.setItemsAndPerson((ILoadData)Loader.getInstance().getPlugin(descr));
				cardLayout.show(mainPanel, "second");
				});
			panel.add(poorDataRepresentation,BorderLayout.CENTER);
			panelBis.add(jb);
		}
		panel.add(panelBis, BorderLayout.SOUTH);
		return panel;
	}
	
	/**
	 * init the items list
	 * init the person list
	 * @param plugin
	 */
	private void setItemsAndPerson(ILoadData plugin) {
		run.setItems(plugin.initializeItems());
		run.setPeople(plugin.initializeCharacters(run.getItems()));
	}
	
	/**
	 * Create the main panel
	 * container of the game panel and the plugin panel
	 * @param pluginType
	 * @return panel : JPanel
	 */
	private JPanel createSecondPanel(List<List<PluginDescriptor>> pluginType) {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
	
		JPanel plugInPanel = menu.createPlugInPanel(pluginType);
		JPanel gamePanel = menu.createGamePanel();
		
		panel.add(gamePanel, BorderLayout.CENTER);
		panel.add(plugInPanel, BorderLayout.EAST);
		
		return panel;
	}
}