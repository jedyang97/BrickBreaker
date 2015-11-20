package com.jed.driver;

import java.util.ArrayList;

import javax.swing.JFrame;

import com.jed.entity.Brick;
import com.jed.gui.EditPanel;
import com.jed.util.ValueStack;

public class EditPanelTest {
	
	public static void main(String[] args) {
		ValueStack vs = new ValueStack();
		ArrayList<Brick> bricks = new ArrayList<Brick>();
		vs.setBricks(bricks);
		
		JFrame frame = new JFrame();
		
		EditPanel p = new EditPanel(vs);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(p);

		frame.setSize(800, 800);
		frame.setVisible(true);

		p.requestFocusInWindow();

	}
}
