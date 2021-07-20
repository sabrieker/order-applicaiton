package com.boom.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.boom.entity.Photographer;
import com.boom.service.photographer.PhotographerQueryService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "api/photographer", produces = { MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8" }, consumes = {
		MediaType.APPLICATION_JSON_VALUE})
@OpenAPIDefinition(
        info = @Info(
                title = "Photographer API",
                version = "0.0.1",
                description = "This API provides resources for Photographer Data",
                license = @License(name = "Apache 2.0", url = "https://foo.bar"),
                contact = @Contact(url = "https://sample-server.com", name = "Sabri", email = "sabrieker@mail.com")
        )
)
@Tag(name = "photographer-api")
@RequiredArgsConstructor
@Slf4j
public class PhotographerApiController {

	private final PhotographerQueryService photographerQueryService;
	
	@GetMapping(value = "/list", consumes = { MediaType.ALL_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@Operation(description = "List all the Photographers via pagination.")
	public List<Photographer> list( 
			@RequestParam(defaultValue = "0") Integer pageNo, 
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "name") String sortBy) {
		log.info("Get the details of the Order");
		
		return photographerQueryService.findAll(pageNo,pageSize,sortBy);
	}
}
