package com.acme.unified.sample.web.controller;

import com.acme.unified.sample.dal.service.GreetingRepositoryImpl;
import com.acme.unified.sample.web.dto.GreetingV11DTO;
import com.acme.unified.sample.web.dto.GreetingV1DTO;

import com.acme.unified.sample.dal.model.BaseGreeting;
import com.acme.unified.sample.dal.model.Greeting;
import java.util.concurrent.atomic.AtomicLong;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * The Class Greeting Controller.
 *
 * @author Jim.DelloStritto
 * @project template-service-java
 * @created Oct 17, 2020
 * @references
 * @credits pivotal.io
 */
@RestController
public class GreetingController {

	private static final Logger LOGGER = LoggerFactory.getLogger(GreetingController.class);
	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@Autowired
	GreetingRepositoryImpl gRepo;

	@GetMapping("/greeting")
	public GreetingV1DTO greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new GreetingV1DTO(counter.incrementAndGet(), String.format(template, name));
	}
	@GetMapping("/api/1.1/greeting")
	public BaseGreeting greetingGet(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(   null, 
										String.format(template,name),
										counter.incrementAndGet(),
										null,
										null,
										null,
										null,
										false);
	}

	@RequestMapping(value = "/api/1.1/greeting", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public BaseGreeting greetingPost(@RequestBody @Valid GreetingV11DTO greetingV11DTO) {
				
		LOGGER.info("ACME_SAMPLE: Conversation controller method `postGreeting` called: {}", greetingV11DTO.toString());
		
		Greeting _gIn = new Greeting(   null, 
										String.format(template,greetingV11DTO.getGreeting() ),
										counter.incrementAndGet(),
										greetingV11DTO.getCompanyID(),
										greetingV11DTO.getDivisionID(),
										null,
										null,
										false);

		Greeting _gOut = gRepo.createGreeting(_gIn);
		return _gOut;
	}
}
