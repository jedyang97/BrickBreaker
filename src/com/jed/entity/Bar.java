package com.jed.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.jed.util.ValueStack;

public class Bar extends Rectangle{

	private ValueStack vs;

	
	public static final Color BAR_COLOR = Color.CYAN;
	public static final int BAR_SPEED = 60;

	public Bar(ValueStack vs) {
		this.vs = vs;
		this.width = 150;
		this.height = 20;
	}
	
	public void init() {
		
		this.setX((vs.getGamePanel().getWidth() - this.width) / 2);
	}

	

	public void setWidth(int width) {
		this.width = width;
	}



	public void setHeight(int height) {
		this.height = height;
	}


	public void setX(int x) {
		this.x = x;
	}

	public void shiftLeft() {
		setX(x - BAR_SPEED);
	}

	public void shiftRight() {
		setX(x + BAR_SPEED);
	}
	
	public void paintBar(Graphics g) {
		this.y = vs.getGamePanel().getHeight() - 10 - this.height;
		if (this.x < 0) {
			this.setX(0);
		} else if (this.getX() > vs.getGamePanel().getWidth() - this.getWidth()) {
			this.setX(vs.getGamePanel().getWidth() - this.width);
		}
		g.setColor(this.BAR_COLOR);
		g.fillRoundRect(this.x, this.y,
				this.width, this.height, 20, 20);
		
	}
}
