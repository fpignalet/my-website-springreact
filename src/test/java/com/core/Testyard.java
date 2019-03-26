package com.core;

import com.core.eng.EngService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class Testyard {

	/**
	 *
	 */
	@Autowired
	private MockMvc mockMvc;

	/**
	 *
	 */
	@MockBean
	private EngService service;

	/**
	 * @throws Exception
	 */
	@Test
	public void test1() throws Exception {
		mockMvc
			.perform(get("/"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(
				content().string(
					containsString("FROM MODEL: TOTO 1!")
				)
			);
	}

	/**
	 *
	 */
	@Test
	public void test2() {
		when(service.doReactTest())
			.thenReturn("Hello Mock 2");
	}

	/**
	 *
	 */
	@Test
	public void test3() {
		service.addOneItem1(0, "TEST");
		service.findAllOrderedByNameDescending().forEach(
			(it)->{ log.info(String.format("DBItem1 ID: %s, NAME: %s<br>", it.getId(), it.getName())); }
		);
	}

	/**
	 *
	 */
	private static Logger log = LoggerFactory.getLogger(EngService.class);

}
