package com.boom.dto.order;

import java.time.LocalDateTime;

import com.boom.enums.order.OrderPhotoType;
import com.boom.enums.order.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@AllArgsConstructor @NoArgsConstructor
public class OrderDTO {

	private Long id;
	private OrderPhotoType photoType;
	private OrderStatus status;
	private String title;
	private String logisticInfo;
	private LocalDateTime scheduledTime;
	private LocalDateTime assignTime;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String fromComputer;
}
