package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import api.Game;
import entity.EntityManager;

public class GameOfLife extends Game {

	private int frameWidth;
	private int frameHeight;
	
	private boolean paused = false;
	
	private int fpsLimit = 60;
	private long lastRepaintTime;
	
	private long lastUpdate;
	private long timeSinceLastUpdate;
	private int updateTime;
	
	private EntityManager entityManager = null;
	private int entityCountX;
	private int entityCountY;
	
	private boolean infoActive = false;
	
	
	public GameOfLife(int width, int height, String title, int tileCountX, int tileCountY, int updateTime) {
		setPreferredSize(new Dimension(width, height));
		
		setFrameWidth(width);
		setFrameHeight(height);
		setTitle(title);
		
		setTileCountX(tileCountX);
		setTileCountY(tileCountY);
		
		setUpdateTime(updateTime);
		
		initObjects();
		
		startGame();
	}
	
	private void initObjects() {
		setEntityManager(new EntityManager(getFrameWidth(), getFrameHeight(),
				getTileCountX(), getTileCountX()));
	}

	private void updateObjects() {
		getEntityManager().update();
	}

	private void drawObjects(Graphics g) {
		getEntityManager().drawEntitys(g);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawObjects(g);
		
		//TODO
		if (infoActive) {
			g.setFont(g.getFont().deriveFont(Font.BOLD,
					15));
			g.setColor(Color.GREEN);
			g.fillRect(5, 5, 175, 50);
			g.setColor(Color.BLACK);
			g.drawString(getFPS() + " FPS", 10, 20);
			g.drawString("Update Time: " + getUpdateTime() + "(ms)", 10, 35);
			g.drawString("Running: " + !isPaused(), 10, 50);
		}
		
	}

	@Override
	public void run() {
		
		setPaused(true);
		long currentTime;

		while (true) {

			currentTime = System.currentTimeMillis();
			
			// TODO Limit power usage ?
			if (currentTime - lastRepaintTime >= (1/60)) {
				repaint();
				updateFPS(currentTime);
			}

			timeSinceLastUpdate = currentTime - lastUpdate;
			
			if (!isPaused()) {
				if (timeSinceLastUpdate >= updateTime) {
					updateObjects();
					lastUpdate = System.currentTimeMillis();
				}
			} else {
					getEntityManager().updateMarkedEntities();
			}
		}
	}

	// ---> Getters and Setters <--- 
	
	private boolean isPaused() {
		return paused;
	}

	private void setPaused(boolean paused) {
		this.paused = paused;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	private void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	private void setFrameWidth(int frameWidth) {
		this.frameWidth = frameWidth;
	}

	public int getFrameWidth() {
		return frameWidth;
	}

	private void setFrameHeight(int frameHeight) {
		this.frameHeight = frameHeight;
	}

	public int getFrameHeight() {
		return frameHeight;
	}

	public int getTileCountX() {
		return entityCountX;
	}

	private void setTileCountX(int tileCountX) {
		this.entityCountX = tileCountX;
	}

	public int getTileCountY() {
		return entityCountY;
	}

	private void setTileCountY(int tileCountY) {
		this.entityCountY = tileCountY;
	}

	public int getUpdateTime() {
		return updateTime;
	}

	private void setUpdateTime(int updateTime) {
		this.updateTime = updateTime;
	}
	
	// ---> Listeners <---
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			setPaused(!isPaused());
		}
		if (e.getKeyCode() == KeyEvent.VK_DELETE) {
			getEntityManager().reset();
		}
		if (e.getKeyCode() == KeyEvent.VK_F1) {
			//TODO
			infoActive = !infoActive;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (isPaused()) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					getEntityManager().selectEntity(e.getPoint());
				}
			
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
//		if (isPaused()) {
//			if (e.getButton() == MouseEvent.BUTTON1) {
//				getEntityManager().selectEntity(e.getPoint());
//				//Drag and Drop 
//			}
//		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void componentHidden(ComponentEvent e) {
	}

	@Override
	public void componentMoved(ComponentEvent e) {
	}

	@Override
	public void componentResized(ComponentEvent e) {
		getEntityManager().resize(getWidth(), getHeight());
	}

	@Override
	public void componentShown(ComponentEvent e) {
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if (infoActive) {
			int i = e.getWheelRotation();
			updateTime += (i*-10);
		}
	}

}
