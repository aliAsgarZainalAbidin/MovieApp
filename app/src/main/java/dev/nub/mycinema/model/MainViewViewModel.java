package dev.nub.mycinema.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

import static dev.nub.mycinema.BuildConfig.API_KEY;

public class MainViewViewModel extends ViewModel {
    private MutableLiveData<ArrayList<MovieModel>> listMovie = new MutableLiveData<>();

    public void setListMovie(){
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        final ArrayList<MovieModel> movie = new ArrayList<>();
        String url = "https://api.themoviedb.org/3/discover/movie?api_key="+API_KEY+"&language=en-US";
        asyncHttpClient.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("results");

                    for (int i=0; i<jsonArray.length(); i++){
                        JSONObject jsonMovie = jsonArray.getJSONObject(i);
                        MovieModel movieModel = new MovieModel(jsonMovie);
                        movie.add(movieModel);
                    }

                    listMovie.postValue(movie);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    public LiveData<ArrayList<MovieModel>> getListMovie(){
        return listMovie;
    }
}
