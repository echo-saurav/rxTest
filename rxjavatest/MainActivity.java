package com.sindabad.rxjavatest;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    String TAG = "RX_JAVA";
    int complete = 0;
    int error = 0;
    int i = 0;
    CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        compositeDisposable = new CompositeDisposable();
        getDataService =
                Repository.getRetrofitInstance().create(GetDataService.class);

        for (i = 0; i < 1000; i++) {
            startThread(i);
        }


    }

    private GetDataService getDataService;

    void startThread(int c) {

//        getDataService.getAllPost().subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(products -> {
//                            complete++;
//                            Log.d(TAG,
//                                    c + ": accept:" + complete + ":  result[0]:" + products.name);
//                        }
//                        ,
//                        throwable -> {
//                            error++;
//                            Log.d(TAG,
//                                    c + ": accept: " + error + " :error " + throwable
//                                    .getMessage());
//
//                        }
//                );

        Disposable disposable =
                getDataService.getAllPost().subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(products -> {
                                    complete++;
                                    Log.d(TAG,
                                            c + ": accept:" + complete + ":  result[0]:" + products.name);
                                }
                                ,
                                throwable -> {
                                    error++;
                                    Log.d(TAG,
                                            c + ": accept: " + error + " :error " + throwable.getMessage());

                                }
                        );
        compositeDisposable.add(disposable);
    }
}