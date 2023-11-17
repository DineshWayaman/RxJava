package com.dineshwayaman.rxjava.Services;

import com.dineshwayaman.rxjava.Models.Repo;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubServices {
    @GET("users/{username}/repos")
    Observable<List<Repo>> getRepos(@Path("username") String username);
}
