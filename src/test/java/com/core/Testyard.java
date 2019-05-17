package com.core;

import com.core.eng.EngServiceDB;
import com.core.eng.EngServiceJSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
//@AutoConfigureMockMvc
public class Testyard {

	/**
	 *
	 */
/*
	@Autowired
	private MockMvc mockMvc;
*/

	/**
	 *
	 */
	@Autowired
	private EngServiceDB serviceDB;

	/**
	 *
	 */
	@Autowired
	private EngServiceJSON serviceJSON;

	/**
	 * @throws Exception
	 */
	@Test
	public void testDB1() throws Exception {
/*
	    serviceDB.cleanAllItems();
		serviceDB.addOneItem1(0, "TEST0");
		final List<DBItem1> result = serviceDB.findById(0);
		assertNotNull(result);
		assertNotNull(result.size());
        assertEquals(1, result.size());
		assertEquals(0, result.get(0).getId());
		assertEquals("TEST0", result.get(0).getName());
*/
	}

	/**
	 *
	 */
	@Test
	public void testDB2() {
/*
        serviceDB.cleanAllItems();
        serviceDB.addOneItem1(0, "TEST0");
        serviceDB.addOneItem1(1, "TEST1");
        final List<DBItem1> result = serviceDB.findAllItems();
        assertNotNull(result);
        assertNotNull(result.size());
        assertEquals(2, result.size());
        assertEquals(0, result.get(0).getId());
        assertEquals("TEST0", result.get(0).getName());
        assertEquals(1, result.get(1).getId());
        assertEquals("TEST1", result.get(1).getName());
*/
	}

	/**
	 *
	 */
	@Test
	public void testDB3() {
/*
        serviceDB.cleanAllItems();
        serviceDB.addOneItem1(0, "TEST0");
        serviceDB.addOneItem1(1, "TEST1");
        serviceDB.addOneItem1(2, "TEST2");
        final List<DBItem1> result = serviceDB.findAllOrderedByNameDescending();
        assertNotNull(result);
        assertNotNull(result.size());
        assertEquals(3, result.size());
        assertEquals(2, result.get(2).getId());
        assertEquals("TEST2", result.get(2).getName());
        assertEquals(1, result.get(1).getId());
        assertEquals("TEST1", result.get(1).getName());
        assertEquals(0, result.get(0).getId());
        assertEquals("TEST0", result.get(0).getName());
*/
	}

    /**
     *
     */
    @Test
	public void testINTG1() throws Exception {
/*
		mockMvc
			.perform(get("/"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(
					content().string(
							containsString("FROM MODEL: MAIN CONTROLER!")
					)
			);
*/
	}

	/**
	 *
	 */
	private static Logger log = LoggerFactory.getLogger(EngServiceDB.class);

}
