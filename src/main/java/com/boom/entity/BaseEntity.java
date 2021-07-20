package com.boom.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public abstract class BaseEntity {

	@NotNull
    @Column(nullable = false, columnDefinition = "datetimeoffset")
    private LocalDateTime createTime;
	
	@NotNull
    @Column(nullable = false, columnDefinition = "datetimeoffset")
    private LocalDateTime updateTime;
	
    @Column(length = 200)
    private String fromComputer;
}
