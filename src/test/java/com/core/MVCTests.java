package com.core;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@Slf4j
@RunWith(SpringRunner.class)
@WebMvcTest
public class MVCTests {

    /*
    UNDER CONSTRUCTION
     */

    @Autowired
    private MockMvc mvc;

    @Before
    public void setup(){
    }

/*
    @Test
    public void testMockDefaultPage() throws Exception {
        this.mvc.perform(get("/"))
            .andExpect(status().isOk())
            .andExpect(content().string("FRONTEND_DEFAULT"));
    }

    @Test
    public void testMockReactPage() throws Exception {
        this.mvc.perform(get("/entryreact"))
            .andExpect(status().isOk())
            .andExpect(content().string("FRONTEND_REACT"));
    }

    @Test
    public void testMockAngularPage() throws Exception {
        this.mvc.perform(get("/entryangular"))
            .andExpect(status().isOk())
            .andExpect(content().string("FRONTEND_ANGULAR"));
    }

    @Test
    public void testMockAddressBookPage() throws Exception {
        this.mvc.perform(get("/entryaddressbook"))
            .andExpect(status().isOk())
            .andExpect(content().string("FRONTEND_ADDRESSBOOK"));
    }
*/
}
