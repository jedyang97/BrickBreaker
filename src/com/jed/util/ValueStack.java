package com.jed.util;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;

import com.jed.entity.*;
import com.jed.gui.ControlPanel;
import com.jed.gui.EditPanel;
import com.jed.gui.GamePanel;

public class ValueStack {

	private Ball ball;
	private Bar bar;
	private JFrame frame;
	private GamePanel gamePanel;
	private ControlPanel controlPanel;
	private EditPanel editPanel;
	private Thread gameThread;
	private ArrayList<Brick> bricks;
	//private HashMap<Point, Brick> blockMap = new HashMap<Point, Brick>();

	public Ball getBall() {
		return ball;
	}

	public void setBall(Ball ball) {
		this.ball = ball;
	}

	public Bar getBar() {
		return bar;
	}

	public void setBar(Bar bar) {
		this.bar = bar;
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public GamePanel getGamePanel() {
		return gamePanel;
	}

	public void setGamePanel(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	public ControlPanel getControlPanel() {
		return controlPanel;
	}

	public void setControlPanel(ControlPanel controlPanel) {
		this.controlPanel = controlPanel;
	}

	public Thread getGameThread() {
		return gameThread;
	}

	public void setGameThread(Thread gameThread) {
		this.gameThread = gameThread;
	}

	public ArrayList<Brick> getBricks() {
		return bricks;
	}

	public void setBricks(ArrayList<Brick> bricks) {
		this.bricks = bricks;
	}

	public EditPanel getEditPanel() {
		return editPanel;
	}

	public void setEditPanel(EditPanel editPanel) {
		this.editPanel = editPanel;
	}

	/*
	public HashMap<Point, Brick> getBlockMap() {
		return blockMap;
	}

	public void setBlockMap(HashMap<Point, Brick> blockMap) {
		this.blockMap = blockMap;
	}
	*/

}
