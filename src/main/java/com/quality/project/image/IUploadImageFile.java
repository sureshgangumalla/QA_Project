package com.quality.project.image;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface IUploadImageFile {
	public boolean save(MultipartFile file);

	public IImages getLastSavedImage();

	public Map<String, String> ImageErrors();
}
