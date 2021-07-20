package com.boom.service.order;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.boom.dto.order.OrderDTO;
import com.boom.entity.PhotoOrder;

import lombok.AllArgsConstructor;

/*
 * 
 * Query Service for reading Order data
 */
@Service
@AllArgsConstructor
public class OrderQueryServiceImpl implements OrderQueryService{

	private final ModelMapper mapper;
	private final PhotoOrderRepository orderRepository;
	
	@Override
	public PhotoOrder findOne(Long id) {
		Optional<PhotoOrder> order = orderRepository.findById(id);
		
		if(order.isPresent()) {
			return order.get();
		}
		
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
	}

	@Override
	public List<OrderDTO> findAll(Integer pageNo, Integer pageSize, String sortBy) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		
		return orderRepository.findAll(paging)
							  .stream()
							  .map(var -> mapper.map(var, OrderDTO.class))
							  .collect(Collectors.toList());
	}

}
