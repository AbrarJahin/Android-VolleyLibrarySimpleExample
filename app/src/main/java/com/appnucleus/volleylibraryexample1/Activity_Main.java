package com.appnucleus.volleylibraryexample1;

/*
*       To Import Volley-
*           1. Go to "Project" perspective from "Android" perspective from left side top panel (under your project name)
 *          2. Pest "volley.jar" in the libs folder
 *          3. Right click on "volley.jar" and click add as a library from the bottom most of the poped up menu
 *          4. You are done - now you can change the perspective
* */

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

        String  url = "http://echo.jsontest.com/key/value/one/two";     //It is a JSON Link, so it will work
        //url         = "http://httpbin.org/html";                      //-this url is a HTML Link, so it will not work

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