package com.gds.desafioandroidgds.service;

import com.gds.desafioandroidgds.model.Cartao;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("/nicolau-kerpen?querycard=15420003466&pin=YMON5BHOQM&qcapi")
    Call<Cartao> getCartao();

    @GET("/unisabor?querycard=16150001503&pin=N6E4F7KIC6&qcapi")
    Call<Cartao> getCartaoCentroCinquenta();

    @GET("/unisabor?querycard=16150004109&pin=AQW55LDRUW&qcapi")
    Call<Cartao> getCartaoQuatrocentosDez();

    @GET("/unisabor?querycard=16150005000&pin=O3K4U8I8E9&qcapi")
    Call<Cartao> getCartaoQuinhentos();

    @GET("/unisabor?querycard=16150006713&pin=R67BGZURPY&qcapi")
    Call<Cartao> getCartaoSeiscentosSetentaUm();
}
