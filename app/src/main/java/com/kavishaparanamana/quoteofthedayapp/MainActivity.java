package com.kavishaparanamana.quoteofthedayapp;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.kavishaparanamana.quoteofthedayapp.controllers.EmailSenderController;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EmailSenderController quoteController;
    AsyncTask<?, ?, ?> runningTask;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);



        setSupportActionBar(toolbar);

       FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editTxt = (EditText)findViewById(R.id.editText);
                String textVal =editTxt.getText().toString();
                if(textVal.matches("")){
                    Snackbar.make(view, "Please add recipients email address above.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }else{
                    String[]  emails=(textVal).split(",");
                    if (runningTask != null)
                        runningTask.cancel(true);
                    runningTask = new SendOperation(emails);
                    runningTask.execute();
                }




            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    private final class SendOperation extends AsyncTask<Object, Void, String> {
        String[] emails ;

        SendOperation(String[] emails){
            super();
            this.emails=emails;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Sending emails....");
            progressDialog.setCancelable(false);
            progressDialog.show();

        }


        @Override
        protected String doInBackground(Object... params) {
            quoteController = new EmailSenderController();
            try{
                quoteController.getQuote();
              // Quote quoteObj= quoteController.getQuote();
                //quoteController.sendEmail(emails,quoteObj.getTitle(),quoteObj.getQuote(),true,getString(R.string.email_user),getString(R.string.email_pass));

               // quoteController.sendEmail(emails,"Test emails ","",true,getString(R.string.email_user),getString(R.string.email_pass));
            } catch (Exception e){
                Log.e("getQuote", e.getMessage(), e);
            }

            return "TEST Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            progressDialog.dismiss();

        }
    }
}
