package ru.javawebinar.topjava.web;

import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * ResourceControllerTest.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 7/26/2020
 */
public class ResourceControllerTest extends AbstractControllerTest {
    @Test
    void whenTestCssJpsIsOk() throws Exception {
        perform(get("/resource/css/style.css"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string(""));
    }

}
