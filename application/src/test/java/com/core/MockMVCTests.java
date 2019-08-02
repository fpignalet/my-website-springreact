package com.core;

import com.core.ctrl.EHTMLPages;
import com.core.ctrl.impl.ControllerAdBook;
import com.core.data.IDBContactDAO;
import com.core.eng.impl.EngServiceDBABook;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@RunWith(SpringRunner.class)
@WebMvcTest
@ContextConfiguration(classes = CoreApplication.class)
public class MockMVCTests {

    /*
    UNDER CONSTRUCTION
     */

    @Autowired
    private MockMvc mvc;

    @Before
    public void setup(){
    }

    @Test
    public void testMockDefaultPage() throws Exception {
        this.mvc.perform(get("/"))
            .andExpect(status().isOk())
            .andExpect(view().name(EHTMLPages.DEFAULT.getName()))
            .andExpect(content().string(containsString("FRONTEND_DEFAULT")))
            .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testMockReactPage() throws Exception {
        this.mvc.perform(get("/entryreact"))
            .andExpect(status().isOk())
            .andExpect(view().name(EHTMLPages.DEFAULT.getName()))
            .andExpect(content().string(containsString("FRONTEND_REACT")))
            .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testMockAngularPage() throws Exception {
        this.mvc.perform(get("/entryangular"))
            .andExpect(status().isOk())
            .andExpect(view().name(EHTMLPages.ANGULAR.getName()))
            .andExpect(content().string(containsString("FRONTEND_ANGULAR")))
            .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testMockAddressBookPage() throws Exception {
        this.mvc.perform(get("/entryaddressbook"))
            .andExpect(status().isOk())
            .andExpect(view().name(EHTMLPages.ADBOOK.getName()))
            .andExpect(content().string(containsString("FRONTEND_ADDRESSBOOK")))
            .andDo(MockMvcResultHandlers.print());
    }

    @Autowired
    private ControllerAdBook ctlADBook;

    @Autowired
    private EngServiceDBABook svcADBook;

    @Autowired
    private IDBContactDAO repoABook;

}
