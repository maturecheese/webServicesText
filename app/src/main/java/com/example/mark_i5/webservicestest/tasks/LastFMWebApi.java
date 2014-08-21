package com.example.mark_i5.webservicestest.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.mark_i5.webservicestest.R;
import com.example.mark_i5.webservicestest.WebServicesActivity;
import com.example.mark_i5.webservicestest.data.Track;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by mark-i5 on 21/08/2014.
 */
public class LastFMWebApi extends AsyncTask<String, Integer, String> {

    private ProgressDialog progressDialog;
    private Context context;
    private WebServicesActivity activity;
    private static final String LOGTAG = "LastFMWebApi";


    public LastFMWebApi(WebServicesActivity activity){
        super();
        this.activity = activity;
        this.context = activity.getApplicationContext();
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d(LOGTAG, this.context.toString());
        progressDialog = ProgressDialog.show(this.activity, this.context.getResources().getString(R.string.searching)
                       , this.context.getResources().getString(R.string.searching_desc), true, false );
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            Log.d(LOGTAG, "background thread running: " + Thread.currentThread().getName());
            String result = LastFMHelper.downloadFromServer(params);
            return result;
        } catch (LastFMHelper.ApiException e) {
            e.printStackTrace();
            return null;
        }


    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        this.progressDialog.dismiss();
        //Log.d(LOGTAG, "response :\n\n" + result);

        String info = "found some results";
        if (result.length() == 0 ){
            info = "could not find any results";
        }
        Toast.makeText(this.context, info, Toast.LENGTH_LONG).show();

        ArrayList<Track> tracks = new ArrayList<Track>();

        try{
            JSONObject jsonObj = new JSONObject(result);
            JSONObject jsonTopTracks = jsonObj.getJSONObject("toptracks");
            JSONArray jsonTracks = jsonTopTracks.getJSONArray("track");

            for (int i = 0 ; i < jsonTracks.length(); i ++){
                JSONObject track = jsonTracks.getJSONObject(i);
                String name = track.getString("name");
                String artist = track.getString("artist");
                String artistUrl = track.getJSONObject("artist").getString("url");
                String trackUrl = track.getString("url");
                String imgUrl;
                try{
                    imgUrl = track.getJSONArray("image").getJSONObject(1).getString("text");
                }
                catch (Exception e){
                    imgUrl = null;
                }

                Track newTrack = new Track(name,artist,trackUrl,artistUrl,imgUrl, 0);
                tracks.add(newTrack);

            }

        }
        catch (Exception e){
            e.printStackTrace();
        }

        Log.d(LOGTAG, tracks.toString());

    }
}
