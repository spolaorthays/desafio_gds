package com.gds.desafioandroidgds.service;

public interface ServiceListener {

    void onSuccess(Object object);

    void onError(Throwable throwable);
}
