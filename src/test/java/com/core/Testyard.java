package com.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class Testyard {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EngService service;

	@Test
	public void shouldReturnDefaultMessage() throws Exception {
		when(service.doReactTest()).thenReturn("Hello Mock");
		this.mockMvc
				.perform(get("/")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Hello World")));
	}

	@Test
	public void test1() {
		/*
		assertThat(this.service
				.doReactTest()
				.contains("Hello World");
		*/
	}

	@Test
	public void test2() {
		/*
		pageService.findAllOrderedByNameDescending().forEach(
				(it)->{ log.info(String.format("DBItem1 ID: %s, NAME: %s<br>", it.getId(), it.getName())); }
		);
		*/
	}

}
