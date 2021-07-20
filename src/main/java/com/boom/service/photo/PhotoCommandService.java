package com.boom.service.photo;

import java.util.List;

import com.boom.entity.Photo;
import com.boom.entity.PhotoAlbum;

public interface PhotoCommandService {

	void create(PhotoAlbum photoSet);
	void uploadPhoto(List<Photo> photos);
}
