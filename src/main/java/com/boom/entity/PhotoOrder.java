package com.boom.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
@Entity
@Table(name="photo_order")
@AllArgsConstructor @NoArgsConstructor
public class PhotoOrder extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private OrderPhotoType photoType;
	
	@NotNull
	private String title;
	
	@NotNull
	private String logisticInfo;
	
	private LocalDateTime scheduledTime;
	private LocalDateTime assignTime;
	
	@Column(nullable = false)
	@Builder.Default
	private Boolean isCancelled = false;
	
	@ManyToOne
    @JoinColumn(name="customer_id", nullable=false)
	private Customer customer;
	
	@OneToMany(mappedBy = "photoOrder")
	private List<PhotoAlbum> photoAlbum;

	@ManyToMany(mappedBy = "photoOrders")
	@Builder.Default
	private List<Photographer> photographers = new ArrayList<>();
}
