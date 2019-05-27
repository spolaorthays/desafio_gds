package com.gds.desafioandroidgds.database.dao;

import android.content.Context;

import com.gds.desafioandroidgds.model.Cartao;
import com.gds.desafioandroidgds.service.RetrofitService;
import com.gds.desafioandroidgds.service.ServiceListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Dao {

    public Cartao getCartao (Context context, final ServiceListener listener){

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

        return new Cartao();
    }

    public Cartao getApiDois (Context context, final ServiceListener listener){

        Call<Cartao> call = RetrofitService.getApi().getCartaoCentroCinquenta();

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

        return new Cartao();
    }

    public Cartao getApiTres (Context context, final ServiceListener listener){

        Call<Cartao> call = RetrofitService.getApi().getCartaoQuatrocentosDez();

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

        return new Cartao();
    }

    public Cartao getApiQuatro (Context context, final ServiceListener listener){

        Call<Cartao> call = RetrofitService.getApi().getCartaoQuinhentos();

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

        return new Cartao();
    }

    public Cartao getApiCinco (Context context, final ServiceListener listener){

        Call<Cartao> call = RetrofitService.getApi().getCartaoSeiscentosSetentaUm();

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

        return new Cartao();
    }
}
