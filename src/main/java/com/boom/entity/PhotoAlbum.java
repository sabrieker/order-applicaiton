package com.boom.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.boom.enums.photoalbum.PhotoAlbumStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@Entity
@Table(name="photo")
@NoArgsConstructor @AllArgsConstructor
public class PhotoAlbum extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	@Builder.Default
	private Boolean isCompleted  = false;
	
	@NotEmpty
	@Enumerated(EnumType.STRING)
	private PhotoAlbumStatus status;
	
	@ManyToOne
    @JoinColumn(name="photographer_id", nullable=false)
	private Photographer photographer;
	
	@OneToMany(mappedBy = "photoAlbum")
	private List<Photo> photo;
	
	@ManyToOne
	@JoinColumn(name="photo_order_id", nullable=false)
	private PhotoOrder photoOrder;
}
