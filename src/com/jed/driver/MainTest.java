package com.jed.driver;



import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import com.jed.gui.MainGUI;
import com.jed.util.ValueStack;


public class MainTest {

	public static void main(String[] args) {
		ValueStack vs = new ValueStack();
		MainGUI gui = new MainGUI(vs);
		gui.go();

		// keyTest();

	}

	public static void keyTest() {
		JFrame f = new JFrame();
		f.addKeyListener(new MainTest().new Listener());
		f.setSize(500, 500);
		f.setVisible(true);
	}

	class Listener extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			System.out.println(e.getKeyCode());
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			super.keyReleased(arg0);
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			super.keyTyped(arg0);
		}

	}
}
