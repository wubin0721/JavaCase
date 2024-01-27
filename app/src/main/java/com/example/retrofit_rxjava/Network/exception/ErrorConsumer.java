package com.example.retrofit_rxjava.Network.exception;

import io.reactivex.functions.Consumer;

public abstract class ErrorConsumer implements Consumer<Throwable> {


    @Override
    public void accept(Throwable throwable) throws Exception {
        ApiException ex;
        if(throwable instanceof ApiException){
                ex = (ApiException)throwable;
        }else{
            ex = ApiException.handleException(throwable);
        }
    }

    protected abstract void error(ApiException e);
}
