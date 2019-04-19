package com.quality.project.image;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.quality.project.properties.AdminProperties;

public class UploadImageFile {

	private ImageValidator validator;
	private MultipartFile savedFile;
	private String defaultLocationToSaveFile;

	public UploadImageFile() {
		validator = new ImageValidator();
		defaultLocationToSaveFile = AdminProperties.getProperty("app.admin.imageLocation");
	}

	public boolean save(MultipartFile fileToBeUploaded) {
		savedFile = fileToBeUploaded;
		if (validator.isValidImage(fileToBeUploaded)) {
			saveFile(fileToBeUploaded);
			return true;
		}
		return false;
	}

	public IImages getLastSavedImage() {
//Hard coded as Spring is not able to access files if the whole system location is provided
		String FileLocation = "/assets/images/";
		Images image = new Images();
		image.setImageName(savedFile.getOriginalFilename());
		image.setImageLocation(FileLocation + savedFile.getOriginalFilename());
		return image;
	}

	public Map<String, String> imageErrors() {
		return validator.getErrors();
	}

	private void saveFile(MultipartFile fileToBeUploaded) {
		FileOutputStream output;
		File createTemporaryFileToWrite;
		Path fileStorageLocationPath = Paths.get(defaultLocationToSaveFile).toAbsolutePath().normalize();
		String fileStorageLocation = fileStorageLocationPath.toString();

		try {
			createTemporaryFileToWrite = new File(fileStorageLocation + "/" + fileToBeUploaded.getOriginalFilename());
			output = new FileOutputStream(fileStorageLocation + "/" + fileToBeUploaded.getOriginalFilename());
			output.write(fileToBeUploaded.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
