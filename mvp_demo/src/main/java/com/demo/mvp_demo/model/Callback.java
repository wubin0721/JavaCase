package com.demo.mvp_demo.model;

public interface Callback {
    void onSuccess(User user);

    void onFailure(String msg);
}
