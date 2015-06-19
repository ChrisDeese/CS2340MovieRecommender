
import org.json.simple.JSONObject;

/**
 * @author jacob
 */
public class Movie {
    private JSONObject json;
    private String title;
    private String id;

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
        this.title = (String) json.get("title");
        this.id = (String) json.get("id");
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
        return (String) json.get("title");
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
    public int getRuntime() {
        return (int) json.get("runtime");
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
        return (String) json.get("audience_rating").;
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
}
