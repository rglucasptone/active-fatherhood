package com.rglucapstone.activefatherhood;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;

import java.util.ArrayList;

import com.rglucapstone.activefatherhood.R;
import com.rglucapstone.activefatherhood.apis.PadreGuru;
import com.rglucapstone.activefatherhood.apis.Question;

/**
 * Created by Luisa Castro on 16/12/2015.
 */
public class HomeActivity extends AppCompatActivity {
    private ArrayAdapter listQuestionsAdapter;
    private ListView listQuestions;
    private ArrayList<Question> arrayOfQuestions;
    private Context list;

    private ArrayAdapter listInfoGuruAdapter;
    private ListView listPadresGurus;
    private ArrayList<Question> arrayOfInfoGuru;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        /*ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);*/

        ArrayList<Question> arrayOfQuestions = new ArrayList<Question>();
        this.populateQuestions(arrayOfQuestions);
        listQuestionsAdapter = new QuestionItemAdapter(this, arrayOfQuestions);
        listQuestions = (ListView) findViewById(R.id.listQuestions);
        listQuestions.setAdapter(listQuestionsAdapter);
        //setListAdapter(listQuestionsAdapter);

        list = this;
        listQuestions.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parentAdapter, View view, int position,
                                    long id) {
                Intent intent = new Intent(list, QuestionActivity.class);
                //i.putExtra("id", user.getUserAccountId()+"");
                //Question question = getItem(position);
                intent.putExtra("user", "Pedro Pablo");
                intent.putExtra("date", "hace 5 horas");
                intent.putExtra("content", "Lorem ipsum dolor sit amet, consectetur adipiscing elit?");
                startActivity(intent);

                //TextView t = (TextView) findViewById(R.id.questionContent);
                //t.setText("hola mundo");

                //Toast.makeText(getApplicationContext(), "TV Selected", Toast.LENGTH_SHORT).show();
            }
        });

        ArrayList<PadreGuru> arrayOfInfoGuru = new ArrayList<PadreGuru>();
        this.populateInfoGuru(arrayOfInfoGuru);
        listInfoGuruAdapter = new GuruItemAdapter(this, arrayOfInfoGuru);
        listPadresGurus = (ListView) findViewById(R.id.listPadresGurus);
        listPadresGurus.setAdapter(listInfoGuruAdapter);


        final RelativeLayout content_question = (RelativeLayout) findViewById(R.id.content_question);
        final RelativeLayout content_publicaciones = (RelativeLayout) findViewById(R.id.content_publicaciones);
        final RelativeLayout content_gurus = (RelativeLayout) findViewById(R.id.content_gurus);

        final Button btn_menu_general= (Button) findViewById(R.id.menu_general);
        final Button menu_publicaciones= (Button) findViewById(R.id.menu_publicaciones);
        final Button menu_gurus= (Button) findViewById(R.id.menu_gurus);

        content_publicaciones.setVisibility(View.INVISIBLE);
        content_gurus.setVisibility(View.INVISIBLE);

        btn_menu_general.setBackgroundColor(Color.WHITE);

        btn_menu_general.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                btn_menu_general.setBackgroundColor(Color.WHITE);
                menu_publicaciones.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_no_button));
                menu_gurus.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_no_button));

                content_publicaciones.setVisibility(View.INVISIBLE);
                content_gurus.setVisibility(View.INVISIBLE);
                content_question.setVisibility(View.VISIBLE);
               /* content_question.setVisibility((content_question.getVisibility() == View.VISIBLE)
                        ? View.GONE : View.VISIBLE);*/
            }
        });

        menu_publicaciones.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                menu_publicaciones.setBackgroundColor(Color.WHITE);
                btn_menu_general.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_no_button));
                menu_gurus.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_no_button));

                content_question.setVisibility(View.INVISIBLE);
                content_gurus.setVisibility(View.INVISIBLE);
                content_publicaciones.setVisibility(View.VISIBLE);
            }
        });

        menu_gurus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                menu_gurus.setBackgroundColor(Color.WHITE);
                btn_menu_general.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_no_button));
                menu_publicaciones.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_no_button));

                content_question.setVisibility(View.INVISIBLE);
                content_publicaciones.setVisibility(View.INVISIBLE);
                content_gurus.setVisibility(View.VISIBLE);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar, menu);


        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchActionBarItem = menu.findItem(R.id.action_buscar);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchActionBarItem);
        //SearchView searchView = (SearchView) menu.findItem(R.id.action_buscar).getActionView();

        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(true); // Do not iconify the widget; expand it by default

       /* SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.action_buscar).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(true);*/

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_buscar:
                // User chose the "Settings" item, show the app settings UI...
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    public void profileOn(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    private void populateQuestions(ArrayList questions){
        Question q1 = new Question("Pedro Xavier", "hace 45 minutos", "¿Cómo y cuándo hablar con los niños acerca de las enfermedades que puedan tener?","Bebes","10 Me Gusta","15 Respuestas");
        questions.add(q1);
        Question q2 = new Question("Telmo Riofrio", "hace 1 hora", "¿Es mi bebé corto para su edad?","Bebes","10 Me Gusta","15 Respuestas");
        questions.add(q2);
        Question q3 = new Question("Lorenzo Perez", "hace 1 hora", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum nibh metus, pulvinar non nulla vel?","Bebes","10 Me Gusta","15 Respuestas");
        questions.add(q3);
        Question q4 = new Question("Rafael Nevarez", "hace 2 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum nibh metus, pulvinar non nulla vel?","Bebes","10 Me Gusta","15 Respuestas");
        questions.add(q4);
        Question q5 = new Question("Daniel Gavilanes", "hace 3 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit?","Bebes","10 Me Gusta","15 Respuestas");
        questions.add(q5);
        Question q6 = new Question("David Lolin", "hace 4 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum nibh metus, pulvinar non nulla vel?","Bebes","10 Me Gusta","15 Respuestas");
        questions.add(q6);
        Question q7 = new Question("Juan Constantine", "hace 5 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit?","Bebes","10 Me Gusta","15 Respuestas");
        questions.add(q7);
        Question q8 = new Question("Ronald Gonzales", "hace 6 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum nibh metus, pulvinar non nulla vel?","Bebes","10 Me Gusta","15 Respuestas");
        questions.add(q8);
        Question q9 = new Question("Joe Sarzosa", "hace 7 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit?","Bebes","10 Me Gusta","15 Respuestas");
        questions.add(q9);
        Question q10 = new Question("Mauricio Reina", "hace 8 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum nibh metus, pulvinar non nulla vel?","Bebes","10 Me Gusta","15 Respuestas");
        questions.add(q10);
        Question q11 = new Question("Rafael Tamayo", "hace 9 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit?","Bebes","10 Me Gusta","15 Respuestas");
        questions.add(q11);
        Question q12 = new Question("Erwin Bravo", "hace 10 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum nibh metus, pulvinar non nulla vel?","Bebes","10 Me Gusta","15 Respuestas");
        questions.add(q12);
        Question q13 = new Question("Xavier Quimi", "hace 11 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit?","Bebes","10 Me Gusta","15 Respuestas");
        questions.add(q13);
        Question q14 = new Question("Carlos Choez", "hace 12 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum nibh metus, pulvinar non nulla vel?","Bebes","10 Me Gusta","15 Respuestas");
        questions.add(q14);
        Question q15 = new Question("Jorge Ortega", "hace 13 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit?","Bebes","10 Me Gusta","15 Respuestas");
        questions.add(q15);
    }

    private void populateInfoGuru(ArrayList arrayOfInfoGuru){
        PadreGuru q1 = new PadreGuru("Pedro Xavier", "hace 45 minutos", "¿Cómo y cuándo hablar con los niños acerca de las enfermedades que puedan tener?");
        arrayOfInfoGuru.add(q1);
        PadreGuru q2 = new PadreGuru("Telmo Riofrio", "hace 1 hora", "¿Es mi bebé corto para su edad?");
        arrayOfInfoGuru.add(q2);
        /*Question q3 = new Question("Lorenzo Perez", "hace 1 hora", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum nibh metus, pulvinar non nulla vel?");
        questions.add(q3);
        Question q4 = new Question("Rafael Nevarez", "hace 2 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum nibh metus, pulvinar non nulla vel?");
        questions.add(q4);
        Question q5 = new Question("Daniel Gavilanes", "hace 3 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit?");
        questions.add(q5);
        Question q6 = new Question("David Lolin", "hace 4 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum nibh metus, pulvinar non nulla vel?");
        questions.add(q6);
        Question q7 = new Question("Juan Constantine", "hace 5 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit?");
        questions.add(q7);
        Question q8 = new Question("Ronald Gonzales", "hace 6 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum nibh metus, pulvinar non nulla vel?");
        questions.add(q8);
        Question q9 = new Question("Joe Sarzosa", "hace 7 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit?");
        questions.add(q9);
        Question q10 = new Question("Mauricio Reina", "hace 8 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum nibh metus, pulvinar non nulla vel?");
        questions.add(q10);
        Question q11 = new Question("Rafael Tamayo", "hace 9 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit?");
        questions.add(q11);
        Question q12 = new Question("Erwin Bravo", "hace 10 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum nibh metus, pulvinar non nulla vel?");
        questions.add(q12);
        Question q13 = new Question("Xavier Quimi", "hace 11 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit?");
        questions.add(q13);edddddddddddresssd0
        Question q14 = new Question("Carlos Choez", "hace 12 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum nibh metus, pulvinar non nulla vel?");
        questions.add(q14);
        Question q15 = new Question("Jorge Ortega", "hace 13 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit?");
        questions.add(q15);*/
    }

    public void ask(View view) {
        Intent intent = new Intent(this, AskActivity.class);
        startActivity(intent);
    }

}
