
import org.json.simple.JSONObject;

/**
 * @author jacob
 */
public class Movie {
    private JSONObject json;

    //TODO
    //Cast
    //Reviews
    //Clips

    /**
     * Creates a new instance of a Movie
     * @param json
     */
    public Movie(JSONObject json) {
        this.json = json;
    }

    /**
     * Gets the rotten tomatoes ID of this movie
     * @return id
     */
    public int getID() {
        return (int) json.get("id");
    }

    /**
     * Gets the title of this movie
     * @return title
     */
    public String getTitle() {
        return json.get("title");
    }

    /**
     * Gets the MPAA rating of this movie
     * @return mpaa_rating
     */
    public String getMPAA() {
        return json.get("mpaa_rating");
    }

    /**
     * Gets the theatre release date of this movie
     * @return release_date_theatre
     */
    public String getReleaseT() {
        return json.get("release_dates");   //This isn't right, need to fix later
    }

    /**
     * Gets the dvd release date of this movie
     * @return release_date_dvd
     */
    public String getReleaseD() {
        return json.get("release_dates");   //This isn't right, need to fix later
    }

    /**
     * Gets the synopsis of this movie
     * @return synopsis
     */
    public String getSynopsis() {
        return json.get("synopsis");
    }

    /**
     * Gets the director of this movie
     * @return director
     */
    public String getDirector() {
        return json.get("director");
    }

    /**
     * Gets the studio of this movie
     * @return studio
     */
    public String getStudio() {
        return json.get("studio");
    }

    /**
     * Gets the runtime of this movie
     * @return runtime
     */
    public int getRuntime() {
        return json.get("runtime");
    }

    /**
     * Gets the critic's consensus of this movie
     * @return critics_consensus
     */
    public String getConsensus() {
        return json.get("critics_consensus");
    }

    /**
     * Gets the critic's rating of this movie
     * @return critics_rating
     */
    public String getCRating() {
        return json.get("critics_rating");
    }

    /**
     * Gets the audience's rating of this movie
     * @return audience_rating
     */
    public String getARating() {
        return json.get("audience_rating");
    }

    /**
     * Gets the critic's score of this movie
     * @return critics_score
     */
    public int getCScore() {
        return json.get("critics_score");
    }

    /**
     * Gets the audience's score of this movie
     * @return audience_score
     */
    public String getAScore() {
        return json.get("audience_score");
    }

    /**
     * Gets the thumbnail of the movie's poster
     * @return thumbnail
     */
    public String getThumbnail() {
        return json.get("thumbnail");
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
        return json.get("original");
    }
}
