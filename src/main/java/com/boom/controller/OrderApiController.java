package com.boom.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.boom.dto.order.OrderDTO;
import com.boom.dto.order.OrderRequestDTO;
import com.boom.dto.response.BaseApiResponse;
import com.boom.entity.PhotoOrder;
import com.boom.entity.Photographer;
import com.boom.enums.order.OrderStatus;
import com.boom.service.order.OrderCommandService;
import com.boom.service.order.OrderQueryService;
import com.boom.service.photographer.PhotographerCommandService;
import com.boom.service.photographer.PhotographerQueryService;
import com.boom.validation.CheckTimeFormat;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "api/order", produces = { MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8" }, consumes = {
		MediaType.APPLICATION_JSON_VALUE})
@OpenAPIDefinition(
        info = @Info(
                title = "Order API",
                version = "0.0.1",
                description = "This API provides resources for Order Data",
                license = @License(name = "Apache 2.0", url = "https://foo.bar"),
                contact = @Contact(url = "https://sample-server.com", name = "Sabri", email = "sabrieker@gmail.com")
        )
)
@Tag(name = "order-api")
@RequiredArgsConstructor
@Slf4j
public class OrderApiController {
	
	private final OrderCommandService orderCommandService;
	private final OrderQueryService orderQueryService;
	private final PhotographerQueryService photographerQueryService;
	private final PhotographerCommandService photographerCommandService;
	private final ModelMapper mapper;
	
	@GetMapping(value = "/list", consumes = { MediaType.ALL_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@Operation(description = "List all the Order request via Pagination")
	public List<OrderDTO> listAll( 
			@RequestParam(defaultValue = "0") Integer pageNo, 
			@RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortBy) {
		log.info("List all the Orders via Pagination");
		
		return orderQueryService.findAll(pageNo,pageSize,sortBy);
	}

	@PutMapping(value = "/create", consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@Operation(description = "Allows to create a new Order.")
	public BaseApiResponse create(@Valid @RequestBody  OrderRequestDTO orderDto) {
		log.info("Create Order with given data.{}",orderDto);
		
		orderCommandService.save(orderDto);
	
		return BaseApiResponse.builder().success(Boolean.TRUE).build();
	}
	

	@GetMapping(value = "/{id}", consumes = { MediaType.ALL_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@Operation(description = "Change the status of the Order")
	public OrderDTO getOne(@PathVariable long id) {
		log.info("Get the details of the Order");
		
		return mapper.map(orderQueryService.findOne(id),OrderDTO.class);
	}
	
	@PostMapping(value = "/schedule/{id}", consumes = { MediaType.ALL_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@Operation(description = "Schedule the time for the given Order")
	public OrderDTO schedule(@PathVariable long id,
			@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date, 
			@RequestParam("time") @DateTimeFormat(pattern = "HH:mm") @CheckTimeFormat String time) {
		log.info("Update the current status of the Order");
		
		PhotoOrder order= orderQueryService.findOne(id);
		
		if(order.getStatus() != OrderStatus.UNSCHEDULED) {
			throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, "Order is allready scheduled.");
		}
		
	    LocalTime timePart = LocalTime.parse(time,DateTimeFormatter.ofPattern("HH:mm"));
		if(timePart.isBefore(LocalTime.now())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Time should be in the future between 08:00 and 20:00.");
		}
	    
		return mapper.map(orderCommandService.schedule(order,LocalDateTime.of(date, timePart)),OrderDTO.class);
	}

	/**
	 * M
	 * @param id
	 * @param photographerId
	 * @return
	 */
	@PostMapping(value = "/assign/{id}", consumes = { MediaType.ALL_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@Operation(description = "Change the status of the Order with assigning an Photographer.")
	public BaseApiResponse assign(@PathVariable long id,@RequestParam("photographer") long photographerId) {
		log.info("Assign the Photographer to the Order");
		
		PhotoOrder order = orderQueryService.findOne(id);
		if(order.getStatus() != OrderStatus.PENDING) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Order is not in the PENDING state for assigning a Photographer");
		}
		
		Photographer photographer = photographerQueryService.findOne(photographerId);
		
		order.setAssignTime(LocalDateTime.now());
		order.setStatus(OrderStatus.ASSIGNED);
		order.setUpdateTime(LocalDateTime.now());
		order.getPhotographers().add(photographer);
		
		photographer.getPhotoOrders().add(order);
		
		orderCommandService.save(order);
		photographerCommandService.save(photographer);
		
		
		return BaseApiResponse.builder().success(Boolean.TRUE).build();
	}
	
	@PostMapping(value = "/cancel/{id}", consumes = { MediaType.ALL_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@Operation(description = "Cancel the Order.")
	public BaseApiResponse cancel(@PathVariable long id) {
		log.info("Cancel the Order");
		
		PhotoOrder order = orderQueryService.findOne(id);
		order.setIsCancelled(Boolean.TRUE);
		order.setUpdateTime(LocalDateTime.now());
		
		orderCommandService.save(order);
		
		return BaseApiResponse.builder().success(Boolean.TRUE).build();
	}
}
