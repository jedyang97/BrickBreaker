package com.jed.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.jed.util.ValueStack;

public class Brick extends Rectangle {
	private ValueStack vs;

	public Color color = Color.RED;
	
	public Brick(ValueStack vs) {
		this.vs = vs;
		this.width = 50;
		this.height = 50;
	}
	
	public Brick(ValueStack vs, int x, int y) {
		this(vs);
		this.x = x;
		this.y = y;
	}

	public void init() {

		int r = (int) (Math.random() * 256);
		int g = (int) (Math.random() * 256);
		int b = (int) (Math.random() * 256);

		this.setColor(Color.red);
		this.x = ((int) (Math.random() * (vs.getGamePanel().getWidth() - this.width)));
		this.y = ((int) (Math.random() * (vs.getGamePanel().getHeight() - this.height)));

	}

	
	public void paintBrick(Graphics g) {
		g.setColor(this.getColor());
		g.fillRoundRect(this.x, this.y, this.width, this.height, 20, 20);
		
	}
	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

}
