package com.andrewtrainor.compoundapp.BusinessData;

import com.andrewtrainor.compoundapp.BusinessData.Business;
import com.andrewtrainor.compoundapp.BusinessData.BusinessData;
import com.andrewtrainor.compoundapp.SecondaryActivity;
import com.andrewtrainor.compoundapp.UserData.Profile;
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

    public static HashMap<String, Profile> users = new HashMap<>();
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
                        String business_address = (String) response_array.getJSONObject(array_len).getJSONObject("location").getJSONArray("display_address").get(0);
                        String business_type = (String) response_array.getJSONObject(array_len).getJSONArray("categories").getJSONObject(0).get("title");
                        String business_phone = (String) response_array.getJSONObject(array_len).get("display_phone");
                        double business_rating = (Double) response_array.getJSONObject(array_len).get("rating");

                        double business_lat = 1;
                        double business_lon = 1;

                        Business business= new Business(name, business_img, business_lat, business_lon, business_address, business_rating, business_phone, business_type);

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
        String url = api_url + "location=Miami&term=gym";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    ArrayList<Business> Business_list = new ArrayList<>();
                    JSONArray response_array = response.getJSONArray("businesses");
                    for(int array_len = 0; array_len < response_array.length(); array_len++) {

                        String name = (String) response_array.getJSONObject(array_len).get("name");
                        String business_img = (String) response_array.getJSONObject(array_len).get("image_url");
                        String business_address = (String) response_array.getJSONObject(array_len).getJSONObject("location").getJSONArray("display_address").get(0);
                        String business_type = (String) response_array.getJSONObject(array_len).getJSONArray("categories").getJSONObject(0).get("title");
                        String business_phone = (String) response_array.getJSONObject(array_len).get("display_phone");
                        double business_lat = 1;
                        double business_lon = 1;
                        double business_rating = (Double) response_array.getJSONObject(array_len).get("rating");

                        Business business= new Business(name, business_img, business_lat, business_lon, business_address, business_rating, business_phone, business_type);

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
                        String business_address = (String) response_array.getJSONObject(array_len).getJSONObject("location").getJSONArray("display_address").get(0);
                        String business_type = (String) response_array.getJSONObject(array_len).getJSONArray("categories").getJSONObject(0).get("title");
                        double business_rating = (Double) response_array.getJSONObject(array_len).get("rating");
                        String business_phone = (String) response_array.getJSONObject(array_len).get("display_phone");
                        double business_lat = 1;
                        double business_lon = 1;

                        Business business= new Business(name, business_img, business_lat, business_lon, business_address, business_rating,business_phone, business_type);

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

    public void getByName(String title, final BusinessData returnVal){
        String url = api_url + "term="+ title +"&location=Boca_Raton";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    ArrayList<Business> Business_list = new ArrayList<>();
                    JSONArray response_array = response.getJSONArray("businesses");
                    for(int array_len = 0; array_len < response_array.length(); array_len++) {

                        String name = (String) response_array.getJSONObject(array_len).get("name");
                        String business_address = (String) response_array.getJSONObject(array_len).getJSONObject("location").getJSONArray("display_address").get(0);
                        String business_img = (String) response_array.getJSONObject(array_len).get("image_url");
                        Double business_lat = (Double) response_array.getJSONObject(array_len).getJSONObject("coordinates").getDouble("latitude");
                        Double business_lon = (Double) response_array.getJSONObject(array_len).getJSONObject("coordinates").getDouble("longitude");
                        String business_type = (String) response_array.getJSONObject(array_len).getJSONArray("categories").getJSONObject(0).get("title");
                        double business_rating = (Double) response_array.getJSONObject(array_len).get("rating");
                        String business_phone = (String) response_array.getJSONObject(array_len).get("display_phone");


                        Business business= new Business(name, business_img, business_lat, business_lon, business_address, business_rating, business_phone, business_type);

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

    public void getByLoc(String title, String location, final BusinessData returnVal){
        String url = api_url + "term="+ title +"&location=" + location;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    ArrayList<Business> Business_list = new ArrayList<>();
                    JSONArray response_array = response.getJSONArray("businesses");
                    for(int array_len = 0; array_len < response_array.length(); array_len++) {

                        String name = (String) response_array.getJSONObject(array_len).get("name");
                        String business_address = (String) response_array.getJSONObject(array_len).getJSONObject("location").getJSONArray("display_address").get(0);
                        String business_img = (String) response_array.getJSONObject(array_len).get("image_url");
                        Double business_lat = (Double) response_array.getJSONObject(array_len).getJSONObject("coordinates").getDouble("latitude");
                        Double business_lon = (Double) response_array.getJSONObject(array_len).getJSONObject("coordinates").getDouble("longitude");
                        String business_type = (String) response_array.getJSONObject(array_len).getJSONArray("categories").getJSONObject(0).get("title");
                        double business_rating = (Double) response_array.getJSONObject(array_len).get("rating");
                        String business_phone = (String) response_array.getJSONObject(array_len).get("display_phone");


                        Business business= new Business(name, business_img, business_lat, business_lon, business_address, business_rating, business_phone, business_type);

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


    public void update(Profile profile) {
        Profile _user = users.get(profile.getUsername());
        _user.setFavorites(profile.getFavorites());
    }

    public Profile create(String username, String password) {
        Profile user = new Profile(username, password);
        users.put(username, user);
        return user;
    }

}
