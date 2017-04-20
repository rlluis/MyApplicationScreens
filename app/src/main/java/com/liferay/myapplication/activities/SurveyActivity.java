package com.liferay.myapplication.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.liferay.mobile.screens.ddl.form.DDLFormListener;
import com.liferay.mobile.screens.ddl.form.DDLFormScreenlet;
import com.liferay.mobile.screens.ddl.model.DocumentField;
import com.liferay.mobile.screens.ddl.model.Record;
import com.liferay.myapplication.R;
import com.liferay.myapplication.notification.SnackbarUtil;

import org.json.JSONObject;

import java.util.Map;

public class SurveyActivity extends AppCompatActivity implements DDLFormListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        DDLFormScreenlet surveyListener = (DDLFormScreenlet) findViewById(R.id.form_survey);
        surveyListener.setListener(this);
    }

    @Override
    public void onDDLFormLoaded(Record record) {

    }

    @Override
    public void onDDLFormRecordLoaded(Record record, Map<String, Object> valuesAndAttributes) {

    }

    @Override
    public void onDDLFormRecordAdded(Record record) {
        SnackbarUtil.showMessage(this, getString(R.string.thanks));
        startActivity(new Intent(this, DiscountActivity.class));
    }

    @Override
    public void onDDLFormRecordUpdated(Record record) {

    }

    @Override
    public void onDDLFormDocumentUploaded(DocumentField documentField, JSONObject jsonObject) {

    }

    @Override
    public void onDDLFormDocumentUploadFailed(DocumentField documentField, Exception e) {

    }

    @Override
    public void error(Exception e, String userAction) {
        SnackbarUtil.showMessage(this, e.getMessage());
    }
}
