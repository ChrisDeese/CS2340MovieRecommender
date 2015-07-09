
import Model.User.UserData;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.json.simple.JSONObject;

/**
 * @author jacob
 */
public class Movie {
    private JSONObject json;
    private String title;
    private String Id;
    private int rating;
    private int movieId;
    private int ratingTotal;
    private int ratingCount;
    private String major;
    
    private static SessionFactory factory2;

    //TODO
    //Cast
    //Reviews
    //Clips

    public Movie() {
        
    }
    
    /**
     * Creates a new instance of a Movie
     * @param json
     */
    public Movie(JSONObject json) {
        this.json = json;
        this.title = (String) json.get("title");
        this.Id = (String) json.get("id");
    }
    
    public void rateMovie(int i) {
        this.setRatingCount(this.getRatingCount() + 1);
        this.setRatingTotal(this.getRatingTotal() + i);
        this.setRating(this.getRatingTotal() / this.getRatingCount());
        try {
            factory2 = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) { 
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex); 
        }
        System.out.println(this.movieId);
        Session session = factory2.openSession();
        Query query = session.createQuery("from Movie where id = '" + this.Id + "'");
        List<Movie> movs = query.list();
        System.out.println(movs.size());
        Transaction tx = null;
        Movie mov = movs.get(0);
        mov.setRatingCount(mov.getRatingCount() + 1);
        mov.setRatingTotal(mov.getRatingTotal() + i);
        mov.setRating(mov.getRatingTotal() / mov.getRatingCount());
        session.save(mov);
        tx.commit();
    }

    /**
     * Get total from all ratings
     * @return 
     */
    public int getRatingTotal() {
        return this.ratingTotal;
    }
    
    public String getMajor() {
        
        return this.major;
    }
    
    public void setMajor(String s) {
        this.major = s;
    }
    
    /**
     * Set total of ratings
     * @param num 
     */
    public void setRatingTotal(int num) {
        this.ratingTotal = num;
    }
    
    /**
     * Get number of times movie has been rated
     * @return 
     */
    public int getRatingCount() {
        return this.ratingCount;
    }
    
    public void setTitle(String s) {
        this.title = s;
    }
    
    /**
     * Set the number of times this movie has been rated
     * @param num 
     */
    public void setRatingCount(int num) {
        this.ratingCount = num;
    }
    
    
    /**
     * Get database Id for the movie
     * @return 
     */
    public int getMovieId() {
        return this.movieId;
    }
    
    /**
     * Set the database id for the movie (not used)
     * @param Id 
     */
    public void setMovieId(int Id) {
        this.movieId = Id;
    }
    
    /**
     * Gets the rotten tomatoes ID of this movie
     * @return id
     */
    public String getId() {
        return this.Id;
    }

    public void setId(String id) {
        this.Id = id;
    }
    
    /**
     * Gets the title of this movie
     * @return title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Gets the MPAA rating of this movie
     * @return mpaa_rating
     */
    public String getMPAA() {
        return (String) json.get("mpaa_rating");
    }

    /**
     * Gets the theater release date of this movie
     * @return release_date_theater
     */
    public String getReleaseT() {
        return (String) json.get("release_dates.theater");
    }

    /**
     * Gets the dvd release date of this movie
     * @return release_date_dvd
     */
    public String getReleaseD() {
        return (String) json.get("release_dates.dvd");
    }

    /**
     * Gets the synopsis of this movie
     * @return synopsis
     */
    public String getSynopsis() {
        //System.out.print((String) json.get("synopsis"));
        return (String) json.get("synopsis");
    }

    /**
     * Gets the director of this movie
     * @return director
     */
    public String getDirector() {
        return (String) json.get("director");
    }

    /**
     * Gets the studio of this movie
     * @return studio
     */
    public String getStudio() {
        return (String) json.get("studio");
    }

    /**
     * Gets the runtime of this movie
     * @return runtime
     */
    public long getRuntime() {
        return (long) json.get("runtime");
    }

    /**
     * Gets the critic's consensus of this movie
     * @return critics_consensus
     */
    public String getConsensus() {
        return (String) json.get("critics_consensus");
    }

    /**
     * Gets the critic's rating of this movie
     * @return critics_rating
     */
    public String getCRating() {
        return (String) json.get("critics_rating");
    }

    /**
     * Gets the audience's rating of this movie
     * @return audience_rating
     */
    public String getARating() {
        return (String) json.get("audience_rating");
    }

    /**
     * Gets the critic's score of this movie
     * @return critics_score
     */
    public int getCScore() {
        return (int) json.get("critics_score");
    }

    /**
     * Gets the audience's score of this movie
     * @return audience_score
     */
    public String getAScore() {
        return (String) json.get("audience_score");
    }

    /**
     * Gets the thumbnail of the movie's poster
     * @return thumbnail
     */
    public String getThumbnail() {
        //System.out.print((String) json.get("thumbnail"));
        return (String) json.get("thumbnail");
    }

    //TODO
    //Figure out what these are

    /**
     * Gets the profile of the movie's poster
     * @return profile
     */
    //public String getProfile() {
    //    return profile;
    //}

    /**
     * Gets the thumbnail of the movie's poster
     * @return thumbnail
     */
    //public String getDetailed() {
    //    return detailed;
    //}

    /**
     * Gets the original image of the movie's poster
     * @return original
     */
    public String getOriginal() {
        return (String) json.get("original");
    }
    
    public int getRating() {
        return this.rating;
    }
    
    public void setRating(int rating) {
        this.rating=rating;
    }
    
}
