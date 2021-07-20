package com.boom.service.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.boom.entity.PhotoOrder;

@Repository
interface PhotoOrderRepository extends JpaRepository<PhotoOrder, Long>,PagingAndSortingRepository<PhotoOrder, Long>{

}
