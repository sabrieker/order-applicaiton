package com.boom.service.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.boom.dto.order.OrderRequestDTO;
import com.boom.dto.order.OrderRequestDTO.Contact;
import com.boom.entity.Customer;
import com.boom.entity.PhotoOrder;
import com.boom.enums.order.OrderPhotoType;
import com.boom.service.customer.CustomerCommandService;
import com.boom.service.customer.CustomerQueryService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OrderCommandServiceTest {

	@Autowired
	private ModelMapper mapper;

	@Mock
	private CustomerQueryService customerQueryService;
	@Mock 
	private CustomerCommandService customerCommandService;
	@Mock
	private PhotoOrderRepository orderRepository;
	
	@Test
	public void saveOrder() {

		String logisticInfo = "Free Text Logistic Info";
		String title = "Free Text Optional Title";
		String email = "some@email.com";
		String name = "Name";
		String surname = "Surname";
		String cellNumber = "5447823652";

		Contact contact = Contact.builder().name(name).surname(surname).email(email)
				.cellNumber(cellNumber).build();
		Customer customer = Customer.builder().name(name).surname(surname).email(email).cellNumber(cellNumber).build();
		
		OrderRequestDTO dto = OrderRequestDTO.builder().contact(contact).localDate(LocalDate.now())
				.logisticInfo(logisticInfo).photoType(OrderPhotoType.EVENT).title(title).time("09:00").build();

		OrderCommandService service = new OrderCommandServiceImpl(orderRepository, customerQueryService, customerCommandService, mapper);
		
		when(customerQueryService.findByEmail(email)).thenReturn(customer );
		when(orderRepository.save(Mockito.any())).thenReturn(PhotoOrder.builder().id(1L).build());

		PhotoOrder order = service.save(dto);
		assertEquals(order.getId(), null);

	}

}
