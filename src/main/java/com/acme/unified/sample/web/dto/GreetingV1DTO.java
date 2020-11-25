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
public class GreetingV1DTO {

	private final long id;
	private final String content;

	public GreetingV1DTO(long id, String content) {
		this.id = id;
		this.content = content;
	}

	public long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}
}