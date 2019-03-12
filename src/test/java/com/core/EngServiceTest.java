package com.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebMvcTest(EngService.class)
public class EngServiceTest {

	@Test
	public void test1() {
//		pageService.addOneItem1(count, "NAME" + count);
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
