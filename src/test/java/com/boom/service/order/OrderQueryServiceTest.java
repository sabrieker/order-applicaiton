package com.boom.service.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import com.boom.dto.order.OrderDTO;
import com.boom.entity.PhotoOrder;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class OrderQueryServiceTest {

	@Autowired
	private ModelMapper mapper;
	
	@Mock
	private PhotoOrderRepository orderRepository;
	
	@Test
	void testFindOne() {
		String title = "TEST-DATA";
		Optional<PhotoOrder> orderExpected = Optional.of(PhotoOrder.builder().id(1L).title(title).build());
		
		when(orderRepository.findById(1L)).thenReturn(orderExpected);

		OrderQueryService service = new OrderQueryServiceImpl(mapper, orderRepository);
		PhotoOrder findOne = service.findOne(1L);
		assertEquals(orderExpected.get().getTitle(), findOne.getTitle());
	}

	@Test
	void testFindAll() {
		
		Page<PhotoOrder> expectedOrders = Page.empty();
		
		when(orderRepository.findAll(Mockito.any(Pageable.class)))
			.thenReturn(expectedOrders);
		
		OrderQueryService service = new OrderQueryServiceImpl(mapper, orderRepository);
		List<OrderDTO> orders = service.findAll(1, 10, "id");
		
		assertTrue(orders.isEmpty());
		
	}

}
