package loader;

import game.Game;

/**
 * Laucher
 * @author mschneider, amurrayvidal
 */
public class Launcher extends Thread{
	public void run() {
	    // appel des autorun
	    @SuppressWarnings("unused")
		Game game = new Game();
	}

	public static void main(String[] args) {
		Launcher launcher = new Launcher();
		launcher.start(); // Start the thread
	 }
}
