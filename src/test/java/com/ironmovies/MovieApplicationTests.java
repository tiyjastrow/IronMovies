package com.ironmovies;

import static org.assertj.core.api.Assertions.assertThat;

import com.ironmovies.models.Movie;
import com.ironmovies.models.ResultsPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MovieApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void greetingShouldReturnDefaultMessage() throws Exception {
        String homeResult = "You have reached the Movie REST API";
        String result = this.restTemplate.getForObject("http://localhost:" + port + "/",
                String.class);
        assertThat(result).contains(homeResult);
    }

    @Test
    public void apiTestTest() throws Exception {
        ResultsPage result = this.restTemplate.getForObject("http://localhost:" + port + "/apitest",
                ResultsPage.class);
        assertThat(result.getResults().size() > 0);

        Movie movieResult = result.getResults().get(0);
        assertThat(movieResult.getTitle().equals("my apiTest Title"));
    }

}

//    @Test
//    public void testLargeNumbers () throws Exception {
//        System.out.println("Testing large numbers");
//        assertTrue(MethodsClass.containsOne(10000002));
//        assertFalse(MethodsClass.containsOne(986974958));
//    }

