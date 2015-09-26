package com.hari.development.faasosdelight;

import android.app.Application;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.hari.development.faasosdelight.util.LruBitmapCache;


/**
 * Created by development on 25/09/15.
 */
public class IssueTracker extends Application{

    private RequestQueue requestQueue;
    private static IssueTracker mInstance;
    private ImageLoader imageLoader;

    public static final String TAG = IssueTracker.class.getName();

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        requestQueue = Volley.newRequestQueue(getApplicationContext());
    }

    public static synchronized IssueTracker getInstance(){
        return mInstance;
    }

    public RequestQueue getRequestQueue(){
        return requestQueue;
    }

    public <T> void add(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (imageLoader == null) {
            imageLoader = new ImageLoader(this.requestQueue,
                    new LruBitmapCache());
        }
        return this.imageLoader;
    }


    public void cancel() {
        requestQueue.cancelAll(TAG);
    }

}

