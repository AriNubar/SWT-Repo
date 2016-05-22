package org.iMage.edge.detection.sobel.filter;

import java.awt.image.BufferedImage;

import org.iMage.edge.detection.base.ImageFilter;

/**
 * Filters all pixels that have a grayscale color below a certain threshold and sets them to 0 (makes them black).
 * Pixels above the threshold are converted to grayscale normally (as defined in {@link GrayScaleFilter}).
 */
public class LowerThresholdFilter implements ImageFilter {
	
	BufferedImage  image;
	   int width;
	   int height;

	/** Default constructor must be available! */
	public LowerThresholdFilter() {
		// TODO Auto-generated constructor stub
	}

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
		            
		            if (average < 127) {
		            	Color newColor = new Color(0, 0, 0, alpha);
			            image.setRGB(j,i, newColor.getRGB());
		            } else {
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
