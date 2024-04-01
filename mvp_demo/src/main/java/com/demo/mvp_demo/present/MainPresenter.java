package com.demo.mvp_demo.present;

import com.demo.mvp_demo.model.Callback;
import com.demo.mvp_demo.model.User;
import com.demo.mvp_demo.model.Imodel;
import com.demo.mvp_demo.model.MainModel;
import com.demo.mvp_demo.view.Iview;

public class MainPresenter implements IPresenter, Callback {

    //P层持有M层
    private Imodel mainModel;

    //P层收到M层数据后去通知更新V层
    private Iview activity;

    public MainPresenter(Iview activity){
        mainModel = new MainModel();
        this.activity = activity;
    }


    @Override
    public void login(String username, String password) {
            // M层去获取数据
            mainModel.login(username,password,this);
    }

    //P层通知 V层更新UI
    @Override
    public void onSuccess(User user) {
        activity.showProgressDialog();
        activity.loginSuccess(user);
    }

    @Override
    public void onFailure(String msg) {
        activity.hideProgressDialog();
        activity.loginFailure(msg);
    }
}
