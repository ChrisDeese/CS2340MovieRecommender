package Model.User;

import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * @author jacob
 */
public class Search {
    private UserData user;
    private JSONParser parser;

    /**
     * Creates a new instance of a Search
     * @param user
     */
    public Search(UserData user) {
        this.user = user;
        parser = new JSONParser();
    }

    //TODO
    //Get more than one response
    /**
     * Sets the name of this user
     * @param name
     */
    public Movie find(String movie) {
        URL jsonRequest = new URL("http://api.rottentomatoes.com/api/public/v1.0/movies.json?apikey=yedukp76ffytfuy24zsqk7f5&q=" + movie + "&page_limit=1");
        URLConnection connection = jsonRequest.openConnection();
        connection.setDoOutput(true);
        Scanner scanner = new Scanner(wikiRequest.openStream());
        String response = scanner.useDelimiter("\\Z").next();
        JSONObject json = Util.parseJson(response);
        scanner.close();
        return new Movie(found);
    }
}
