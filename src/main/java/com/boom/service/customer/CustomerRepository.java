package com.boom.service.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.boom.entity.Customer;

@Repository
interface CustomerRepository extends JpaRepository<Customer, Long>,PagingAndSortingRepository<Customer, Long>{

	Customer findByEmail(String email);
}
