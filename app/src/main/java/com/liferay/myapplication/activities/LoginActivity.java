package com.liferay.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;

import com.liferay.mobile.android.service.Session;
import com.liferay.mobile.screens.auth.login.LoginListener;
import com.liferay.mobile.screens.auth.login.LoginScreenlet;
import com.liferay.mobile.screens.context.User;
import com.liferay.mobile.screens.util.LiferayLogger;
import com.liferay.myapplication.R;

import com.liferay.myapplication.notification.SnackbarUtil;
import org.json.JSONObject;

public class LoginActivity extends PushScreensActivity implements LoginListener {

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
        SnackbarUtil.showMessage(this, "Login Failed");
    }

    @Override
    protected Session getDefaultSession() {
        return null;
    }

    @Override
    protected void onPushNotificationReceived(JSONObject jsonObject) {

    }

    @Override
    protected void onErrorRegisteringPush(String message, Exception e) {
        LiferayLogger.e(e.getMessage());
    }

    @Override
    protected String getSenderId() {
        return getString(R.string.sender_id);
    }
}
