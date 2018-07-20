package org.haiti.chidren.web.rest;

import org.haiti.chidren.ChidrenApp;
import org.haiti.chidren.service.ChidService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/**
 * Test class for the NeedsResource REST controller.
 *
 * @see NeedsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChidrenApp.class)
public class NeedsResourceIntTest {

    private MockMvc restMockMvc;
    
    @Autowired
    private ChidService chidService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        NeedsResource needsResource = new NeedsResource(chidService);
        restMockMvc = MockMvcBuilders
            .standaloneSetup(needsResource)
            .build();
    }

    /**
    * Test getAllChids
    */
    @Test
    public void testGetAllChids() throws Exception {
        restMockMvc.perform(get("/api/needs/get-all-chids"))
            .andExpect(status().isOk());
    }

}
