package com.hari.development.faasosdelight;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private IssueTracker helper = IssueTracker.getInstance();
    private String API_URL = "https://faasos.0x10.info/api/faasos?type=json&query=list_menu";
    //ImageView mImageView;
    private CustomListAdapter adapter;
    private ListView listView;
    private ProgressDialog pDialog;

    List<Faassos> faassosMenuList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        faassosMenuList = new ArrayList<>();

        //mImageView = (ImageView)findViewById(R.id.main_bg);

        listView = (ListView) findViewById(R.id.list);
        adapter = new CustomListAdapter(this,faassosMenuList);
        listView.setAdapter(adapter);

        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();



        foodMenu();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void foodMenu(){


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                API_URL,
                null,
                new Response.Listener<JSONObject>() {

                    JSONArray name;
                    JSONObject itemList;
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Faassos faassos;
                            name = response.getJSONArray("menu");

                            for( int i = 0 ; i < name.length() - 1 ; i++  ){
                                itemList = name.getJSONObject(i);
                                faassos = new Faassos();
                                faassos.setName(itemList.getString("name"));
                                faassos.setCategory(itemList.getString("category"));
                                faassos.setSpice_meter(Integer.parseInt(itemList.getString("spice_meter")));
                                faassos.setImage(itemList.getString("image"));
                                faassos.setDescription(itemList.getString("description"));
                                faassos.setIs_veg(itemList.getString("is_veg"));
                                faassos.setPrice(Integer.parseInt(itemList.getString("price")));
                                faassos.setRating(Double.parseDouble(itemList.getString("rating")));

                                faassosMenuList.add(faassos);
                            }

                            for(Faassos i : faassosMenuList){
                                Log.v("asd",i.getName());
                                //loadImg(i.getImage());
                            }


                        }catch(Exception e){
                            //Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                            Log.v("asd",e.getMessage());
                            hidePDialog();
                        }
                        adapter.notifyDataSetChanged();
                        hidePDialog();
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        helper.add(jsonObjectRequest);
    }

   /* private void loadImg(String imageUrl) {
        // Retrieves an image specified by the URL, and displays it in the UI
        ImageRequest request = new ImageRequest(imageUrl,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        mImageView.setImageBitmap(bitmap);
                    }
                }, 0, 0, ImageView.ScaleType.CENTER_CROP, Bitmap.Config.ARGB_8888,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        imageError(error);
                    }
                });

        // we don't need to set the priority here;
        // ImageRequest already comes in with
        // priority set to LOW, that is exactly what we need.
        helper.add(request);
    }
    int mainColor = Color.parseColor("#FF5722");

    private void imageError(Exception e) {
        mImageView.setBackgroundColor(mainColor);
        e.printStackTrace();
    }
*/
   @Override
   public void onDestroy() {
       super.onDestroy();
       hidePDialog();
   }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }
}
