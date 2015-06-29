
import java.io.BufferedReader;
import java.io.Serializable;
import java.util.Iterator;
import java.util.ArrayList;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import static javax.ws.rs.core.HttpHeaders.USER_AGENT;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.json.simple.parser.ParseException;

/**
 * @author jacob
 */
 @ManagedBean
 @SessionScoped
public class Search implements Serializable {
    private JSONParser parser;
    private String input;
    private List<Movie> movies;
    private static SessionFactory factory;

    /**
     * Creates a new instance of a Search
     */
    public Search() {
        parser = new JSONParser();
        try{
         factory = new Configuration().configure().buildSessionFactory();
      }catch (Throwable ex) { 
         System.err.println("Failed to create sessionFactory object." + ex);
         throw new ExceptionInInitializerError(ex); 
      }
    }
    
    /**
     * gets input
     */
    public String getInput() {
        return this.input;
    }
    
    /**
     * Set input
     */
    public void setInput(String input) {
        this.input = input;
    }

    /**
     * Finds a list of movies that match a string passed in
     * @param movie title
     */
    public String find(String movie) throws IOException, Exception {
        URL jsonRequest = null;
        movie = movie.replaceAll(" ", "_");
        try {
            jsonRequest = new URL("http://api.rottentomatoes.com/api/public/v1.0/movies.json?apikey=yedukp76ffytfuy24zsqk7f5&q=" + movie + "&page_limit=20");
        } catch (MalformedURLException ex) {
            Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String response2 = sendGet(jsonRequest);
        //URLConnection connection = jsonRequest.openConnection();
        //connection.setDoOutput(true);
        //Scanner scanner = new Scanner(jsonRequest.openStream());
        //String response2 = scanner.useDelimiter("\\Z").next();
        Object obj = null;
        try {
            obj = parser.parse(response2);
        } catch (ParseException ex) {
            Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
        }
        JSONObject jsonO = (JSONObject) obj;
        JSONArray json = (JSONArray) jsonO.get("movies");

        List<Movie> results = new ArrayList<Movie>();
        for (int c = 1; c < json.size(); c++) {
            JSONObject obj2 = (JSONObject) json.get(c);
            results.add(new Movie(obj2));
        }

        movies = results;
        addMovies();
        return "SearchResults";
    }

    /**
     * Sets the movie list to a list of the newest movies in theaters
     */
    public String newDVD() throws MalformedURLException, IOException, Exception {
        URL jsonRequest = null;
        try {
            jsonRequest = new URL("http://api.rottentomatoes.com/api/public/v1.0/lists/dvds/new_releases.json?apikey=yedukp76ffytfuy24zsqk7f5&page_limit=20");
        } catch (MalformedURLException ex) {
            Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
        }

        String response2 = sendGet(jsonRequest);
        //URLConnection connection = jsonRequest.openConnection();
        //connection.setDoOutput(true);
        //Scanner scanner = new Scanner(jsonRequest.openStream());
        //String response2 = scanner.useDelimiter("\\Z").next();
        Object obj = null;
        try {
            obj = parser.parse(response2);
        } catch (ParseException ex) {
            Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
        }
        JSONObject jsonO = (JSONObject) obj;
        JSONArray json = (JSONArray) jsonO.get("movies");
        //scanner.close();

        List<Movie> results = new ArrayList<Movie>();
        for (int c = 1; c < json.size(); c++) {
            JSONObject obj2 = (JSONObject) json.get(c);
            results.add(new Movie(obj2));
        }

        movies = results;
        addMovies();
        return "newDVDs";
    }

    /**
     * Sets the movie list to a list of the newest movies on DVD
     */
    public String newTheater() throws IOException, Exception {
        URL jsonRequest = null;
        try {
            jsonRequest = new URL("http://api.rottentomatoes.com/api/public/v1.0/lists/movies/in_theaters.json?apikey=yedukp76ffytfuy24zsqk7f5&page_limit=20");
        } catch (MalformedURLException ex) {
            Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
        }
        String response2 = sendGet(jsonRequest);
        //URLConnection connection = jsonRequest.openConnection();
        //connection.setDoOutput(true);
        //Scanner scanner = new Scanner(jsonRequest.openStream());
        //String response2 = scanner.useDelimiter("\\Z").next();
        Object obj = null;
        try {
            obj = parser.parse(response2);
        } catch (ParseException ex) {
            Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
        }
        JSONObject jsonO = (JSONObject) obj;
        JSONArray json = (JSONArray) jsonO.get("movies");
        //scanner.close();

        List<Movie> results = new ArrayList<Movie>();
        for (int c = 1; c < json.size(); c++) {
            JSONObject obj2 = (JSONObject) json.get(c);
            results.add(new Movie(obj2));
        }

        movies = results;
        return "newMovies";
    }
    
    private String sendGet(URL url) throws Exception{
        URL obj = url;
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");

            //add request header
            //con.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response;
                                response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
            }
            in.close();
        return response.toString();
    }

    /**
     * Returns the list saved by this search object
     * @return movie list
     */
    public List<Movie> getMovies() {
        return movies;
    }
    
    public void addMovies() {
        
        for (Movie m: movies) {
            Session session = factory.openSession();
        Transaction tx = null;
                try {
            tx = session.beginTransaction();
            session.save(m);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
                    session.close();
                }
        }
        
    }
    
}
