package org.jis.generator;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Before;
import org.junit.Test;

public class GeneratorTest {
	
	private Generator generator;
	private BufferedImage bufferedImage;
	
	@Before
	public void setUp() throws Exception{
		generator = new Generator(null, 0);
		
		bufferedImage = null;
		try {
			bufferedImage = ImageIO.read(new File("src/test/resources/picture.jpg"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
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
	
	

}
