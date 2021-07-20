package com.boom.service.photographer;

import java.util.List;

import com.boom.entity.Photographer;

public interface PhotographerQueryService {

	public Photographer findOne(Long id);

	public List<Photographer> findAll(Integer pageNo, Integer pageSize, String sortBy);
}
