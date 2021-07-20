package com.boom.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.boom.dto.response.BaseApiResponse;
import com.boom.entity.PhotoAlbum;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "api/photo-album", produces = { MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8" }, consumes = {
		MediaType.APPLICATION_JSON_VALUE})
@OpenAPIDefinition(
        info = @Info(
                title = "Photo Album API",
                version = "0.0.1",
                description = "This API provides resources for Photo Album Data",
                license = @License(name = "Apache 2.0", url = "https://foo.bar"),
                contact = @Contact(url = "https://sample-server.com", name = "Sabri", email = "sabrieker@gmail.com")
        )
)
@Tag(name = "photo-album-api")
@RequiredArgsConstructor
@Slf4j
public class PhotoAlbumApiController {

	@PutMapping(value = "/create", consumes = { MediaType.ALL_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@Operation(description = "Create a Photo Album for an Order to upload Photos")
	public BaseApiResponse create(String title,Long orderId) {
		log.info("Create Photo Album for uploading photos for the Order with given data.");
		
		return BaseApiResponse.builder().success(Boolean.TRUE).build();
	}
	
	@PostMapping(value = "/complete", consumes = { MediaType.ALL_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@Operation(description = "Upload photos to Photo Album is finished.")
	public BaseApiResponse complete(@PathVariable Long photoAlbumId) {
		log.info("Complete the album for the Photo Order with given data:{}",photoAlbumId);
		
		return BaseApiResponse.builder().success(Boolean.TRUE).build();
	}
	
	@GetMapping(value = "/list", consumes = { MediaType.ALL_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@Operation(description = "List the photo albums with pagination")
	public List<PhotoAlbum> listAll() {
		log.info("List all Photo albums");
		
		return null;
	}
}
