package dev.nub.mycinema.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class TvModel implements Parcelable {
    private String title;
    private String popularity;
    private String first_air_date;
    private String backdrop_path;
    private String id;
    private String overview;
    private String poster_path;

    public TvModel (JSONObject jsonObject){
        try {
            String title = jsonObject.getString("name");
            String popularity = jsonObject.getString("popularity");
            String first_air_date = jsonObject.getString("first_air_date");
            String backdrop_path = jsonObject.getString("backdrop_path");
            String id = jsonObject.getString("id");
            String overview = jsonObject.getString("overview");
            String poster_path = jsonObject.getString("poster_path");
            String url = "https://image.tmdb.org/t/p/original/";
            this.title = title;
            this.popularity = popularity;
            this.first_air_date = first_air_date;
            this.backdrop_path = url+backdrop_path;
            this.id = id;
            this.overview = overview;
            this.poster_path = url+poster_path;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.popularity);
        dest.writeString(this.first_air_date);
        dest.writeString(this.backdrop_path);
        dest.writeString(this.id);
        dest.writeString(this.overview);
        dest.writeString(this.poster_path);
    }

    public TvModel() {
    }

    protected TvModel(Parcel in) {
        this.title = in.readString();
        this.popularity = in.readString();
        this.first_air_date = in.readString();
        this.backdrop_path = in.readString();
        this.id = in.readString();
        this.overview = in.readString();
        this.poster_path = in.readString();
    }

    public static final Parcelable.Creator<TvModel> CREATOR = new Parcelable.Creator<TvModel>() {
        @Override
        public TvModel createFromParcel(Parcel source) {
            return new TvModel(source);
        }

        @Override
        public TvModel[] newArray(int size) {
            return new TvModel[size];
        }
    };
}
