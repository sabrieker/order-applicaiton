package com.boom.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.boom.dto.response.BaseApiResponse;
import com.boom.entity.Photo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "api/photo", produces = { MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8" }, consumes = {
		MediaType.APPLICATION_JSON_VALUE})
@OpenAPIDefinition(
        info = @Info(
                title = "Photo API",
                version = "0.0.1",
                description = "This API provides resources for Photo Data",
                license = @License(name = "Apache 2.0", url = "https://foo.bar"),
                contact = @Contact(url = "https://sample-server.com", name = "Sabri", email = "sabrieker@mail.com")
        )
)
@Tag(name = "photo-api")
@RequiredArgsConstructor
@Slf4j
public class PhotoApiController {

	@PutMapping(value = "/upload/{photoAlbumId}", consumes = { MediaType.ALL_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@Operation(description = "Upload Photo to the specific Photo Album")
	public BaseApiResponse upload(@PathVariable("photoAlbumId") Long photoAlbumId) {
		log.info("Create Order with given data.");
		
		return BaseApiResponse.builder().success(Boolean.TRUE).build();
	}
	
	@GetMapping(value = "/list/{photoAlbumId}", consumes = { MediaType.ALL_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@Operation(description = "List the photos of the given album.")
	public List<Photo> list(@PathVariable("photoAlbumId")  Long photoAlbumId) {
		log.info("Update the status");
		
		return null;
	}
}
