package com.sindabad.rxjavatest;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface GetDataService {
//    @GET("/posts/1/comments")
//    Flowable<List<Repository.PostData>> getAllPost();
//    https://dev.sindabad.com/rest/bn/V1/mobileapps/products/PBAP000000230CWT
    @GET("/rest/bn/V1/mobileapps/products/PBAP000000230CWT")
    Flowable<Repository.Product> getAllPost();
}
