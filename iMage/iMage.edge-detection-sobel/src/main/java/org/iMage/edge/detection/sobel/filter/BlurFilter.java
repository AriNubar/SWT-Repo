package org.iMage.edge.detection.sobel.filter;

import java.awt.Color;
import java.awt.image.BufferedImage;

import org.iMage.edge.detection.base.ImageFilter;

/**
 * Implements the blur filter as requested on worksheet 2.
 */
public class BlurFilter implements ImageFilter {

	private int red = 0;
	private int green = 0;
	private int blue = 0;

	private int alpha = 0;

	/**
	 * Default constructor must be available!
	 */
	public BlurFilter() {
		
	}

	
	/*
	 * Method for Blur Filter. 
	 * Each inner pixel is multiplied with weight matrix.
	 * 
	 * @param image Image to be filtered.
	 * @return bufferedImage Image with filter.
	 * 
	 */
	@Override
	public BufferedImage applyFilter(BufferedImage image) {

		BufferedImage bufferedImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());

		float[][] weight = { 
				{ 1 / 9f, 1 / 9f, 1 / 9f }, 
				{ 1 / 9f, 1 / 9f, 1 / 9f }, 
				{ 1 / 9f, 1 / 9f, 1 / 9f } };

		for (int x = 1; x < image.getWidth() - 2; x++) {
			for (int y = 1; y < image.getHeight() - 2; y++) {

				for (int i = 0; i < weight.length; i++) {
					for (int j = 0; j < weight[i].length; j++) {
						calculate(image.getRGB((x - 1) + i, (y - 1) + j), weight[i][j]);
					}
				}

				normalize();

				Color newColor = new Color(red, green, blue, alpha);

				red = 0;
				green = 0;
				blue = 0;

				bufferedImage.setRGB(x, y, newColor.getRGB());
			}
		} return bufferedImage;
	}

	/*
	 * Calculates the red, green, blue values individually.
	 * 
	 * @param rgb RGB value of the pixel
	 * @param weight Weight value of filter
	 * 
	 */
	private void calculate(int rgb, float weight) {
		Color color = new Color(rgb);

		red += color.getRed() * weight;
		green += color.getGreen() * weight;
		blue += color.getBlue() * weight;

		alpha += color.getAlpha() * weight;
	}
	
	/*
	 * Normalizes the values over the maximum and under the minimum value 
	 * by turning into maximum and minimum value in the range.
	 * 
	 */
	private void normalize() {
		if (red < 0) {
		    red = 0;
		}
		if (green < 0) {
		    green = 0;
		}
		if (blue < 0) {
		    blue = 0;
		}
		if (alpha < 0) {
		    alpha = 0;
		}



		if (red > 255) {
		    red = 255;
		}
		if (green > 255) {
		    green = 255;
		}
		if (blue > 255) {
		    blue = 255;
		}
		if (alpha > 255) {
		    alpha = 255;
		}
	}
}
