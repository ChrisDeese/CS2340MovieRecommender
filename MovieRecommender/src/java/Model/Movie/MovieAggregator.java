package Model.Movie;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Class to aggregate movies for recommendations
 *
 * @author chris
 */
@ManagedBean(name = "movieAggregator")
@ApplicationScoped
public class MovieAggregator {

    private static SessionFactory factory;
    private Map<String, Movie> movies = new HashMap<>();
    private List<Movie> recommendations;

    /**
     * Constructor for MovieAggregator
     */
    public MovieAggregator() {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        createMovieMap();
    }

    /**
     * finds a movie based on its movieId
     *
     * @param movieId
     * @return
     */
    Movie find(String movieId) {
        return movies.get(movieId);
    }

    /**
     * Creates map of the Movies in the database
     */
    public void createMovieMap() {
        Session session = factory.openSession();
        Query query = session.createQuery("from Movie");
        List<Movie> movieList = query.list();
        for (Movie m : movieList) {
            System.out.println(m.getId());

            movies.put(m.getId(), m);
        }
        System.out.println(movies.size());
    }

    /**
     * Creates Recommendations based on highest rated movies
     *
     * @return
     */
    public List makeRecommendations() {
        recommendations = new ArrayList<Movie>();
        for (String s : movies.keySet()) {
            if (movies.get(s).getRating() > 3) {
                recommendations.add(movies.get(s));
            }
        }
        return recommendations;
    }

    /**
     * Creates Recommendations based on major
     *
     * @param major
     * @return
     */
    public List makeMajorRecommendations(String major) {
        recommendations = new ArrayList<Movie>();
        for (String s : movies.keySet()) {
            //System.out.println(movies.get(s).getMajor());
            if (movies.get(s).getMajor() != null) {
                if (movies.get(s).getMajor().equals(major)) {
                    recommendations.add(movies.get(s));
                }
            }
        }
        return recommendations;
    }

    /**
     * go back to previous page (fixes session)
     *
     * @return
     */
    public String goBack() {
        FacesContext.getCurrentInstance()
                .getExternalContext().invalidateSession();
        return "MovieSearch.xhtml?faces-redirect=true";
    }

}
