package com.jed.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Iterator;

import com.jed.util.ValueStack;

public class Ball extends Rectangle {

	private ValueStack vs;

	public int diameter = 30;
	public int xSpeed;
	public int ySpeed;

	public static final Color BALL_COLOR = Color.WHITE;

	public Ball(ValueStack vs) {
		this.vs = vs;
		this.width = this.diameter;
		this.height = this.diameter;
		setxSpeed(1);
		setySpeed(1);
	}

	public void init() {
		
		this.x = ((int) (Math.random() * (this.vs.getFrame().getWidth() - diameter)));
		this.y = ((int) (Math.random() * (this.vs.getFrame().getHeight() - diameter)));
		
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getDiameter() {
		return diameter;
	}

	public void setDiameter(int diameter) {
		this.diameter = diameter;
	}

	public int getxSpeed() {
		return xSpeed;
	}

	public void setxSpeed(int xSpeed) {
		this.xSpeed = xSpeed;
	}

	public int getySpeed() {
		return ySpeed;
	}

	public void setySpeed(int ySpeed) {
		this.ySpeed = ySpeed;
	}

	public void paintBall(Graphics g) {
		g.setColor(BALL_COLOR);
		g.fillOval(this.x, this.y, this.diameter, this.diameter);
	}

	public void move() {
		int width = vs.getGamePanel().getWidth();
		int height = vs.getGamePanel().getHeight();
		
		// check for walls
		if (this.x + this.getxSpeed() > width - diameter) {
			this.setX(2 * (width - diameter) - (this.x + this.getxSpeed()));
			this.setxSpeed(-Math.abs(this.getxSpeed()));
			while (this.x > width - diameter) {
				this.setX(width - diameter);
			}
			while (this.getX() < 0) {
				this.setX(0);
			}
		} else if (this.getX() + this.getxSpeed() < 0) {
			this.setX(-(this.x + this.getxSpeed()));
			this.setxSpeed(Math.abs(this.getxSpeed()));
			while (this.x < 0) {
				this.setX(0);
			}
			while (this.x > width - diameter) {
				this.setX(width - diameter);
			}
		} else {
			this.setX(this.x + this.getxSpeed());
		}
		// check for walls
		if (this.y + this.getySpeed() > height - diameter) {
			this.setY(2 * (height - diameter) - (this.y + this.getySpeed()));
			this.setySpeed(-Math.abs(this.getxSpeed()));
			while (this.y > height - diameter) {
				this.setY(height - diameter);
			}
			while (this.y < 0) {
				this.setY(0);
			}
		} else if (this.y + this.getySpeed() < 0) {
			this.setY(-(this.y + this.getySpeed()));
			this.setySpeed(Math.abs(this.getxSpeed()));
			while (this.y < 0) {
				this.setY(0);
			}
			while (this.y > height - diameter) {
				this.setY(height - diameter);
			}
		} else {
			this.setY(this.y + this.getySpeed());
		}
		// check for bar
		if (this.intersects(vs.getBar())) {
			Rectangle r = this.intersection(vs.getBar());
			this.y = this.y - 2 * r.height;
			this.setySpeed(-Math.abs(this.getxSpeed()));
		}
		// check for bricks
		Iterator<Brick> it = vs.getBricks().iterator();
		while (it.hasNext()) {
			Brick b = (Brick) it.next();
			if (this.intersects(b)) {
				it.remove();
				Rectangle r = this.intersection(b);
				if (r.width > r.height) {
					this.y = this.y < b.y ? (this.y - 2 * r.height)
							: (this.y + 2 * r.height);
					this.setySpeed(-this.getySpeed());
				} else if (r.width < r.height) {
					this.x = this.x < b.x ? (this.x - 2 * r.width)
							: (this.x + 2 * r.width);
					this.setxSpeed(-this.getxSpeed());
				} else if (r.width == r.height) {
					this.y = this.y < b.y ? (this.y - 2 * r.height)
							: (this.y + 2 * r.height);
					this.x = this.x < b.x ? (this.x - 2 * r.width)
							: (this.x + 2 * r.width);
					this.setySpeed(-this.getySpeed());
					this.setxSpeed(-this.getxSpeed());
				}

			}
		}

	}
}
