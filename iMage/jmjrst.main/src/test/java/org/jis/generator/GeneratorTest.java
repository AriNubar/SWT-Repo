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
	public void test() {
		fail("Not yet implemented");
	}

}
