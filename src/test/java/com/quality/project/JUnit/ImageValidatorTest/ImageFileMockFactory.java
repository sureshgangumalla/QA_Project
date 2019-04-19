package com.quality.project.JUnit.ImageValidatorTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

public class ImageFileMockFactory implements IImageFileMockFactory {
	private Map<String, MultipartFile> imageFiles;
	Path fileStorageLocationPath;
	String fileStorageLocation;

	public ImageFileMockFactory() {
		this.imageFiles = new HashMap<String, MultipartFile>();
		this.fileStorageLocationPath = Paths.get("./src/main/webapp/assets/images").toAbsolutePath().normalize();
		this.fileStorageLocation = fileStorageLocationPath.toString();
		this.createImages();
	}

	public void createImages() {

		try {
			MultipartFile emptyFile = null;
			imageFiles.put("emptyImageFile", emptyFile);

			MultipartFile validFile;
			FileInputStream mockFile1 = new FileInputStream(new File(fileStorageLocation + "/default.jpg"));
			validFile = new MockMultipartFile("default.jpg", "test.png", "", mockFile1);
			validFile.getOriginalFilename();
			imageFiles.put("validImageFile", validFile);

			MultipartFile inValidFile;
			FileInputStream mockFile2 = new FileInputStream(new File(fileStorageLocation + "/default.jpg"));
			inValidFile = new MockMultipartFile("default.xyz", "test.xyz", "", mockFile2);
			inValidFile.getOriginalFilename();
			imageFiles.put("inValidImageFile", inValidFile);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public MultipartFile getImages(String type) {
		return imageFiles.get(type);
	}
}
