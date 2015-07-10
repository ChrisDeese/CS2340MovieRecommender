package java;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import model.movie.MovieAggregator;
import model.movie.Movie;
import org.junit.Assert;

/**
 *
 * @author chris deese
 */
public class RecommendTest {
    
    private MovieAggregator moveAg;
    
    public RecommendTest() {
    }
    
    @Before
    public void setUp() {
        moveAg = new MovieAggregator();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testNormalRecommendation() {
        List<Movie> list = moveAg.makeRecommendations();
        assertEquals("list length should be 1", 1, list.size());
    }
    
    //test of major recommendation with empty string
    @Test
    public void testMajorRecommendationEmpty() {
        List<Movie> list = moveAg.makeMajorRecommendations("");
        assertEquals("list should be empty", 0, list.size());
    }
    
    //test with a correct major with known answer
    @Test
    public void testMajorRecommendationsCorrect() {
         List<Movie> list = moveAg.makeMajorRecommendations("CS");
         assertEquals("list should be size 2", 2, list.size());
    }
    
    //test with null value
    @Test
    public void testMajorRecomendationsNull() {
        List<Movie> list = moveAg.makeMajorRecommendations(null);
        assertEquals("list should be empty", 0, list.size());
    }
    
    
}
