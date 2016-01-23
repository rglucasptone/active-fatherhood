package com.rglucapstone.activefatherhood.activities;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.rglucapstone.activefatherhood.R;

import java.util.ArrayList;

/**
 * Created by ronald on 09/01/16.
 */
public class SignUpActivity extends Activity{
    private Button button;
    ArrayList selectedCategories;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        button = (Button) findViewById(R.id.btn_acept);
        View view;
        // add button listener
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                selectedCategories = new ArrayList();

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                /*alertDialogBuilder.setTitle(R.string.message_preferences);*/
                alertDialogBuilder.setMultiChoiceItems(R.array.list_categories, null, new DialogInterface.OnMultiChoiceClickListener() {
                    public void onClick(DialogInterface dialog, int which, boolean isCheked) {
                        if (isCheked) {
                            //selectedCategories.add(which);
                        } else if (selectedCategories.contains(which)) {
                            selectedCategories.remove(Integer.valueOf(which));
                        }
                    }
                })
                        .setPositiveButton(R.string.btn_aceptar_dialog, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                SignUpActivity.this.finish();
                                Intent intent = new Intent(context, HomeActivity.class);
                                startActivityForResult(intent, 0);
                            }
                        })
                   /* .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // if this button is clicked, just close
                            // the dialog box and do nothing
                            dialog.cancel();
                        }
                    })*/;
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
    }

    public void keepOn(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
