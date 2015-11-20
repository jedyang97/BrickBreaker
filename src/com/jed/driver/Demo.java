package com.jed.driver;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.jhlabs.image.GaussianFilter;

public class Demo {
    public static void main(String[] args) {
        GaussianFilter gf = new GaussianFilter();
        gf.setRadius(200);

        try {
            BufferedImage bi = ImageIO.read(new FileInputStream("d:\\a.jpg"));
            BufferedImage result = gf.filter(bi, null);
            ImageIO.write(result, "jpg", new File("d:/result.jpg"));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Complete!");

    }
}
