package com.acme.unified.sample.web.dto;

/**
 * The Class Greeting.
 *
 * @author Jim.DelloStritto
 * @project template-service-java
 * @created Oct 17, 2020
 * @references
 * @credits pivotal.io
 */
public class GreetingDTO {

	private final long id;
	private final String content;

	public GreetingDTO(long id, String content) {
		this.id = id;
		this.content = content;
	}

	/** 
	 * @return long
	 */
	public long getId() {
		return id;
	}
	
	/** 
	 * @return String
	 */
	public String getContent() {
		return content;
	}
}