package entity;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class EntityManager {

	private int frameWidth;
	private int frameHeight;

	private int entityCountX;
	private int entityCountY;

	private int entityWidth;
	private int entityHeight;

	private int seletedEntities;
	
	private ArrayList<Entity> entities = new ArrayList<Entity>();

	public EntityManager(int frameWidth, int frameHeight, int entityCountX, int entityCountY) {
		setFrameWidth(frameWidth);
		setFrameHeight(frameHeight);
		setEntityCountX(entityCountX);
		setEntityCountY(entityCountY);

		calculateEntitySize();

		createEntitys();
	}

	public Entity getEntityAt(int x, int y) {

		if ((!(x >= 0 && x <= entityCountX))
				|| (!(y >= 0 && y <= entityCountY))) {
			return null;
		}

		Entity e = null;

		for (int i = 0; i < entities.size(); i++) {
			e = entities.get(i);
			if (e.getxPosition() == x && e.getyPosition() == y) {
				return e;
			}
		}
		return null;
	}

	public void setentityAliveAt(int x, int y) {
		getEntityAt(x, y).setAlive(true);
		;
	}

	public void reset() {
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).setAlive(false);
		}
	}

	public void update() {
		
		Entity e;
		
		if (seletedEntities >= 1) {
			for (int i = 0; i < entities.size(); i++) {
				e = entities.get(i);
				
				if (e.isMarked()) {
					if (e.isAliveNextRound()) {
						e.setAlive(true);
					} else {
						e.setAlive(false);
					}
					e.setMarked(false);
				}
			}
			
		}
		
		for (int i = 0; i < entities.size(); i++) {
			e = entities.get(i);
		    e.update();
		}
		
		for (int i = 0; i < entities.size(); i++) {
			e = entities.get(i);
			if (e.isAliveNextRound()) {
				e.setAlive(true);
			} else {
				e.setAlive(false);
			}
		}
		
		//TODO New Logic !
			
	}

	public void updateMarkedEntities() {
		Entity e;
		for (int i = 0; i < entities.size(); i++) {
			e = entities.get(i);
			
			if (e.isMarked()) {	
				if (!e.isAlive()) {
					e.setAliveNextRound(true);
				} else {
					e.setAliveNextRound(false);
				} 
			}
		}
	}
	
	public void drawEntitys(Graphics g) {
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).draw(g, getEntityWidth(), getEntityHeight());
		}
	}

	private void createEntitys() {
		for (int x = 0; x < getEntityCountX(); x++) {
			for (int y = 0; y < getEntityCountY(); y++) {
				entities.add(new Entity(x, y, false, this));
			}
		}
	}
	
	public void selectEntity(Point p) {
		
		int x = p.x / getEntityWidth();
		int y = (p.y / getEntityHeight());
		
		if (!getEntityAt(x, y).isMarked()) {
			getEntityAt(x, y).setMarked(true);
			seletedEntities++;
		} else {
			getEntityAt(x, y).setMarked(false);
			seletedEntities--;
		}
	}
	
	private void calculateEntitySize() {
		setEntityWidth(getFrameWidth() / getEntityCountX());
		setEntityHeight(getFrameHeight() / getEntityCountY());
	}
	
	public void resize(int width, int height) {
		setFrameWidth(width);
		setFrameHeight(height);
		calculateEntitySize();
	}
	
	// ---> Getters and Setters <---
	
	public int getFrameWidth() {
		return frameWidth;
	}

	private void setFrameWidth(int frameWidth) {
		this.frameWidth = frameWidth;
	}

	public int getFrameHeight() {
		return frameHeight;
	}

	private void setFrameHeight(int frameHeight) {
		this.frameHeight = frameHeight;
	}

	public int getEntityCountX() {
		return entityCountX;
	}

	private void setEntityCountX(int entityCountX) {
		this.entityCountX = entityCountX;
	}

	public int getEntityCountY() {
		return entityCountY;
	}

	private void setEntityCountY(int entityCountY) {
		this.entityCountY = entityCountY;
	}

	public int getEntityWidth() {
		return entityWidth;
	}

	private void setEntityWidth(int entityWidth) {
		this.entityWidth = entityWidth;
	}

	public int getEntityHeight() {
		return entityHeight;
	}

	private void setEntityHeight(int entityHeight) {
		this.entityHeight = entityHeight;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}
}
