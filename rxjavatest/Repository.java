package com.sindabad.rxjavatest;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {

    private static Retrofit retrofit;

    public synchronized static Retrofit getRetrofitInstance() {
//        final String api_end = "https://jsonplaceholder.typicode.com/";
//        https://dev.sindabad.com/
        final String api_end = "https://dev.sindabad.com";

//        OkHttpClient.Builder clientBuilder = new OkHttpClient().newBuilder();
//
//        clientBuilder.addInterceptor(new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//
//                Request request = chain.request();
//                Request.Builder requestBuilder;
//                requestBuilder = request.newBuilder().removeHeader("User-Agent")
//
//                        .addHeader("Content-Type", "application/json")
//                        .addHeader("Authorization", "7pyd692pl3bb4zk32k2izpktt6262nva");
//                Response response = chain.proceed(requestBuilder.build());
//                return response;
//            }
//        });
//
////        OkHttpClient.Builder clientBuilder = new OkHttpClient().newBuilder();
//        OkHttpClient okHttpClient = clientBuilder.readTimeout(60, TimeUnit.SECONDS)
//                .connectTimeout(60, TimeUnit.SECONDS)
//                .build();

        OkHttpClient.Builder clientBuilder = new OkHttpClient().newBuilder();

        clientBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                Request request = chain.request();
                Request.Builder requestBuilder;
                requestBuilder = request.newBuilder().removeHeader("User-Agent")
//                        .addHeader("User-Agent", osInfo())
                        .addHeader("Content-Type", "application/json")
                        .addHeader("Authorization", "Bearer 7pyd692pl3bb4zk32k2izpktt6262nva");

                Response response = chain.proceed(requestBuilder.build());
                return response;

//                if (request.headers().get("Cache-Control") == null) {
//                    requestBuilder
//                            .header("Cache-Control", "public, only-if-cached, max-stale=" + 60
//                            * 60 /** 60 * 24 * 1*/)
//                            .method(request.method(), request.body());
//                    Response response = chain.proceed(requestBuilder.build());
//                    if (response.cacheResponse() == null) {
//                        if (Utilities.isNetworkAvailable(context)) {
//                            requestBuilder
//                                    .header("Cache-Control", "public, max-age=" + 60)
//                                    .method(request.method(), request.body());
//                            return chain.proceed(requestBuilder.build());
//                        } else {
//                            throw new IOException("No Internet Connectivity");
//                        }
//                    } else {
//                        return response;
//                    }
//                } else {
//                    if (Utilities.isNetworkAvailable(context)) {
//                        requestBuilder
//                                //.header("Cache-Control", "public, max-age=" + 60)
//                                .method(request.method(), request.body());
//                        return chain.proceed(requestBuilder.build());
//                    } else {
//                        throw new IOException("No Internet Connectivity");
//                    }
//                }
            }
        });

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        clientBuilder.addInterceptor(loggingInterceptor);


        OkHttpClient okHttpClient = clientBuilder.readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();

        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(api_end)
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;

    }

    class Product {
        public int id;
        public String name;
        public String sku;

        public Product() {
        }

        public Product(int id, String name, String sku) {
            this.id = id;
            this.name = name;
            this.sku = sku;
        }
    }
}