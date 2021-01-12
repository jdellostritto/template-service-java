/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *	  https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.acme.unified.sample;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.acme.unified.sample.dal.model.BaseGreeting;
import com.acme.unified.sample.web.dto.GreetingDTOV1;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import org.junit.Assert;

@SpringBootTest
@AutoConfigureMockMvc
public class GreetingControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objMap;

	@Test
	public void noParamGreetingShouldReturnDefaultMessageV1() throws Exception {

		this.mockMvc.perform(get("/greeting")).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.content").value("Hello, World!"));
	}

	@Test
	public void paramGreetingShouldReturnTailoredMessageV1() throws Exception {

		this.mockMvc.perform(get("/greeting").param("name", "Spring Community"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.content").value("Hello, Spring Community!"));
	}


	@Test
	public void noParamGreetingShouldReturnDefaultMessage() throws Exception {

		ResultActions ra = this.mockMvc.perform(get("/api/1.0/greeting")
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.accept(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isOk())
		.andDo(MockMvcResultHandlers.print());
		
		MvcResult result = ra.andReturn();
		String contentAsString = result.getResponse().getContentAsString();

		BaseGreeting g = objMap.readValue(contentAsString, BaseGreeting.class);

		Assert.assertEquals("Hello, World!", g.getGreeting());

				
	}

	@Test
	public void sendGreetingShouldReturnWithSentGreeting() throws Exception {

		GreetingDTOV1 gDTO = new GreetingDTOV1(UUID.fromString("00000000-0000-0000-0000-00000000000A"),UUID.fromString("00000000-0000-0000-0000-000000000001"), "Player One");
		ObjectMapper mapper = new ObjectMapper();
		String  jsonInString = mapper.writeValueAsString(gDTO);

		ResultActions ra = mockMvc.perform(post("/api/1.0/greeting")
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.content(jsonInString)
		.accept(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isCreated())
		.andDo(MockMvcResultHandlers.print());
		
		MvcResult result = ra.andReturn();
		String contentAsString = result.getResponse().getContentAsString();

		BaseGreeting g = objMap.readValue(contentAsString, BaseGreeting.class);

		Assert.assertEquals("Hello, Player One!", g.getGreeting());

				
	}



}
