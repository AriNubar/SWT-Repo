package org.iMage.edge.detection.sobel.filter;

import java.awt.Color;
import java.awt.image.BufferedImage;

import org.iMage.edge.detection.base.ImageFilter;

/**
 * Filters all pixels that have a grayscale color below a certain threshold and
 * sets them to 0 (makes them black). Pixels above the threshold are converted
 * to grayscale normally (as defined in {@link GrayScaleFilter}).
 */
public class LowerThresholdFilter implements ImageFilter {

	/** Default constructor must be available! */
	public LowerThresholdFilter() {
	}

	/*
	 * Method for Threshold Filter. 
	 * The pixels, which RGB values are lower than the threshold value are set to black. 
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

				if (average < 127) {
					Color newColor = new Color(0, 0, 0, alpha);
					bufferedImage.setRGB(j, i, newColor.getRGB());
				} else {
					Color newColor = new Color(average, average, average, alpha);
					bufferedImage.setRGB(j, i, newColor.getRGB());
				}
			}	
		}
		return bufferedImage;
	}
}
