package com.boom.service.order;

import java.time.LocalDateTime;

import com.boom.dto.order.OrderDTO;
import com.boom.dto.order.OrderRequestDTO;
import com.boom.entity.PhotoOrder;

public interface OrderCommandService {

	PhotoOrder save(PhotoOrder order);
	PhotoOrder save(OrderRequestDTO orderDTO);

	PhotoOrder schedule(PhotoOrder order, LocalDateTime of);

}
