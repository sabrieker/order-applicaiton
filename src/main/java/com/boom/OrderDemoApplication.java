package com.boom;

import java.util.Collections;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.boom.filter.CorrelationIdInterceptor;


@SpringBootApplication
public class OrderDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderDemoApplication.class, args);
	}

	@Bean
    public RestTemplate restTemplate() {
		RestTemplate template = new RestTemplate();
		template.setInterceptors(Collections.singletonList(new CorrelationIdInterceptor()));
        return template;
    }
	
	
	@Bean	
    public ModelMapper initModelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return mapper;
    }
}
