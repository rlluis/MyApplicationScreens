package com.liferay.myapplication.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.liferay.mobile.android.callback.typed.JSONArrayCallback;
import com.liferay.mobile.android.service.Session;
import com.liferay.mobile.screens.context.SessionContext;
import com.liferay.mobile.screens.context.User;
import com.liferay.mobile.screens.util.LiferayLogger;
import com.liferay.mobile.screens.webcontent.display.WebContentDisplayScreenlet;
import com.liferay.myapplication.R;
import com.liferay.myapplication.mobileSDK.GetUserSegment;

import org.json.JSONArray;
import org.json.JSONObject;

public class ATWebContent extends AppCompatActivity implements View.OnClickListener {

    private WebContentDisplayScreenlet webcontent;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atweb_content);

		Button continueButton = (Button) findViewById(R.id.continue_button);
        continueButton.setOnClickListener(this);

        Session session = SessionContext.createSessionFromCurrentSession();
        User user = SessionContext.getCurrentUser();
        GetUserSegment service = new GetUserSegment(session);
        final boolean userSegmentActive=true;

        session.setCallback(new JSONArrayCallback() {
            @Override
            public void onFailure(Exception exception) {
                LiferayLogger.e(exception.toString());
            }

            @Override
            public void onSuccess(JSONArray result) {
                String userSegmentId = getString(R.string.user_segment);
                String articulo = calculoArticulo(result, userSegmentId);
                webcontent = (WebContentDisplayScreenlet) findViewById(R.id.web_atarticle);
                webcontent.setArticleId(articulo);
                webcontent.load();
            }
            
            private String calculoArticulo(JSONArray result, String userSegmentId) {
                Boolean belongsToSegment = false;
                String article = "";
                try {
                    if (result != null) {
                        String segment = "";
                        for (int i = 0; i < result.length(); i++) {
                            JSONObject jsonObject = result.getJSONObject(i);
                            segment = jsonObject.getString("userSegmentId");
                            if (segment.equals(userSegmentId)) {
                                belongsToSegment = true;
                            }
                        }
                    }

                    if (belongsToSegment) {
                        article = getString(R.string.webcontent_usersegment);
                    } else {
                        article = getString(R.string.webcontent_normal);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return article;
            }});

        try {
            service.getUserSegments(user.getId(), userSegmentActive);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        if (getString(R.string.webcontent_usersegment).equals(webcontent.getArticleId())) {
            startActivity(new Intent(this, SurveyActivity.class));
        }else{
            startActivity(new Intent(this, MainActivity.class));
        }
    }
}
