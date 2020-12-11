package com.andrewtrainor.compoundapp;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Driver {

    private String api_url = "https://api.yelp.com/v3/businesses/search?";
    private String my_token ="yudmsKPzscsPbfFo0CPQLGkzENmb6KjlPAxGUd34eVR0fYZ8eRIFdn5wzlLmikkyIXrPCzMnEHIhxfsm0s0r4_dsqYySlHBLnCi6PoN1hkEkKAZV0InZ5V5tTOPPX3Yx";

    public void getNewHot(final BusinessData returnVal){
        String url = api_url + "attributes=hot_and_new&location=Miami";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    ArrayList<Business> Business_list = new ArrayList<>();
                    JSONArray response_array = response.getJSONArray("businesses");
                    for(int array_len = 0; array_len < response_array.length(); array_len++) {

                        String name = (String) response_array.getJSONObject(array_len).get("name");
                        String business_img = (String) response_array.getJSONObject(array_len).get("image_url");
                        Business business= new Business(name, business_img);

                        Business_list.add(business);
                    }
                    returnVal.BusinessRequest(Business_list);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.toString());
            }
        }){
            @Override
            public Map < String, String > getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization", "bearer " + my_token);
                return params;
            }
        };

        SecondaryActivity.getVolley().addToRequestQueue(jsonObjectRequest);
    }

    public void getGyms(final BusinessData returnVal){
        String url = api_url + "location=Port_Saint_Lucie&term=gym";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    ArrayList<Business> Business_list = new ArrayList<>();
                    JSONArray response_array = response.getJSONArray("businesses");
                    for(int array_len = 0; array_len < response_array.length(); array_len++) {

                        String name = (String) response_array.getJSONObject(array_len).get("name");
                        String business_img = (String) response_array.getJSONObject(array_len).get("image_url");
                        Business business= new Business(name, business_img);

                        Business_list.add(business);
                    }
                    returnVal.BusinessRequest(Business_list);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.toString());
            }
        }){
            @Override
            public Map < String, String > getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization", "bearer " + my_token);
                return params;
            }
        };

        SecondaryActivity.getVolley().addToRequestQueue(jsonObjectRequest);
    }

    public void getRestaurants(final BusinessData returnVal){
        String url = api_url + "term=restaurants&location=Miami";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    ArrayList<Business> Business_list = new ArrayList<>();
                    JSONArray response_array = response.getJSONArray("businesses");
                    for(int array_len = 0; array_len < response_array.length(); array_len++) {

                        String name = (String) response_array.getJSONObject(array_len).get("name");
                        String business_img = (String) response_array.getJSONObject(array_len).get("image_url");
                        Business business= new Business(name, business_img);

                        Business_list.add(business);
                    }
                    returnVal.BusinessRequest(Business_list);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.toString());
            }
        }){
            @Override
            public Map < String, String > getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization", "bearer " + my_token);
                return params;
            }
        };

        SecondaryActivity.getVolley().addToRequestQueue(jsonObjectRequest);
    }

}
