package org.jis.generator;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GeneratorTest {

	private Generator generator;
	private BufferedImage bufferedImage;

	@Before
	public void setUp() throws Exception {
		generator = new Generator(null, 0);

		bufferedImage = null;
		try {
			bufferedImage = ImageIO.read(new File("src/test/resources/picture.jpg"));
		} catch (IOException e) {
		}
	}

	@After
	public void tearDown() throws Exception {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("HHmmss_SSS");
		File output = new File("target/testData/rotatedPicture_" + format.format(date) + ".jpg");
		ImageIO.write(bufferedImage, "jpg", output);
	}

	@Test
	public void nullImageTest() {
		BufferedImage image1 = null;
		BufferedImage rotatedImage = generator.rotateImage(image1, 0.0);
		assertEquals(image1, rotatedImage);
	}

	@Test
	public void rotateZeroDegreeTest() {
		BufferedImage rotatedImage = generator.rotateImage(bufferedImage, 0.0);
		assertEquals(bufferedImage, rotatedImage);
	}

	@Test(expected = IllegalArgumentException.class)
	public void illegalArgumentExceptionTest() {
		generator.rotateImage(bufferedImage, 1.0);
	}

	@Test(expected = NullPointerException.class)
	public void nullPointerExceptionTest() {
		generator.rotateImage(null, 1.0);
	}

	@Test
	public void test90() {
		int height = bufferedImage.getHeight();
		int width = bufferedImage.getWidth();

		bufferedImage = generator.rotateImage(bufferedImage, Generator.ROTATE_90);

		assertEquals(bufferedImage.getHeight(), width);
		assertEquals(bufferedImage.getWidth(), height);
	}

	@Test
	public void test270() {
		int height = bufferedImage.getHeight();
		int width = bufferedImage.getWidth();

		bufferedImage = generator.rotateImage(bufferedImage, Generator.ROTATE_270);

		assertEquals(bufferedImage.getHeight(), width);
		assertEquals(bufferedImage.getWidth(), height);
	}

}
