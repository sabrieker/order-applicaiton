package com.boom.service.customer;

import org.springframework.stereotype.Service;

import com.boom.entity.Customer;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerQueryServiceImpl implements CustomerQueryService {

	private final CustomerRepository customerRepository;
	
	@Override
	public Customer findByEmail(String email) {
		return customerRepository.findByEmail(email);
	}

}
