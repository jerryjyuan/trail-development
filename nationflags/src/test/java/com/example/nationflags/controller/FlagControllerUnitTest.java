package com.example.nationflags.controller;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.http.ResponseEntity;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.HttpMethod;

import com.example.nationflags.model.Flag;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.core.AnyOf.anyOf;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.StringEndsWith.endsWith;

import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(FlagController.class)
public class FlagControllerUnitTest {

    @Autowired
	  private MockMvc mvc;
    
    @Autowired
    private FlagRepository flagRepo;
    
    @Test
	  public void testGetAllFlags() throws Exception {        
        List<Flag> expectedFlagsList = new ArrayList<>();
		    given(this.flagRepo.findAll().willReturn(nexpectedFlagsList);
        ObjectMapper mapper = new ObjectMapper();
		    this.mvc.perform(get("/getall").accept(MediaType.TEXT_PLAIN))
				.andExpect(status().isOk())
                .andExpect(content.json(mapper.writeValueAsString(expectedFlagsList)));
	  }
  
    @Test
	  public void testGetFlagByNation() {
        Flag expectedFlag = new Flag();
		    given(this.flagRepo.findFlagByNation("USA")).willReturn(expectedFlag);
		    this.mvc.perform(get("/getflag/USA").accept(MediaType.TEXT_PLAIN))
				.andExpect(status().isOk())
                .andExpect(content().string(stringEndsWith("USA.png")));
	}

}
