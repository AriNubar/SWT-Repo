package org.iMage.edge.detection.sobel.filter;

import java.awt.Color;
import java.awt.image.BufferedImage;

import org.iMage.edge.detection.base.ImageFilter;

/**
 * Detects edges via the Prewitt filter operator.
 */
public class PrewittFilter implements ImageFilter {

    private int red = 0;
    private int green = 0;
    private int blue = 0;
    private int alpha = 0;

	/** Default constructor must be available! */
	public PrewittFilter() {
		
	}

	/*
	 * Method for Prewitt Filter. 
	 * The vertical and horizontal edges of the picture are found then applied into a new picture.
	 * 
	 * @param image Image to be filtered.
	 * 
	 */
	@Override
	public BufferedImage applyFilter(BufferedImage image) {

		//Picture to be with horizontal and vertical edges.
        BufferedImage bufferedImage = new BufferedImage(
                image.getWidth(), image.getHeight(), image.getType()
        );
        
        //Picture to be with horizontal edges.
        BufferedImage bufferedImageX = new BufferedImage(
                image.getWidth(), image.getHeight(), image.getType()
        );
        
        //Picture to be with vertical edges.
        BufferedImage bufferedImageY = new BufferedImage(
                image.getWidth(), image.getHeight(), image.getType()
        );



        // Weight for finding horizontal edges.
        float[][] prewittWeightX = {
                {-1, 0, 1},
                {-1, 0, 1},
                {-1, 0, 1}
        };

        for (int x = 1; x < image.getWidth() - 2; x++) {
            for (int y = 1; y < image.getHeight() - 2; y++) {

                for (int i = 0; i < prewittWeightX.length; i++) {
                    for (int j = 0; j < prewittWeightX[i].length; j++) {
                        calculate(image.getRGB((x - 1) + i, (y - 1) + j), prewittWeightX[i][j]);
                    }
                }

                normalize();

                Color newColor = new Color(red, green, blue, alpha);

                red = 0;
                green = 0;
                blue = 0;
                alpha = 0;

                bufferedImageX.setRGB(x, y, newColor.getRGB());
            }
        }


        // Weight for finding vertical edges.
        float[][] prewittWeightY = {
                {-1, -1, -1},
                {0, 0, 0},
                {1, 1, 1}
        };

        for (int x = 1; x < image.getWidth() - 2; x++) {
            for (int y = 1; y < image.getHeight() - 2; y++) {

                for (int i = 0; i < prewittWeightY.length; i++) {
                    for (int j = 0; j < prewittWeightY[i].length; j++) {
                        calculate(image.getRGB((x - 1) + i, (y - 1) + j), prewittWeightY[i][j]);
                    }
                }

                normalize();

                Color newColor = new Color(red, green, blue, alpha);

                red = 0;
                green = 0;
                blue = 0;
                alpha = 0;

                bufferedImageY.setRGB(x, y, newColor.getRGB());
            }
        }

        // Get horizontal and vertical edges together via formula.
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Color colorX = new Color(bufferedImageX.getRGB(x, y));

                int redX = colorX.getRed();
                int greenX = colorX.getGreen();
                int blueX = colorX.getBlue();

                int alphaX = colorX.getAlpha();

                Color colorY = new Color(bufferedImageY.getRGB(x, y));

                int redY = colorY.getRed();
                int greenY = colorY.getGreen();
                int blueY = colorY.getBlue();

                int alphaY = colorY.getAlpha();

                calculatePrewittRed(redX, redY);
                calculatePrewittGreen(greenX, greenY);
                calculatePrewittBlue(blueX, blueY);

                calculatePrewittAlpha(alphaX, alphaY);

                normalize();

                Color newColor = new Color(red, green, blue, alpha);

                red = 0;
                green = 0;
                blue = 0;
                alpha = 0;

                bufferedImage.setRGB(x, y, newColor.getRGB());
            }
        }

        return bufferedImage;
	}

	/*
	 *  
	 * Calculates alpha value of a pixel in Prewitt filter.
	 * 
	 * @param alphaX Alpha Value after calculating with Prewitt-X-Weight.
	 * @param alphaY Alpha Value after calculating with Prewitt-Y-Weight.
	 * 
	 */
	private void calculatePrewittAlpha(int alphaX, int alphaY) {
		alpha += Math.floor(Math.sqrt(Math.pow(alphaX, 2) + Math.pow(alphaY, 2)));
	}

	/*
	 *  
	 * Calculates blue value of a pixel in Prewitt filter.
	 * 
	 * @param blueX Blue Value after calculating with Prewitt-X-Weight.
	 * @param blueY Blue Value after calculating with Prewitt-Y-Weight.
	 * 
	 */
	private void calculatePrewittBlue(int blueX, int blueY) {
		blue += Math.floor(Math.sqrt(Math.pow(blueX, 2) + Math.pow(blueY, 2)));
	}

	
	/*
	 *  
	 * Calculates green value of a pixel in Prewitt filter.
	 * 
	 * @param greenX Green Value after calculating with Prewitt-X-Weight.
	 * @param greenY Green Value after calculating with Prewitt-Y-Weight.
	 * 
	 */
	private void calculatePrewittGreen(int greenX, int greenY) {
		green += Math.floor(Math.sqrt(Math.pow(greenX, 2) + Math.pow(greenY, 2)));
	}


	/*
	 *  
	 * Calculates red value of a pixel in Prewitt filter.
	 * 
	 * @param redX Green Value after calculating with Prewitt-X-Weight.
	 * @param redY Green Value after calculating with Prewitt-Y-Weight.
	 * 
	 */
	private void calculatePrewittRed(int redX, int redY) {
		red += Math.floor(Math.sqrt(Math.pow(redX, 2) + Math.pow(redY, 2)));
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
}