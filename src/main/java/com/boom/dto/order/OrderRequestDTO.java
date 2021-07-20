package com.boom.dto.order;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.boom.enums.order.OrderPhotoType;
import com.boom.validation.CheckTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@AllArgsConstructor @NoArgsConstructor
public class OrderRequestDTO {

	@NotNull(message = "Please provide photo type from the list : (REAL_ESTATE, FOOD,EVENT)") 
	private OrderPhotoType photoType;
	
	private String title;
	private String logisticInfo;
	
	@NotNull(message = "Please provide Date for the order") 
	@Future(message = "Requested reservation date and time should be in the future") 
	private LocalDate localDate;
	
	@NotNull(message = "Please provide time for the order between 08:00 and 20:00 with HH:mm format")
	@CheckTimeFormat(message = "Please provide a time between 08.00 and 20.00")
	private String time;

	@NotNull(message = "Please provide Contact Details")
	private Contact contact;

	@Data @Builder
	@AllArgsConstructor @NoArgsConstructor
	public static class Contact{
		@NotNull(message = "Name cannot be null.") 
		private String name;
		
		@NotNull(message = "Surname cannot be null.") 
		private String surname;
		
		@Email(message = "Enter a valid email address.") 
		private String email;
		
		@Size(min = 10, max = 14, message = "Cell number must be between 10-14 characters")
		private String cellNumber;
	}
}
