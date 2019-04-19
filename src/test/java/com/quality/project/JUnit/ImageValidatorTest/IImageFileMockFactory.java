package com.quality.project.JUnit.ImageValidatorTest;

import org.springframework.web.multipart.MultipartFile;

public interface IImageFileMockFactory {
	public void createImages();

	public MultipartFile getImages(String type);
}
