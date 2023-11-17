package com.dineshwayaman.rxjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.dineshwayaman.rxjava.Adapters.RepoRecyclerAdapter;
import com.dineshwayaman.rxjava.Services.GithubServices;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView repoRecycle;
    private RepoRecyclerAdapter repoRecyclerAdapter;
    private CompositeDisposable disposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        repoRecycle = findViewById(R.id.repoRecycle);

        repoRecyclerAdapter = new RepoRecyclerAdapter(this);

        repoRecycle.setLayoutManager(new LinearLayoutManager(this));
        repoRecycle.setAdapter(repoRecyclerAdapter);

        fetchRepo();


    }

    private void fetchRepo() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        GithubServices gitService = retrofit.create(GithubServices.class);

        disposable.add(gitService.getRepos("DineshWayaman")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        repos -> {
                            repoRecyclerAdapter.setData(repos);
                        },

                        throwable -> {
                            Toast.makeText(this, "Error fetching git", Toast.LENGTH_SHORT).show();
                        }
                ));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }
}