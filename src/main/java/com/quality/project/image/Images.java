package com.quality.project.image;

import org.springframework.web.multipart.MultipartFile;

public class Images implements IImages{


	private String imageName;

	private String imageLocation;
	
	private String imageNameAndLocation;

	private MultipartFile file;

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getImageLocation() {
		return imageLocation;
	}

	public void setImageLocation(String imageLocation) {
		this.imageLocation = imageLocation;
	}


	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getImageNameAndLocation() {
		return imageNameAndLocation;
	}

	public void setImageNameAndLocation(String imageNameAndLocation) {
		this.imageNameAndLocation = imageNameAndLocation;
	}
	
	

}
