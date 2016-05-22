package org.iMage.edge.detection.sobel.filter;

import java.awt.image.BufferedImage;
import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.*;

import javax.imageio.ImageIO;

import org.iMage.edge.detection.base.ImageFilter;

/**
 * Implements the GrayScaleFilter as requested on worksheet 2.
 */
public class GrayScaleFilter implements ImageFilter {
	
	 BufferedImage  image;
	   int width;
	   int height;

	/** Default constructor must be available! */
	public GrayScaleFilter() {

	}

	
	/*
	 * Method for Gray Filter. 
	 * In each case, the average of RGB values of a pixel are calculated and this value is set in all RGB values. 
	 * @param image Image to be filtered.
	 * 
	 */

	
	@Override
	public BufferedImage applyFilter(BufferedImage image) {
		  
		  try {
		         File input = new File("a.png");
		         image = ImageIO.read(input);
		         width = image.getWidth();
		         height = image.getHeight();
		       
		         for(int i=0; i < height; i++){
		            for(int j=0; j < width; j++){
		            	Color a = new Color(image.getRGB(j,i));
		            	int red = (int) (a.getRed());
		            	int green = (int)(a.getGreen());
			            int blue = (int)(a.getBlue());
			            int alpha = (int) (a.getAlpha());
			            int average = ((red + green + blue) / 3);
			            
			            Color newColor = new Color(average, average, average, alpha);
			            image.setRGB(j,i, newColor.getRGB());
		            }
		         } 
		         File output = new File("grayscale.png");
		         ImageIO.write(image, "png", output);       
	      } catch (Exception e) {}
	   }
		return null;
	}
}
