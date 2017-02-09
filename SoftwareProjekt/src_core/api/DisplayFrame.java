package api;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * Frame that can hold a {@link Game} and
 * offers some predefined behavior.
 * 
 * @author PurifyPioneer
 * @version 1.0
 * @since 1.0
 */
public class DisplayFrame {

	// Default properties of the DisplayFrame
	private final String title = "Display Frame";
	private final int width = 500;
	private final int height = 350;
	
	// Not extending JFrame because encapsulation
	private JFrame frame = null;

	/**
	 * Constructs a new {@link DisplayFrame} with
	 * default values for width, height and title.
	 */
	public DisplayFrame() {
		
		// Create new JFrame and set default properties
		frame = new JFrame();
		frame.setTitle(title);
		frame.setSize(width, height);
		frame.getContentPane().setLayout(new BorderLayout());
		
		//--Just some fancy stuff--
		JLabel lblText = new JLabel("Add a game for more fun.");
		lblText.setHorizontalAlignment(JLabel.CENTER);
		frame.setMinimumSize(new Dimension(250, 100));
		frame.getContentPane().add(lblText, BorderLayout.CENTER);
		//-------------------------
		
		// Change window closing behaviour
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		WindowListener winListener = new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int answer = JOptionPane.showConfirmDialog(frame,
						"Wollen sie das Programm wirklich beenden ?",
						"Wirklich beenden ?", JOptionPane.YES_NO_OPTION,
						JOptionPane.INFORMATION_MESSAGE);
				if (answer == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		};
		frame.addWindowListener(winListener);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	

	
	public DisplayFrame(int width, int height){		
		this();
		frame.setSize(width, height);
	}
	
	/**
	 * 
	 * @param width
	 * @param height
	 * @param title
	 */
	public DisplayFrame(int width, int height, String title) {		
		this(width, height);
		frame.setTitle(title);
	}	
	
	public void addGame(Game game) {
		frame.setTitle(game.getTitle());
		frame.getContentPane().removeAll(); // Make sure that there are now components left
		frame.getContentPane().add(game, BorderLayout.CENTER);
		frame.pack();
		frame.setLocationRelativeTo(null);
	}
}
