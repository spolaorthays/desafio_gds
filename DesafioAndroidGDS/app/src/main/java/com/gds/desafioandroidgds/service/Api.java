package com.gds.desafioandroidgds.service;

import com.gds.desafioandroidgds.model.Cartao;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("/nicolau-kerpen?querycard=15420003466&pin=YMON5BHOQM&qcapi")
    Call<Cartao> getCartao();

//    @GET("/unisabor?querycard=16150001503&pin=N6E4F7KIC6&qcapi")
//    Call<Cartao> getCartaoColegio();
}
