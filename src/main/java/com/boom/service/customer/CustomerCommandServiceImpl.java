package com.boom.service.customer;

import org.springframework.stereotype.Service;

import com.boom.entity.Customer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerCommandServiceImpl implements CustomerCommandService {

	private final CustomerRepository customerRepository;
	
	@Override
	public Customer save(Customer customer) {
		log.info("Save the customer(Email:{},Name:{},Surname:{},CellNumber:{}) ",customer.getEmail(),customer.getName(),customer.getSurname(),customer.getCellNumber());
		
		return customerRepository.save(customer);
	}

}
