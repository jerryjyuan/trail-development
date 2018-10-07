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

import static org.hamcrest.core.AnyOf.anyOf;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.StringEndsWith.endsWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class FlagControllerTest {

    @Autowired
	private TestRestTemplate restTemplate;

    @Test
    public void testGetAllFlags() {        
        String url = "/getall";
        ResponseEntity<List<Flag>> allFlags = restTemplate.exchange(url, HttpMethod.GET, null, Flag.class);
        assertTrue(allFlags != null && allFlags.getBody().size() == 25);
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
                assertThat(flag.getNation(), anyOf(Arrays.asList(equalTo("Nigeria"), equalTo("Ethiopia"),
                                                                 equalTo("Egypt"), equalTo("DR Congo"), 
                                                                 equalTo("South Africa")))
                assertThat(flag.getFlag(), anyOf(Arrays.asList(endsWith("Nigeria.png"), endsWith("Ethiopia.png"),
                                                                 endsWith("Egypt.png"), endsWith("DR Congo.png"), 
                                                                 endsWith("South Africa.png")))
                break;
            case "America":
                assertThat(flag.getNation(), anyOf(Arrays.asList(equalTo("USA"), equalTo("Brazil"),
                                                                 equalTo("Mexico"), equalTo("Colombia"), 
                                                                 equalTo("Argentina")))
                assertThat(flag.getFlag(), anyOf(Arrays.asList(endsWith("USA.png"), endsWith("Brazil.png"),
                                                                 endsWith("Mexico.png"), endsWith("Colombia.png"), 
                                                                 endsWith("Argentina.png")))
                break;
            case "Asia":
                assertThat(flag.getNation(), anyOf(Arrays.asList(equalTo("China"), equalTo("India"),
                                                                 equalTo("Indonesia"), equalTo("Pakistan"), 
                                                                 equalTo("Bangladesh")))
                assertThat(flag.getFlag(), anyOf(Arrays.asList(endsWith("China.png"), endsWith("India.png"),
                                                                 endsWith("Indonesia.png"), endsWith("Pakistan.png"), 
                                                                 endsWith("Bangladesh.png")))
                break;
            case "Europe":
                assertThat(flag.getNation(), anyOf(Arrays.asList(equalTo("Russia"), equalTo("Germany"),
                                                                 equalTo("UK"), equalTo("France"), 
                                                                 equalTo("Italy")))
                assertThat(flag.getFlag(), anyOf(Arrays.asList(endsWith("Russia.png"), endsWith("Germany.png"),
                                                                 endsWith("UK.png"), endsWith("France.png"), 
                                                                 endsWith("Italy.png")))
                break;
            case "Oceania":
                assertThat(flag.getNation(), anyOf(Arrays.asList(equalTo("Australia"), equalTo("Papua New Guinea"),
                                                                 equalTo("New Zealand"), equalTo("Fiji"), 
                                                                 equalTo("Solomon Islands")))
                assertThat(flag.getFlag(), anyOf(Arrays.asList(endsWith("Australia.png"), endsWith("Papua New Guinea.png"),
                                                                 endsWith("New Zealand.png"), endsWith("Fiji.png"), 
                                                                 endsWith("Solomon Islands.png")))
                break;
            default:
                break;
        }
    }
                   
    @Test
    public void testGetFlagByNation() {
        String url = "/getflag/USA";
        ResponseEntity<Flag> testFlag = restTemplate.exchange(url, HttpMethod.GET, null, Flag.class);
        assertNotNull(testFlag);
        assertEquals(testFlag.getNation(), "USA");
        assertTrue(testFlag.getFlag().contains("USA.png"));
   }

}

