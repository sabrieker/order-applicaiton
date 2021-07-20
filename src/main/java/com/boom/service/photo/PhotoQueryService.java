package com.boom.service.photo;

import java.util.List;

import com.boom.entity.Photo;

public interface PhotoQueryService {

	List<Photo> findByOrder();
}
