package com.ironmovies.controllers;

import com.ironmovies.models.Movie;
import com.ironmovies.models.ResultsPage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;


@RestController
public class MovieController {
    List<Movie> movies = new ArrayList();
    static final String API_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=be2a38521a7859c95e2d73c48786e4bb";

    @GetMapping("/")
    public String home(){
        return "You have reached the Movie REST API. Try: /api/...";
    }

    @GetMapping("/apitest")
    public List<Movie> apiTest(){
        Movie movie1 = new Movie();
        movie1.setOverview("my overview");
        movie1.setPopularity(99.0);
        movie1.setPosterPath("https://yt3.ggpht.com/-Yai9Hqhjdl8/AAAAAAAAAAI/AAAAAAAAAAA/DdqlORbBBPQ/s900-c-k-no-mo-rj-c0xffffff/photo.jpg");
        movie1.setTitle("my apiTest Title");
        movies.add(movie1);
        return movies;
    }

    @CrossOrigin(origins = "http://www.unrentedforest.surge.sh/")
    @GetMapping(path ="/api/spider-homecoming")
    public Object singleMovieTest(String route) {
        RestTemplate testTemplate = new RestTemplate();
        ResultsPage resultsPage = testTemplate.getForObject(API_URL, ResultsPage.class);
        return resultsPage.getResults().stream()
                .filter(e->e.getTitle()
                        .contains("Spider-Man: Homecoming"))
                .collect(Collectors.toList());
    }

    @CrossOrigin
    @GetMapping(path = "/api/movies")
    public Object getMovies(String route) {
        //TODO: checkfor api key ="abc"
        RestTemplate restTemplate = new RestTemplate();
        ResultsPage resultsPage = restTemplate.getForObject(API_URL, ResultsPage.class);
        return resultsPage;



//
//            model.addAttribute("movies",getMovies(API_URL)
//                    .stream()
//                    .filter(e->e.getTitle().length()>9)
//                    .filter(e->e.getPopularity()>29 && e.getPopularity()<81)
//                    .collect(Collectors.toList()));
//            return "medium-popular-long-name";
//        }
//        @GetMapping(path = "/now-playing")
//        public String nowPlaying (){
//            model.addAttribute("movies",getMovies(API_URL));
//            return "now-playing";
//        }
//
//        public List<Movie> getMovies(String route){
//            RestTemplate restTemplate = new RestTemplate();
//            ResultsPage results  = restTemplate.getForObject(route, ResultsPage.class);
//
//
//            return results.getResults();
//    }
    }
}
