package com.demo.mvp_demo.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.demo.mvp_demo.present.IPresenter;
import com.demo.mvp_demo.present.MainPresenter;
import com.demo.mvp_demo.R;
import com.demo.mvp_demo.model.User;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, Iview {

    private EditText et_username;
    private EditText et_password;
    private Button btn_login;

    private ProgressDialog progressDialog;

    //V层持有P层的接口，不直接和 M层接触
    private IPresenter mainPresenter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        initView();

        //将 V层交给 P层
        mainPresenter = new MainPresenter(this);
    }

    private void initView() {
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        btn_login = findViewById(R.id.btn_loghin);
        btn_login.setOnClickListener(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("请稍后");
    }

    @Override
    public void onClick(View v) {
        String username = et_username.getText().toString();
        String password = et_password.getText().toString();

        //把事件发给P层
        mainPresenter.login(username,password);
    }


    //P层通知 V层更新UI
    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void loginSuccess(User user) {
        Toast.makeText(this,"成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginFailure(String msg) {
        Toast.makeText(this,"失败",Toast.LENGTH_SHORT).show();
    }


}