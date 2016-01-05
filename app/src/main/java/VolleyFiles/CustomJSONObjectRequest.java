package VolleyFiles;

/**
 * Created by user on 9/1/2015.
 */


import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CustomJSONObjectRequest extends JsonObjectRequest {
    Map<String, String> mParams;

    public CustomJSONObjectRequest(int method, String url, JSONObject jsonRequest,
                                   Response.Listener<JSONObject> listener,
                                   Response.ErrorListener errorListener) {
        super(method, url, jsonRequest, listener, errorListener);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json; charset=utf-8");
        return headers;
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {

        return mParams;
    }

    @Override
    public RetryPolicy getRetryPolicy() {
        // here you can write a custom retry policy

        return super.getRetryPolicy();
    }

    public void setPar(String un, String em, String con, String ps, String cps) {
        mParams = new HashMap<String, String>();
        Log.d("param", un+" "+em+" "+con);
        mParams.put("username", un);
        mParams.put("email", em);
        mParams.put("phone", con);
        mParams.put("passwd", ps);
        mParams.put("cpasswd", cps);
    }
}