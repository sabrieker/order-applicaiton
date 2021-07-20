package com.boom.service.photographer;

import org.springframework.stereotype.Service;

import com.boom.entity.Photographer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class PhotographerCommandServiceImpl implements PhotographerCommandService {

	private final PhotographerRepository repository;
	
	@Override
	public void save(Photographer photographer) {
		log.info("Save Photographer {}",photographer);
		
		repository.save(photographer);
		
		
	}

}
