package com.jed.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.jed.entity.Brick;

public class BrickLoader {
	
	public ArrayList<Brick> loadBricks() {
		ArrayList<Brick> result = new ArrayList<Brick>();
		
		BufferedImage bi;
		try {
			bi = ImageIO.read(new File("d:/a.png"));
			int width = bi.getWidth();
			int height = bi.getHeight();
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					int dip = bi.getRGB(j, i);
					if (dip != -1) {
						System.out.print("*");
					} else {
						System.out.print(" ");
					}
				}
				System.out.println();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		new BrickLoader().loadBricks();
	}
}
