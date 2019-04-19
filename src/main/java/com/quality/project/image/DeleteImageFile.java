package com.quality.project.image;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.quality.project.properties.AdminProperties;

public class DeleteImageFile {

	private String DefaultLocationToDeleteFile;
	private Path fileStorageLocation;
	private String imagePath;

	public DeleteImageFile() {
		this.DefaultLocationToDeleteFile = AdminProperties.getProperty("app.admin.imageLocation");
		this.fileStorageLocation = Paths.get(DefaultLocationToDeleteFile).toAbsolutePath().normalize();
		this.imagePath = fileStorageLocation.toString();
	}

	public boolean delete(IImages deletedImage) {
		File file = new File(imagePath + "/" + deletedImage.getImageName());
		return file.delete();
	}

}
