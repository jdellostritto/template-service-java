package com.acme.unified.sample.dal.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@Table(name = "\"greeting\"")
public class Greeting extends BaseGreeting {

	private final static long serialVersionUID = 1L;

	@Id
	@GeneratedValue
    @Column(name = "\"greetingID\"")
    public UUID getGreetingID() {
		return greetingID;
    }

    @Column(name = "\"companyID\"")
	public UUID getCompanyID() {
		return companyID;
	}
    
    @Column(name = "\"divisionID\"")
	public UUID getDivisionID() {
		return divisionID;
	}
	
	@Column(name = "\"greeting\"")
	public String getGreeting() {
		return greeting;
	}
    
    @Column(name = "\"counter\"")
	public Long getCounter() {
		return counter;
    }
    
    @Column(name = "\"created\"", nullable = false, updatable = false)
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	public DateTime getCreated() {
		return created;
	}
	
	@Column(name = "\"updated\"", nullable = false, updatable = false)
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	public DateTime getUpdated() {
		return updated;
	}
		
	@Column(name = "\"archived\"")
	public Boolean getArchived() {
		return archived;
	}

    public Greeting() {
		super();
    }
    
    public Greeting(UUID greetingID, String greeting, Long counter, UUID companyID, UUID divisionID, DateTime created, DateTime updated,Boolean archived) {
		super(greetingID, greeting, counter, companyID, divisionID, created, updated, archived);
	}
	public Greeting(UUID greetingID) {
		super(greetingID);
		
		
	}
}
