package com.jed.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.jed.gui.EditPanel.MyMouseListener;
import com.jed.gui.EditPanel.MyMouseMotionListener;
import com.jed.util.ValueStack;

public class ControlPanel extends JPanel {

	private ValueStack vs;
	private JRadioButton kb;
	private JRadioButton mb;
	private ButtonGroup modeButtonGroup;
	private JButton play;
	private JButton edit;
	private BufferedImage backgroundImage;
	public JLabel label;

	public BufferedImage getBackgroundImage() {
		return backgroundImage;
	}

	public void setBackgroundImage(BufferedImage backgroundImage) {
		this.backgroundImage = backgroundImage;
	}

	public ControlPanel(ValueStack vs) {
		this.vs = vs;

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		kb = new JRadioButton("KeyBoard Mode", true);
		mb = new JRadioButton("Mouse Mode", false);
		kb.setContentAreaFilled(false);
		mb.setContentAreaFilled(false);
		modeButtonGroup = new ButtonGroup();
		label = new JLabel();
		modeButtonGroup.add(kb);
		modeButtonGroup.add(mb);
		play = new JButton("Start");
		play.addActionListener(new PActionListener());
		edit = new JButton("Edit");
		edit.addActionListener(new EActionListener());
		this.addKeyListener(new PActionListener());

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setAlignmentX(CENTER_ALIGNMENT);
		label.setAlignmentX(CENTER_ALIGNMENT);

		panel.add(kb);
		panel.add(mb);
		panel.add(Box.createRigidArea(new Dimension(10, 50)));
		panel.add(play);
		panel.add(edit);

		panel.setBackground(Color.RED);

		this.add(Box.createVerticalGlue());
		label.setText("Click \"Start\" to begin.");
		label.setFont(new Font("Arial", Font.BOLD, 50));
		label.setForeground(Color.RED);
		this.add(label);
		this.add(panel);
		this.add(Box.createVerticalGlue());

	}

	class EActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			vs.getEditPanel().addMouseMotionListener(vs.getEditPanel().new MyMouseMotionListener());
			vs.getEditPanel().addMouseListener(vs.getEditPanel().new MyMouseListener());
			vs.getEditPanel().addKeyListener(vs.getEditPanel().new MyKeyAdapter());
			
			vs.getFrame().remove(vs.getControlPanel());
			vs.getFrame().add(vs.getEditPanel());
			vs.getEditPanel().requestFocusInWindow();
			vs.getFrame().validate();
		}
	}
	
	class PActionListener implements ActionListener, KeyListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {

			if (modeButtonGroup.getSelection() == mb.getModel()) {
				if (vs.getGamePanel().getMouseMotionListeners().length == 0) {
					vs.getGamePanel().addMouseMotionListener(
							vs.getGamePanel().new MyMouseMotionListener());
				}
			} else if (modeButtonGroup.getSelection() == kb.getModel()) {
				if (vs.getGamePanel().getKeyListeners().length == 0) {
					vs.getGamePanel().addKeyListener(
							vs.getGamePanel().new MyKeyAdapter());
				}
			}
			if (play.getText().equals("Start")) {
				play.setText("Resume");
			}
			vs.getFrame().remove(vs.getControlPanel());
			vs.getFrame().add(vs.getGamePanel());
			vs.getGamePanel().requestFocusInWindow();
			vs.getFrame().validate();
			if (vs.getGameThread() != null) {
				synchronized (vs.getGamePanel()) {
					vs.getGamePanel().setPause(false);
					vs.getGamePanel().notify();
				}
			} else {
				Thread gameThread = new Thread() {
					public void run() {
						vs.getGamePanel().play();
					}
				};
				gameThread.start();
				vs.setGameThread(gameThread);
			}

		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == 27) {
				this.actionPerformed(null);
			}
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub

		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, null);
	}

	public JButton getPlay() {
		return play;
	}

	public void setPlay(JButton play) {
		this.play = play;
	}

}
