package dev.nub.mycinema.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import dev.nub.mycinema.R;
import dev.nub.mycinema.fragment.FavoriteFragment;
import dev.nub.mycinema.fragment.MoviesFragment;
import dev.nub.mycinema.fragment.TvFragment;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment fragment;
            switch (menuItem.getItemId()){
                case R.id.myMovies : {
                    fragment = new MoviesFragment();
                    setupFragment(fragment);
                    return true;
                }

                case R.id.myTvShow : {
                    fragment = new TvFragment();
                    setupFragment(fragment);
                    return true;
                }

                case  R.id.myFavorite : {
                    fragment = new FavoriteFragment();
                    setupFragment(fragment);
                    return true;
                }
            }

            return false;
        }
    };

    //Fullscreen
    private void fullScreen(){
        View view = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        view.setSystemUiVisibility(uiOptions);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navbar);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

        fullScreen();

        if (savedInstanceState== null){
            MoviesFragment moviesFragment = new MoviesFragment();
            setupFragment(moviesFragment);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        fullScreen();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fullScreen();
    }

    private void setupFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment, fragment.getClass().getSimpleName())
                .commit();
    }
}
