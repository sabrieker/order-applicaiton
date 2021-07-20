package com.boom.service.order;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.boom.dto.order.OrderRequestDTO;
import com.boom.dto.order.OrderRequestDTO.Contact;
import com.boom.entity.Customer;
import com.boom.entity.PhotoOrder;
import com.boom.enums.order.OrderStatus;
import com.boom.service.customer.CustomerCommandService;
import com.boom.service.customer.CustomerQueryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderCommandServiceImpl implements OrderCommandService {

	private final PhotoOrderRepository orderRepository;
	private final CustomerQueryService customerQueryService;
	private final CustomerCommandService customerCommandService;
	private final ModelMapper mapper;

	@Override
	public PhotoOrder save(OrderRequestDTO orderDTO) {

		Contact contact = orderDTO.getContact();

		Customer customer = customerQueryService.findByEmail(contact.getEmail());

		if (customer == null) {
			customer = Customer.builder().name(contact.getName()).surname(contact.getSurname())
					.email(contact.getEmail()).cellNumber(contact.getCellNumber())
					.build();
			customer.setUpdateTime(LocalDateTime.now());
			customer.setCreateTime(LocalDateTime.now());
			customer = customerCommandService.save(customer);
		}

		/***
		 * is Order allready exist? Duplicate?
		 */
		PhotoOrder order = mapper.map(orderDTO, PhotoOrder.class);
		if(orderDTO.getTime() != null && orderDTO.getLocalDate() != null) {
			LocalTime timePart = LocalTime.parse(orderDTO.getTime(),DateTimeFormatter.ofPattern("HH:mm"));
			LocalDateTime scheduledTime = LocalDateTime.of(orderDTO.getLocalDate(), timePart);
			
			order.setScheduledTime(scheduledTime);
		}
		
		order.setCustomer(customer);
		order.setCreateTime(LocalDateTime.now());
		order.setUpdateTime(LocalDateTime.now());
		if (order.getScheduledTime() == null)
			order.setStatus(OrderStatus.UNSCHEDULED);
		else
			order.setStatus(OrderStatus.PENDING);

		return this.save(order);
	}

	@Override
	public PhotoOrder save(PhotoOrder order) {
		log.info("Save the order: {}", order);
		if(order.getId() == null) {
			order.setCreateTime(LocalDateTime.now());
		}
		order.setUpdateTime(LocalDateTime.now());
		return orderRepository.save(order);
	}

	@Override
	public PhotoOrder schedule(PhotoOrder order, LocalDateTime of) {

		if (order.getStatus() != OrderStatus.UNSCHEDULED) {
			throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, "Order is allready scheduled.");
		}

		order.setStatus(OrderStatus.PENDING);
		order.setUpdateTime(LocalDateTime.now());
		order.setScheduledTime(of);

		return orderRepository.save(order);
	}

}
