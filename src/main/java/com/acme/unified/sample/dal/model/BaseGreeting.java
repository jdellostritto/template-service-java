package com.acme.unified.sample.dal.model;

import java.io.Serializable;
import java.util.UUID;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.joda.time.DateTime;

public class BaseGreeting  implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	protected UUID greetingID;
	protected String greeting;
	protected Long counter;
	//...
	protected UUID companyID;
	protected UUID divisionID;
	protected DateTime created;
	protected DateTime updated;
	protected Boolean archived;

	/**
	 * GreetingID
	 * @return
	 */
	public UUID getGreetingID() {
		return greetingID;
	}

	public void setGreetingID(UUID _greetingID) {
		this.greetingID = _greetingID;
	}
	/**
	 * Greeting
	 * @return
	 */
	public String getGreeting() {
		return greeting;
	}

	public void setGreeting(String _greeting) {
		this.greeting = _greeting;
	}


	/**
	 * counter
	 * @return
	 */
	public Long getCounter() {
		return counter;
	}
	public void setCounter(Long _counter) {
		this.counter = _counter;
	}


	/**
	 * Company
	 * @return
	 */
	public UUID getCompanyID() {
		return companyID;
	}
	public void setCompanyID(UUID _companyID) {
		this.companyID = _companyID;
	}
	
	/**
	 * Division
	 * @return
	 */
	public UUID getDivisionID() {
		return divisionID;
	}
	public void setDivisionID(UUID _divisionID) {
		this.divisionID = _divisionID;
	}

	/**
	 * Created Date
	 * @return
	 */
	public DateTime getCreated() {
		return created;
	}

	public void setCreated(DateTime created) {
		this.created = created;
	}

	/**
	 * Updated
	 * @return
	 */
	public DateTime getUpdated() {
		return updated;
	}

	public void setUpdated(DateTime updated) {
		this.updated = updated;
	}

	/**
	 * Archived
	 * @return
	 */
	public Boolean getArchived() {
		return archived;
	}

	public void setArchived(Boolean archived) {
		this.archived = archived;
	}

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public BaseGreeting() {
		// nothing to do
	}

	/**
	 * 
	 */
	public BaseGreeting(UUID greetingID, 
						String greeting, 
						Long counter, 
						UUID companyID, 
						UUID divisionID, 
						DateTime created, 
						DateTime updated, 
						Boolean archived) {
		super();
		this.greetingID = greetingID;
		this.greeting = greeting;
		this.counter = counter;
		this.companyID = companyID;
		this.divisionID = divisionID;
		this.created = created;
		this.updated = updated;
		this.archived = archived;
		
		
	}
	public BaseGreeting(UUID greetingID) {
		super();
		this.greetingID = greetingID;
		
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(greetingID).append(greeting).append(counter).append(companyID).append(divisionID).append(created).append(updated).append(archived)
				.toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}

		if ((other instanceof BaseGreeting) == false) {
			return false;
		}

		BaseGreeting rhs = ((BaseGreeting) other);
		return new EqualsBuilder()
				.append(greetingID,rhs.greetingID)
				.append(greeting,rhs.greeting)
				.append(counter,rhs.counter)
				.append(companyID,rhs.companyID)
				.append(divisionID,rhs.divisionID)
				.append(created,rhs.created)
				.append(updated,rhs.updated)
				.append(archived,rhs.archived)
				.isEquals();
	}
}
