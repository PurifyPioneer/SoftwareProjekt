package entity;

import java.awt.Color;
import java.awt.Graphics;

public class Entity {

	private int xPosition;
	private int yPosition;

	private boolean alive;
	private boolean aliveNextRound;

	private boolean marked;

	private EntityManager entityManager;

	public Entity(int xPosition, int yPosition, boolean alive, EntityManager entityManager) {
		setxPosition(xPosition);
		setyPosition(yPosition);
		setAlive(alive);
		setEntityManager(entityManager);
	}

	public void update() {

		int startX = getxPosition() - 1;
		int startY = getyPosition() - 1;
		int endX = startX + 3;
		int endY = startY + 3;

		Entity t = null;

		int aliveAround = 0;

		for (int x = startX; x < endX; x++) {
			for (int y = startY; y < endY; y++) {
				t = getEntityManager().getEntityAt(x, y);
				if (t != null
						&& !(x == this.getxPosition() && y == this
								.getyPosition())) {
					if (t.isAlive()) {
						aliveAround++;
					}
				}
			}
		}

		if (aliveAround == 3) {
			this.setAliveNextRound(true);
		}

		if (aliveAround < 2) {
			this.setAliveNextRound(false);
		}

		if (this.isAlive() && (aliveAround == 2 || aliveAround == 3)) {
			this.setAliveNextRound(true);
		}

		if (aliveAround > 3) {
			this.setAliveNextRound(false);
		}
	
	}

	public void draw(Graphics g, int tileWidth, int tileHeight) {
		if (isAlive()) {
			g.setColor(Color.GREEN);
		} else {
			g.setColor(Color.RED);
		}
		g.fillRect(getxPosition() * tileWidth, getyPosition() * tileHeight,
				tileWidth, tileHeight);
		g.setColor(Color.BLACK);
		g.drawRect(getxPosition() * tileWidth, getyPosition() * tileHeight,
				tileWidth, tileHeight);
		if (this.isMarked()) {
			g.setColor(Color.MAGENTA);
			g.drawRect(getxPosition() * tileWidth, getyPosition() * tileHeight,
					tileWidth - 1, tileHeight - 1);
		}
	}

	// ---> Getters and Setters <---
	
	public int getxPosition() {
		return this.xPosition;
	}

	private void setxPosition(int xPosition) {
		this.xPosition = xPosition;
	}

	public int getyPosition() {
		return this.yPosition;
	}

	private void setyPosition(int yPosition) {
		this.yPosition = yPosition;
	}

	public boolean isAlive() {
		return this.alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public EntityManager getEntityManager() {
		return this.entityManager;
	}

	private void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public boolean isAliveNextRound() {
		return this.aliveNextRound;
	}

	public void setAliveNextRound(boolean aliveNextRound) {
		this.aliveNextRound = aliveNextRound;
	}

	public boolean isMarked() {
		return marked;
	}

	public void setMarked(boolean marked) {
		this.marked = marked;
	}

}
