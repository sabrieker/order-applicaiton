package com.boom.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@Entity
@Table(name="photographer")
@NoArgsConstructor @AllArgsConstructor
public class Photographer extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	private String surname;
	private String email;
	private String cellNumber;
	private String uniqueName;
	
	@OneToMany(mappedBy = "photographer")
	private List<PhotoAlbum> photoSet;
	
	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(
	        name = "Photographer_PhotoOrder", 
	        joinColumns = { @JoinColumn(name = "photogprapher_id") }, 
	        inverseJoinColumns = { @JoinColumn(name = "photo_order_id") }
	    )
	@Builder.Default
	private List<PhotoOrder> photoOrders = new ArrayList<>();
}
