package com.acme.unified.sample.dal.service;

import com.acme.unified.sample.dal.datasource.GreetingRepository;
import com.acme.unified.sample.dal.model.Greeting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class GreetingRepositoryImpl {
    
    private static final Logger LOGGER = LoggerFactory.getLogger("sample.log");

    @Autowired
    GreetingRepository gRepo;

    public Greeting createGreeting(final Greeting greeting){
 
        LOGGER.info("ACME_SAMPLE: createGreeting IN:\n {}", greeting.toString() );
        // set the create time stamp and save
        greeting.setCreated(DateTime.now(DateTimeZone.UTC));
        greeting.setUpdated(DateTime.now(DateTimeZone.UTC));

        Greeting greetingSaved = gRepo.save(greeting);
        
        LOGGER.info("ACME_SAMPLE: createGreeting OUT:\n {}", greetingSaved.toString() );
        return greetingSaved;               
}


}