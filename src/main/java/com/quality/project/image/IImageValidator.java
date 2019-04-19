package com.quality.project.image;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface IImageValidator {
	public Map<String, String> getErrors();

	public boolean isValidImage(MultipartFile File);

}
