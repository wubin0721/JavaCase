package com.demo.mvp_demo.view;

import com.demo.mvp_demo.model.User;

public interface Iview {

    void showProgressDialog();

    void hideProgressDialog();

    void loginSuccess(User user);

    void loginFailure(String msg);
}
