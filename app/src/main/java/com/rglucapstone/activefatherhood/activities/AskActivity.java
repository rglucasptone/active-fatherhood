package com.rglucapstone.activefatherhood.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.rglucapstone.activefatherhood.R;

/**
 * Created by ronald on 15/12/15.
 */
public class AskActivity extends AppCompatActivity {
    private Button button_asking;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask);

        // toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_action);
        toolbar.setTitle("Agregar pregunta");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /**
         * Luego de realizar la pregunta la muestra en el listado
         */
        button_asking = (Button) findViewById(R.id.btn_asking);
        View view;
        button_asking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // SignUpActivity.this.finish();
                Intent intent = new Intent(context, HomeActivity.class);
                startActivityForResult(intent, 0);
            }
        });
    }
    /* Pedndiente revisar uso*/
    public void asking(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    /* Action Back*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
