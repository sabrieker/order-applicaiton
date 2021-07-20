package com.boom.service.order;

import java.util.List;

import com.boom.dto.order.OrderDTO;
import com.boom.entity.PhotoOrder;

public interface OrderQueryService {

	PhotoOrder findOne(Long id);

	List<OrderDTO> findAll(Integer pageNo, Integer pageSize, String sortBy);
}
