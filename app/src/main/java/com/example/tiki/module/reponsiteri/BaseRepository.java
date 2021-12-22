package com.example.tiki.module.reponsiteri;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

public abstract class BaseRepository<T> {
    public <T> void process(Call<T> call, ResultFromApi<T> result) {
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    result.responseFromApi(response.body(), null);
                } else if (response.code() > 400 && response.code() < 511) {
                    result.responseFromApi(null, new HttpException(response));
                } else {
                    result.responseFromApi(response.body(), null);
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                result.responseFromApi(null, t);
            }
        });
    }
}


interface ResultFromApi<T> {
    void responseFromApi(T data, Throwable t);
}
