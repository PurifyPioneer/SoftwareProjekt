package core;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import api.DisplayFrame;
import game.GameOfLife;

public class SoftwareProjekt {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager
							.getSystemLookAndFeelClassName());
				} catch (Exception e) {
					e.printStackTrace();
				}

				//@SuppressWarnings("unused")
				DisplayFrame gameFrame = new DisplayFrame();
				
				gameFrame.addGame(new GameOfLife(750, 750, "Game Of Life", 20, 20, 50));
			}

		});

	}

}
