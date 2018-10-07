package com.example.nationflags;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FlagControllerTest {

    @Autowired
	private TestRestTemplate restTemplate;
    
    @Autowired
    private ServletContext servletContext;
    
	@Test
	public void testGetAllFlags() {        
        String url = servletContext.getContextPath() + "/getall";
        ResponseEntity<List<Flag>> allFlags = restTemplate.exchange(url, HttpMethod.GET, null, Flag.class);
        assertTrue(allFlags != null && allFlags.getBody().size() == 26);
        allFlags.getBody().stream().forEach(f->{
            assertFlag(f);
        });
	}

    private void assertFlag(Flag flag) {
        if (flag == null) {
            return;   
        }
        switch(flag.getContinent()) {
            case "Africa":
                assertThat(flag.getNation(), anyOf(Arrays.asList(isEqual("Nigeria"), isEqual("Ethiopia"),
                                                                 isEqual("Egypt"), isEqual("DR Congo"), 
                                                                 isEqual("South Africa")))
                assertThat(flag.getFlag(), anyOf(Arrays.asList(stringEndsWith("Nigeria.png"), stringEndsWith("Ethiopia.png"),
                                                                 stringEndsWith("Egypt.png"), stringEndsWith("DR Congo.png"), 
                                                                 stringEndsWith("South Africa.png")))
                break;
            case "America":
                assertThat(flag.getNation(), anyOf(Arrays.asList(isEqual("USA"), isEqual("Brazil"),
                                                                 isEqual("Mexico"), isEqual("Colombia"), 
                                                                 isEqual("Argentina")))
                assertThat(flag.getFlag(), anyOf(Arrays.asList(stringEndsWith("USA.png"), stringEndsWith("Brazil.png"),
                                                                 stringEndsWith("Mexico.png"), stringEndsWith("Colombia.png"), 
                                                                 stringEndsWith("Argentina.png")))
                break;
            case "Asia":
                assertThat(flag.getNation(), anyOf(Arrays.asList(isEqual("Japan"), isEqual("Taiwan"), isEqual("India"),
                                                                 isEqual("Indonesia"), isEqual("Pakistan"), 
                                                                 isEqual("Bangladesh")))
                assertThat(flag.getFlag(), anyOf(Arrays.asList(stringEndsWith("Japan.png"), stringEndsWith("Taiwan.png"), 
							         stringEndsWith("India.png"), stringEndsWith("Indonesia.png"), 
							         stringEndsWith("Pakistan.png"), stringEndsWith("Bangladesh.png")))
                break;
            case "Europe":
                assertThat(flag.getNation(), anyOf(Arrays.asList(isEqual("Russia"), isEqual("Germany"),
                                                                 isEqual("UK"), isEqual("France"), 
                                                                 isEqual("Italy")))
                assertThat(flag.getFlag(), anyOf(Arrays.asList(stringEndsWith("Russia.png"), stringEndsWith("Germany.png"),
                                                                 stringEndsWith("UK.png"), stringEndsWith("France.png"), 
                                                                 stringEndsWith("Italy.png")))
                break;
            case "Oceania":
                assertThat(flag.getNation(), anyOf(Arrays.asList(isEqual("Australia"), isEqual("Papua New Guinea"),
                                                                 isEqual("New Zealand"), isEqual("Fiji"), 
                                                                 isEqual("Solomon Islands")))
                assertThat(flag.getFlag(), anyOf(Arrays.asList(isEqual("Australia.png"), isEqual("Papua New Guinea.png"),
                                                                 isEqual("New Zealand.png"), isEqual("Fiji.png"), 
                                                                 isEqual("Solomon Islands.png")))
                break;
            default:
                break;
        }
    }
                   
    @Test
	public void testGetFlagByNation() {
        String url = servletContext.getContextPath() + "/getflag/USA";
        ResponseEntity<Flag> testFlag = restTemplate.exchange(url, HttpMethod.GET, null, Flag.class);
        assertNotNUll(testFlag);
        assertEquals(testFlag.getNation(), "USA");
        assertTrue(testFlag.getFlag().contains("USA.png"));
	}

}
