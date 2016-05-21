package org.jis.generator;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.imageio.ImageIO;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GeneratorCoverageTest {
	
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
	
	
	private static void copyFile(File source, File dest)
			throws IOException {
		InputStream input = null;
		OutputStream output = null;
		try {
			input = new FileInputStream(source);
			output = new FileOutputStream(dest);
			byte[] buf = new byte[1024];
			int bytesRead;
			while ((bytesRead = input.read(buf)) > 0) {
				output.write(buf, 0, bytesRead);
			}
		} finally {
			input.close();
			output.close();
		}
	}


	
	@Test
	public void createZipTest() throws IOException {
		File zipfile = new File("src/test/resources/str.zip");
		Vector<File> selected = new Vector<File>();
		File file = new File("src/test/resources/image.jpg");
		File tempFile = new File("src/test/resources/imageTemp.jpg");
		
		copyFile(file, tempFile);
		
		selected.add(tempFile);
		
		generator.createZip(zipfile, selected);
		
		assertTrue(zipfile.exists());
		
		zipfile.delete();
	}
	
	@Test
	public void createZipFileNotFoundExceptionTest() {
		File zipfile = new File("src/test/resources/str.zip");
		
		Vector<File> selected = new Vector<File>();
		
		File file = new File("src/test/resources/heyy.jpg");
		
		selected.add(file);
		
		generator.createZip(zipfile, selected);
		assertTrue(zipfile.exists());
		zipfile.delete();

	}
	
	@Test (expected = NullPointerException.class)
	public void generateSingleNullPointerExceptionTest() throws IOException {
		File file = new File("src/test/resources/image.jpg");
		File tempFile = new File("src/test/resources/imageTemp.jpg");
		
		
		copyFile(file, tempFile);
		generator.generateSingle(tempFile, bufferedImage);
	}
	
	@Test
	public void generateImageTest() throws IOException {
		File file = new File("src/test/resources/image.jpg");
		File tempFile = new File("src/test/resources/imageTemp.jpg");
		File iout = new File ("src/test/resources/imageTempOut.jpg");
		boolean print = true;
		int width = 300;
		int height = 500;
		String praefix = "pic";
		copyFile(file, tempFile);
		generator.generateImage(tempFile, iout, print, width, height, praefix);
		assertTrue(iout.exists());
		iout.delete();
	}
	
	@Test(expected = NullPointerException.class)
	public void generateImageNullPointerExceptionTest() throws IOException {
		File file = new File("src/test/resources/image.jpg");
		File tempFile = new File("src/test/resources/imageTemp.jpg");
		File iout = new File ("src/test/resources/imageTempOut.jpg");
		boolean print = true;
		int width = 0;
		int height = 0;
		String praefix = "pic";
		copyFile(file, tempFile);
		generator.generateImage(tempFile, iout, print, width, height, praefix);
		iout.delete();
		
	}
	
	@Test(expected = NullPointerException.class)
	public void rotateTest() throws IOException {
		File file = new File("src/test/resources/image.jpg");
		File tempFile = new File("src/test/resources/imageTemp.jpg");
		copyFile(file, tempFile);
		generator.rotate(tempFile, 0);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void rotateIllegalArgumentExceptionTest() {
		generator.rotate(null);
	}
	
	@Test(expected = NullPointerException.class)
	public void rotateNullPointerExceptionTest() throws IOException {
		File file = new File("src/test/resources/image.jpg");
		File tempFile = new File("src/test/resources/imageTemp.jpg");
		copyFile(file, tempFile);
		generator.rotate(tempFile);
	}
	

	
	
	
	



}
