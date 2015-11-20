package com.jed.gui;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.jed.entity.Ball;
import com.jed.entity.Bar;
import com.jed.entity.Brick;
import com.jed.util.ValueStack;
import com.jhlabs.image.GaussianFilter;

public class GamePanel extends JPanel {

	private ValueStack vs;

	private Ball ball;
	private Bar bar;
	private boolean pause = false;
	private int repaintInterval = 1;
	private Color color = Color.BLACK;


	public boolean isPause() {
		return pause;
	}

	public void setPause(boolean pause) {
		this.pause = pause;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public GamePanel(ValueStack vs) {
		this.vs = vs;
		ball = vs.getBall();
		bar = vs.getBar();
	}

	class MyKeyAdapter extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == 37) {
				bar.shiftLeft();
				GamePanel.this.repaint();
			} else if (e.getKeyCode() == 39) {
				bar.shiftRight();
				GamePanel.this.repaint();
			} else if (e.getKeyCode() == 27) {
				long start = System.currentTimeMillis();
				GamePanel.this.pause = true;
				BufferedImage img = new GaussianFilter(40).filter(
						GamePanel.this.capturePanel(), null);

				vs.getFrame().remove(GamePanel.this);
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

	class MyMouseMotionListener extends MouseMotionAdapter {

		@Override
		public void mouseDragged(MouseEvent e) {
			bar.setX(e.getX() - bar.width / 2);
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			bar.setX(e.getX() - bar.width / 2);
		}

	}

	public void play() {
		this.requestFocusInWindow();
		ball.init();
		bar.init();
		/*for(Brick b : vs.getBricks()) {
			b.init();
		}*/
		
		while (true) {
			if (vs.getBricks().isEmpty()) {
				this.pause = true;
				BufferedImage img = new GaussianFilter(40).filter(
						this.capturePanel(), null);

				vs.getFrame().remove(this);
				vs.getFrame().add(vs.getControlPanel());
				vs.getControlPanel().setBackgroundImage(img);
				vs.getControlPanel().label.setText("YOU WIN!");
				vs.getFrame().validate();
				vs.getFrame().repaint();
			}
			synchronized (this) {
				while (pause) {
					try {
						this.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				ball.move();
				vs.getGamePanel().repaint();
				try {
					Thread.sleep(this.repaintInterval);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
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

	@Override
	public void paintComponent(Graphics g) {
		g.setColor(this.getColor());
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		for (Brick b : vs.getBricks()) {
			b.paintBrick(g);
		}
		ball.paintBall(g);
		bar.paintBar(g);
	
		// System.out.println("aa");
	}

}
