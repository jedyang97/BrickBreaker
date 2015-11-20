package com.jed.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import com.jed.entity.Ball;
import com.jed.entity.Bar;
import com.jed.entity.Brick;
import com.jed.util.ValueStack;

public class MainGUI {

	private ValueStack vs;

	private JFrame frame;
	private GamePanel gamePanel;
	private ControlPanel controlPanel;
	private EditPanel editPanel;
	private Ball ball;
	private Bar bar;
	private ArrayList<Brick> bricks;

	public MainGUI(ValueStack vs) {
		this.vs = vs;
	}

	public void go() {
		frame = new JFrame("Game");
		vs.setFrame(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 500);

		ball = new Ball(vs);
		vs.setBall(ball);
		bar = new Bar(vs);
		vs.setBar(bar);
		gamePanel = new GamePanel(vs);
		vs.setGamePanel(gamePanel);
		controlPanel = new ControlPanel(vs);
		vs.setControlPanel(controlPanel);
		ArrayList<Brick> bricks = new ArrayList<Brick>();
		vs.setBricks(bricks);
		editPanel = new EditPanel(vs);
		vs.setEditPanel(editPanel);
		
		
		
		frame.add(controlPanel);

		// frame.add(gamePanel);

		frame.setVisible(true);
		
		controlPanel.getPlay().requestFocusInWindow();

		// gamePanel.play();
		
	}

}
