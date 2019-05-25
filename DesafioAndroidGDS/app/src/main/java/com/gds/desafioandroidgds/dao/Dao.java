package com.gds.desafioandroidgds.dao;

import android.content.Context;

import com.gds.desafioandroidgds.model.Cartao;
import com.gds.desafioandroidgds.service.RetrofitService;
import com.gds.desafioandroidgds.service.ServiceListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Dao {

    public List<Cartao> getCartaoList (Context context, final ServiceListener listener){

        Call<Cartao> call = RetrofitService.getApi().getCartao();

        call.enqueue(new Callback<Cartao>() {
            @Override
            public void onResponse(Call<Cartao> call, Response<Cartao> response) {
                if (response.body() != null){
                    listener.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<Cartao> call, Throwable t) {
                listener.onError(t);
            }
        });

        return new ArrayList<>();
    }
}
