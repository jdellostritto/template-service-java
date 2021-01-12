package com.acme.unified.sample.web.controller;

import com.acme.unified.sample.dal.service.GreetingRepositoryImpl;
import com.acme.unified.sample.web.dto.GreetingDTOV1;
import com.acme.unified.sample.web.dto.GreetingDTO;

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
@RequestMapping(value = "/api/1.0/greeting")
public class GreetingController {

	private static final Logger LOGGER = LoggerFactory.getLogger(GreetingControllerSimple.class);
	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@Autowired
	GreetingRepositoryImpl gRepo;
	
	
	/** 
	 * @param "name"
	 * @param name
	 * @return BaseGreeting
	 */
	@RequestMapping(method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
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

	
	/** 
	 * @param greetingV11DTO
	 * @return BaseGreeting
	 */
	@RequestMapping(method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public BaseGreeting greetingPost(@RequestBody @Valid GreetingDTOV1 greetingDTOV1) {
				
		LOGGER.info("ACME_SAMPLE: Conversation controller method `postGreeting` called: {}", greetingDTOV1.toString());
		
		Greeting _gIn = new Greeting(   null, 
										String.format(template,greetingDTOV1.getGreeting() ),
										counter.incrementAndGet(),
										greetingDTOV1.getCompanyID(),
										greetingDTOV1.getDivisionID(),
										null,
										null,
										false);

		Greeting _gOut = gRepo.createGreeting(_gIn);
		return _gOut;
	}
}
