package dev.nub.mycinema.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class MovieModel implements Parcelable {
    private String title;
    private String overview;
    private String release_date;
    private String id;
    private String poster_path;
    private String backdrop_path;
    private String popularity;
    private double vote_average;

    public MovieModel() {

    }

    public MovieModel(JSONObject jsonObject){
        try {
            String title = jsonObject.getString("title");
            String overview = jsonObject.getString("overview");
            String release_date = jsonObject.getString("release_date");
            String id = jsonObject.getString("id");
            String poster_path = jsonObject.getString("poster_path");
            String backdrop_path = jsonObject.getString("backdrop_path");
            String popularity = jsonObject.getString("popularity");
            double vote_average = jsonObject.getDouble("vote_average");
            String url = "https://image.tmdb.org/t/p/original/";
            this.title = title;
            this.overview = overview;
            this.release_date = release_date;
            this.id = id;
            this.poster_path = url+poster_path;
            this.backdrop_path = url+backdrop_path;
            this.popularity = popularity;
            this.vote_average = vote_average;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.overview);
        dest.writeString(this.release_date);
        dest.writeString(this.id);
        dest.writeString(this.poster_path);
        dest.writeString(this.backdrop_path);
        dest.writeDouble(this.vote_average);
    }

    protected MovieModel(Parcel in) {
        this.title = in.readString();
        this.overview = in.readString();
        this.release_date = in.readString();
        this.id = in.readString();
        this.poster_path = in.readString();
        this.backdrop_path = in.readString();
        this.vote_average = in.readDouble();
    }

    public static final Parcelable.Creator<MovieModel> CREATOR = new Parcelable.Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel source) {
            return new MovieModel(source);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };
}
