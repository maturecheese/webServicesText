package com.example.mark_i5.webservicestest;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.view.LayoutInflater;
import android.util.Log;


import com.example.mark_i5.webservicestest.tasks.LastFMWebApi;


public class WebServicesActivity extends Activity {

    private EditText country_field;
    private EditText city_field;
    private Button search_button;
    private LayoutInflater layoutInflater;
    private WebServicesActivity thisActivity;
    private static String LOGTAG = "WebServicesActivity";
    private InputMethodManager inMgr;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_services);
        this.inMgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        this.country_field = (EditText) findViewById(R.id.country_edit);
        this.city_field = (EditText)findViewById(R.id.city_edit);
        this.search_button = (Button) findViewById(R.id.button_search);
        this.layoutInflater = getLayoutInflater().from(this);
        this.thisActivity = this;

        this.search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    String[] params = new String[2];

                    params[0] = city_field.getText().toString();
                    params[1] = country_field.getText().toString();
                    inMgr.hideSoftInputFromInputMethod(search_button.getWindowToken(),0);
                    LastFMWebApi apiTask = new LastFMWebApi(WebServicesActivity.this);
                    apiTask.execute(params);
                } catch (Exception e) {

                    Log.e(LOGTAG, "failed", e);
                    e.printStackTrace();
                }


            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.web_services, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
