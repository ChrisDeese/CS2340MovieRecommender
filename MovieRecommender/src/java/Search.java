
import java.io.Serializable;
import java.util.Iterator;
import java.util.ArrayList;
import Model.User.UserData;
import java.io.IOException;
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
/**
 * @author jacob
 */
 @ManagedBean
 @SessionScoped
public class Search implements Serializable {
    private JSONParser parser;
    private String input;
    private List<Movie> movies;

    /**
     * Creates a new instance of a Search
     * @param user
     */
    public Search() {
        parser = new JSONParser();
    }

    //TODO
    //Get more than one response
    /**
     * Sets the name of this user
     * @param name
     */

    public void find(String movie) throws IOException {
        URL jsonRequest = null;
        try {
            jsonRequest = new URL("http://api.rottentomatoes.com/api/public/v1.0/movies.json?apikey=yedukp76ffytfuy24zsqk7f5&q=" + movie + "&page_limit=20");
        } catch (MalformedURLException ex) {
            Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
        }
        URLConnection connection = jsonRequest.openConnection();
        connection.setDoOutput(true);
        JSONArray json;
        try (Scanner scanner = new Scanner(jsonRequest.openStream())) {
            String response = scanner.useDelimiter("\\Z").next();
            json = (JSONArray) Util.parseJson(response);
        }

        List<Movie> results = new ArrayList<Movie>();
        for (int c = 1; c < json.size(); c++) {
            results.add((Movie) json.get(c));
        }

        movies = results;
    }

    public void newTheater() throws MalformedURLException {
        URL jsonRequest = new URL("http://api.rottentomatoes.com/api/public/v1.0/lists/dvds/new_releases.json?apikey=yedukp76ffytfuy24zsqk7f5&page_limit=20");
        URLConnection connection = jsonRequest.openConnection();
        connection.setDoOutput(true);
        Scanner scanner = new Scanner(jsonRequest.openStream());
        String response = scanner.useDelimiter("\\Z").next();
        JSONArray json = (JSONArray) Util.parseJson(response);
        scanner.close();

        List<Movie> results = new ArrayList<Movie>();
        for (int c = 1; c < json.size(); c++) {
            results.add((Movie) json.get(c));
        }

        movies = results;
    }

    public void newDVD() {
        URL jsonRequest = new URL("http://api.rottentomatoes.com/api/public/v1.0/lists/movies/in_theaters.json?apikey=yedukp76ffytfuy24zsqk7f5&page_limit=20");
        URLConnection connection = jsonRequest.openConnection();
        connection.setDoOutput(true);
        Scanner scanner = new Scanner(wikiRequest.openStream());
        String response = scanner.useDelimiter("\\Z").next();
        JSONArray json = (JSONArray) Util.parseJson(response);
        scanner.close();

        List<Movie> results = new ArrayList<Movie>();
        for (int c = 1; c < json.size(); c++) {
            results.add(json.get(c));
        }

        movies = results;
    }

    public List<Movie> getMovies() {
        return movies;
    }
}
