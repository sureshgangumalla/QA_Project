package com.quality.project.image;

import java.util.ArrayList;

public class ImagesToBeAddedForRecipe {

	private ArrayList<IImages> images;
	private static ImagesToBeAddedForRecipe imagesToBeSaved = null;

	public ImagesToBeAddedForRecipe() {
		images = new ArrayList<IImages>();
	}

	public static ImagesToBeAddedForRecipe getImagesToBeSavedInstance() {

		if (null == imagesToBeSaved) {
			imagesToBeSaved = new ImagesToBeAddedForRecipe();
		}
		return imagesToBeSaved;
	}

	public void addImage(IImages image) {
		images.add(image);
	}

	public void removeImage(IImages removedImage) {
		for (IImages image : images) {
			if (image.getImageName().equals(removedImage.getImageName())) {
				images.remove(image);
				break;
			}
		}
	}

	public void setImages(ArrayList<IImages> images) {
		this.images = images;
	}

	public ArrayList<IImages> getImages() {
		return images;
	}

	public void clear() {
		images.clear();
	}
}
