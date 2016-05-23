package org.iMage.edge.detection.sobel.filter;

import java.awt.image.BufferedImage;
import java.awt.Color;


import org.iMage.edge.detection.base.ImageFilter;

/**
 * Implements the GrayScaleFilter as requested on worksheet 2.
 */
public class GrayScaleFilter implements ImageFilter {

	/** Default constructor must be available! */
	public GrayScaleFilter() {

	}

	/*
	 * Method for Gray Filter. In each case, the average of RGB values of a
	 * pixel are calculated and this value is set in all RGB values.
	 * 
	 * @param image Image to be filtered.
	 * 
	 */

	@Override
	public BufferedImage applyFilter(BufferedImage image) {
		
		BufferedImage bufferedImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());

			for (int i = 0; i < bufferedImage.getHeight(); i++) {
				for (int j = 0; j < bufferedImage.getWidth(); j++) {
					Color a = new Color(image.getRGB(j, i));
					final int red = a.getRed();
					final int green = a.getGreen();
					final int blue = a.getBlue();
					final int alpha = a.getAlpha();
					final int average = ((red + green + blue) / 3);

					Color newColor = new Color(average, average, average, alpha);
					bufferedImage.setRGB(j, i, newColor.getRGB());
				}
			}
		return bufferedImage;
	}
}
