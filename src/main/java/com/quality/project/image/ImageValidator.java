package com.quality.project.image;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public class ImageValidator implements IImageValidator {
	private Map<String, String> errorMessages;
	private ArrayList<String> validFileExtensions;

	public ImageValidator() {
		this.errorMessages = new HashMap<String, String>();
		this.validFileExtensions = new ArrayList<String>();
		addValidFileExtensions();
	}

	@Override
	public Map<String, String> getErrors() {
		return errorMessages;
	}

	@Override
	public boolean isValidImage(MultipartFile file) {
		boolean validity = true;
		if (null == file) {
			return false;
		}
		String fileName = file.getOriginalFilename();
		if (fileName.isEmpty()) {
			validity = false;
			errorMessages.put("imageFile", "Please provide Image");
		}
		if (isNotImage(fileName)) {
			validity = false;
			errorMessages.put("imageFile", "Not a Image file, Please provide a Image file");
		}
		return validity;
	}

	public boolean isNotImage(String fileName) {
		boolean validImage = true;
		int fileNameLength = fileName.length();
		if (fileNameLength > 4) {
			String fileExtension = fileName.substring(fileNameLength - 4);
			for (String validFileExtension : validFileExtensions) {
				if (validFileExtension.equals(fileExtension)) {
					validImage = false;
				}
			}
		} else {
// Below is defined to bypass, if image name is empty and to provide 'empty' error messages to user
			validImage = false;
		}
		return validImage;
	}

	private void addValidFileExtensions() {
//These extensions can be retrieved from Database 
		validFileExtensions.add(".png");
		validFileExtensions.add(".jpg");
	}

}
