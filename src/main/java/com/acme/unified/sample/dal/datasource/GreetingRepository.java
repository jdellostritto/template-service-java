package com.acme.unified.sample.dal.datasource;

import java.util.UUID;
import com.acme.unified.sample.dal.model.Greeting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GreetingRepository extends JpaRepository<Greeting,String> {
 
    
}
