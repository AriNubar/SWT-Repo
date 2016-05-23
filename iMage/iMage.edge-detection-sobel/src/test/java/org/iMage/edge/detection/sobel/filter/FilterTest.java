package org.iMage.edge.detection.sobel.filter;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;

import java.io.File;
import java.nio.file.Files;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;


/*
 * Test class for Filters.
 * 
 */
public class FilterTest {

	private BufferedImage bufferedImage;

	 private File file = new File("src/test/resources/camera_obscura.png");
	// private File file = new File("src/test/resources/cancel.png");
	// private File file = new File("src/test/resources/a.png");
	private File fileCopy = new File("src/test/resources/copy.png");

	@Before
	public void setUp() throws Exception {
		Files.copy(file.toPath(), fileCopy.toPath());

		bufferedImage = ImageIO.read(fileCopy);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	
			fileCopy.delete();
		
	}
	
	@Test
	public void grayScaleFilter() {
		GrayScaleFilter grayScaleFilter = new GrayScaleFilter();

		bufferedImage = grayScaleFilter.applyFilter(bufferedImage);
	}

	@Test
	public void blurFilter() {
		BlurFilter blurFilter = new BlurFilter();

		bufferedImage = blurFilter.applyFilter(bufferedImage);
	}


	@Test
	public void lowerThresholdFilter() {
		LowerThresholdFilter lowerThresholdFilter = new LowerThresholdFilter();

		bufferedImage = lowerThresholdFilter.applyFilter(bufferedImage);
	}

	@Test
	public void sobelFilter() {
		SobelFilter sobelFilter = new SobelFilter();

		bufferedImage = sobelFilter.applyFilter(bufferedImage);
	}


	@Test
	public void prewittFilter() {
		PrewittFilter prewittFilter = new PrewittFilter();

		bufferedImage = prewittFilter.applyFilter(bufferedImage);
	}
}
