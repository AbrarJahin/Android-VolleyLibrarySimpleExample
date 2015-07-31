package com.appnucleus.volleyLibrarySimpleExample;

import org.json.JSONObject;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

public class Activity_Main extends Activity
{
    private TextView responseText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        responseText = (TextView)findViewById(R.id.txtDisplay);

        String  url = "http://api.androidhive.info/volley/person_object.json";      //It is a JSON Link, so it will work
        //url         = "http://httpbin.org/html";                                  //-this url is a HTML Link, so it will not work - detail in the illustrative example in github

        //Showing Dialogue........
        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        //Creating JSON request and use the data
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.GET, url, null, new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response)
            {
                Log.d("App", response.toString());
                responseText.setText("Response:" + " " +response.toString());
                pDialog.hide();
            }
        },
        new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                VolleyLog.d("App", "Error: " + error.getMessage());
                // hide the progress dialog
                pDialog.hide();
            }
        });
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjReq);
    }
}