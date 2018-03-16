package us.hyalen.trippmember.core.member;

import static org.junit.Assert.*;

import static org.hamcrest.Matchers.containsString;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class MemberIntegrationTest {
	static final String versionMimeType = "application/us.hyalen.member.v1+json";
	static final String baseUri = "/api";
	
	@Autowired
    private MockMvc mockMvc;
	
	@Test
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/api/home")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello TRIPP, this is Hyalen ...")));
    }
	
	@Test
	public void shouldReturnAccessDeny() throws Exception {
		MockHttpServletRequestBuilder request = get(baseUri + "/home").accept(versionMimeType);
        ResultActions result = mockMvc.perform(request);
        mockMvc.perform(post("").content(""));
        result.andReturn().getResponse().getHeader("Authorization");
        assertEquals("", "");
	}
}
