package com.acme.unified.sample.web.dto;
import java.util.UUID;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class Greeting DTO.
 *
 * @author Jim.DelloStritto
 * @project template-service-java
 * @created Nov 20, 2020
 * @references
 * @credits pivotal.io
 */
@Getter
@Setter
public class GreetingDTOV1 {

	@NotNull
	private UUID companyID;
	@NotNull
	private UUID divisionID;
	private String greeting;

	public GreetingDTOV1(@NotNull UUID companyID, @NotNull UUID divisionID, String greeting) {
		this.companyID = companyID;
		this.divisionID = divisionID;
		this.greeting = greeting;
	}
      
}