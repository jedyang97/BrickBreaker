package com.jed.gui;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.jed.entity.Brick;
import com.jed.util.ValueStack;
import com.jhlabs.image.GaussianFilter;

public class EditPanel extends JPanel {

	public Brick brick;

	private ValueStack vs;

	public EditPanel(ValueStack vs) {
		this.vs = vs;
		brick = new Brick(vs);
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		brick.paintBrick(g);
		for (Brick b : vs.getBricks()) {
			b.paintBrick(g);
		}
	}
	
	public BufferedImage capturePanel() {
		try {
			Robot robot = new Robot();
			BufferedImage result = robot.createScreenCapture(new Rectangle(
					(int) this.getLocationOnScreen().getX(), (int) this
							.getLocationOnScreen().getY(), this.getWidth(),
					this.getHeight()));
			return result;
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	class MyMouseMotionListener extends MouseMotionAdapter {

		@Override
		public void mouseDragged(MouseEvent e) {
			vs.getBricks().add(
					new Brick(EditPanel.this.vs, e.getX() - brick.width / 2, e
							.getY() - brick.height / 2));
			EditPanel.this.repaint();
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			brick.setX(e.getX() - brick.width / 2);
			brick.setY(e.getY() - brick.height / 2);
			EditPanel.this.repaint();
		}

	}

	class MyMouseListener extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			vs.getBricks().add(
					new Brick(EditPanel.this.vs, e.getX() - brick.width / 2, e
							.getY() - brick.height / 2));
			EditPanel.this.repaint();
		}

	}
	
	class MyKeyAdapter extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == 27) {
				long start = System.currentTimeMillis();
				BufferedImage img = new GaussianFilter(40).filter(
						EditPanel.this.capturePanel(), null);

				vs.getFrame().remove(EditPanel.this);
				vs.getFrame().add(vs.getControlPanel());
				vs.getControlPanel().setBackgroundImage(img);
				vs.getControlPanel().label.setText("Click \"Resume\" to continue.");
				vs.getFrame().validate();
				vs.getFrame().repaint();
				vs.getControlPanel().requestFocusInWindow();
				System.out.println("USE: "
						+ (System.currentTimeMillis() - start));

			}

		}

		@Override
		public void keyReleased(KeyEvent e) {

		}

		@Override
		public void keyTyped(KeyEvent e) {

		}

	}
}
