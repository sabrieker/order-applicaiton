package com.boom.service.customer;

import com.boom.entity.Customer;

public interface CustomerQueryService {

	Customer findByEmail(String email);
}
