package api;

import java.awt.event.ComponentListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class Game extends JPanel implements Runnable, KeyListener, MouseListener, MouseWheelListener, ComponentListener {

	private Thread thread;
	private String title;

	private long lastTime;
	private int fps;
	private int fpsCounter;
	
	/**
	 * Creates a new Game and takes care
	 * that listeners are available.
	 */
	protected Game() {
		this.setFocusable(true);
		this.addKeyListener(this);
		this.addMouseWheelListener(this);
		this.addMouseListener(this);
		this.addComponentListener(this);
	}
	
	public String getTitle() {
		return title;
	}
	

	protected void setTitle(String title) {
		this.title = title;
	}


	public Thread getThread() {
		return thread;
	}


	protected void setThread(Thread thread) {
		this.thread = thread;
	}
	
	/**
	 * TODO Find away to calculate fps in general purposes
	 * Will calculate how much fps the game is running at.
	 * (How often the frame is repainted).
	 * @param currentTime
	 */
	protected void updateFPS(long currentTime) {
		
		if (currentTime - lastTime>= 1000) {
			fps = fpsCounter;
			fpsCounter = 0;
			lastTime = currentTime;
		}
		fpsCounter++;
	}
	
	protected long getFPS() {
		return fps;
	}
	
	protected void startGame() {
		setThread(new Thread(this, getTitle()));
		getThread().start();
	}
}
