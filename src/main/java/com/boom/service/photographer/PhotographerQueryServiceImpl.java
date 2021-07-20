package com.boom.service.photographer;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.boom.entity.Photographer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class PhotographerQueryServiceImpl implements PhotographerQueryService {

	private final PhotographerRepository repository;
	
	@Override
	public Photographer findOne(Long id) {
		
		log.info("Search for the Photographer:{}",id);
		
		Optional<Photographer> photographer = repository.findById(id);
		if(photographer.isPresent()) {
			return photographer.get();
		}
		
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Photographer is not founds.");
	}

	@Override
	public List<Photographer> findAll(Integer pageNo, Integer pageSize, String sortBy) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		
		return repository.findAll(paging).stream().collect(Collectors.toList());
	}

}
