package com.liferay.myapplication.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.liferay.mobile.android.service.Session;
import com.liferay.mobile.screens.auth.login.LoginListener;
import com.liferay.mobile.screens.auth.login.LoginScreenlet;
import com.liferay.mobile.screens.context.User;
import com.liferay.mobile.screens.util.LiferayLogger;
import com.liferay.myapplication.R;
import com.liferay.myapplication.activities.MainActivity;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements LoginListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginScreenlet loginListener = (LoginScreenlet) findViewById(R.id.login_screenlet);
        loginListener.setListener(this);
    }

    @Override
    public void onLoginSuccess(User user) {
        startActivity(new Intent(this, ATWebContent.class));
    }

    @Override
    public void onLoginFailure(Exception e) {
        Snackbar.make(findViewById(android.R.id.content), "Login Failed", Snackbar.LENGTH_LONG)
                .setActionTextColor(Color.RED)
                .show();
    }


}
