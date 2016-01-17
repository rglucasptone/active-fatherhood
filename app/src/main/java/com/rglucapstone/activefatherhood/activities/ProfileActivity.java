package com.rglucapstone.activefatherhood.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.rglucapstone.activefatherhood.R;
import com.rglucapstone.activefatherhood.data.Question;

import java.util.ArrayList;

/**
 * Created by Luisa Castro on 17/12/2015.
 */
public class ProfileActivity extends Activity {
    private ArrayAdapter listQuestionsAdapter;
    private ListView listQuestions;
    private ArrayList<Question> arrayOfQuestions;
    private Context list;

    private ArrayAdapter listInfoGuruAdapter;
    private ListView listPadresGurus;
    private ArrayList<Question> arrayOfInfoGuru;

    private static final int PROGRESS = 0x1;

    private ProgressBar mProgress;
    private int mProgressStatus = 0;

    private Handler mHandler = new Handler();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_profile);
    }

    public void askGuru(View view){
        Intent intent = new Intent(this, AskGuruActivity.class);
        startActivity(intent);
    }

    public void guruQuestion(View view){
        Intent intent = new Intent(this, AskGuruActivity.class);
        startActivity(intent);
    }

    public void editProfile(View view){
        Intent intent = new Intent(this, EditProfileActivity.class);
        startActivity(intent);
    }

    public void asking(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }
}
