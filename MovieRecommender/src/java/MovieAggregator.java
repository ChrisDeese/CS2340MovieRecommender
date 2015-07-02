
import Model.User.UserData;
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
 *
 * @author chris
 */
@ManagedBean (name = "movieAggregator")
@ApplicationScoped
public class MovieAggregator {
    
    private static SessionFactory factory;
    private Map<String, Movie> movies = new HashMap<>();
    private List<Movie> Recommendations;
    
    public MovieAggregator() {
        try{
         factory = new Configuration().configure().buildSessionFactory();
      }catch (Throwable ex) { 
         System.err.println("Failed to create sessionFactory object." + ex);
         throw new ExceptionInInitializerError(ex); 
      }
        createMovieMap();
    }
    
    Movie find(String movieId) {
        return movies.get(movieId);
    }
    
    public void createMovieMap() {
        Session session = factory.openSession();
        Query query = session.createQuery("from Movie");
        List<Movie> movieList = query.list();
        for (Movie m :movieList) {
            System.out.println(m.getId());
            
            movies.put(m.getId(), m);
        }
        System.out.println(movies.size());
    }
    
    public List makeRecommendations() {
        Recommendations = new ArrayList<Movie>();
        for (String s: movies.keySet()) {
            if (movies.get(s).getRating() > 3) {
                Recommendations.add(movies.get(s));
            }
        }
        return Recommendations;
    }
    
    public List makeMajorRecommendations(String major) {
       Recommendations = new ArrayList<Movie>();
        for (String s: movies.keySet()) {
            if (movies.get(s).getMajor().equals(major)) {
                Recommendations.add(movies.get(s));
            }
        }
        return Recommendations; 
    }
    
    public String goBack() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "welcomePage.xhtml?faces-redirect=true";
    }
    
}
