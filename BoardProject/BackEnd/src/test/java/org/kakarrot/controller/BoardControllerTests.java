package org.kakarrot.controller;

import com.google.gson.Gson;
import lombok.extern.log4j.Log4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kakarrot.config.RootConfig;
import org.kakarrot.config.ServletConfig;
import org.kakarrot.dto.ScrollDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {RootConfig.class, ServletConfig.class})
public class BoardControllerTests {

    @Autowired
    private WebApplicationContext ctx;

    private MockMvc mockMvc;

    @Before
    public void reay() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    @Test
    public void testList() throws Exception {

        ScrollDTO dto = new ScrollDTO();

        String jsonObj = new Gson().toJson(dto);

        log.info(jsonObj);

        log.info(
                mockMvc.perform(
                        post("/board/list")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObj)
                ).andExpect(status().is(200))
        );
    }

}
