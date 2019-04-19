package com.quality.project.JUnit.ImageValidatorTest;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.multipart.MultipartFile;

import com.quality.project.image.ImageValidator;

public class ImageValidatorTest {
	private IImageFileMockFactory imageMock;
	private ImageValidator validator;

	@Before
	public void setUp() {
		imageMock = new ImageFileMockFactory();
		validator = new ImageValidator();
	}

	@Test
	public void emptyImageFileTest() {
		MultipartFile emptyFile = imageMock.getImages("emptyImageFile");
		assertTrue(false == validator.isValidImage(emptyFile));
	}

	@Test
	public void inValidImageFileExtensionTest() {
		MultipartFile inValidImageFile = imageMock.getImages("inValidImageFile");
		assertTrue(false == validator.isValidImage(inValidImageFile));
	}

	@Test
	public void validImageFileTest() {
		MultipartFile validFile = imageMock.getImages("validImageFile");
		assertTrue(true == validator.isValidImage(validFile));
	}
}