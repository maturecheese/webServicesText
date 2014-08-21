package com.example.mark_i5.webservicestest.tasks;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by mark-i5 on 20/08/2014.
 */
public class LastFMHelper {

    private String country;
    private String metro;
    private static final String lastFMGeoMetroUrl =
            "http://ws.audioscrobbler.com/2.0/?method=geo.getmetrotrackchart&api_key=2e3208a5e0ebcdb6624b4daf9ab954ac&format=json";
    private static final int HTTP_STATUS_OK = 200;
    private static byte[] buff = new byte[1024];
    private static String LOGTAG = "LastFMHelper";

    public void setCountry(String country){
        this.country = country;
    }
    public String getCountry(){
        return this.country;
    }
    public void setMetro(String metro){
        this.metro = metro;
    }
    public String getMetro(){
        return this.metro;
    }

    public static String getLastFMGeoMetroUrl(String... params){
        String url = lastFMGeoMetroUrl + "&metro=" + params[0] +"&country=" + params[1];
        return url;
    }

    public static class ApiException extends Exception{
        public ApiException(String msg){
            super(msg);
        }
        public ApiException(String msg, Throwable throwable){
            super(msg, throwable);
        }

    }

    protected static synchronized String downloadFromServer(String... params) throws ApiException{
        String returnValue = null;
        String url = getLastFMGeoMetroUrl(params);
        Log.d(LOGTAG, "fetching url data: " + url);

        HttpClient httpClient = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);

        try{
            HttpResponse response = httpClient.execute(request);
            StatusLine responseStatus = response.getStatusLine();
            if(responseStatus.getStatusCode() != HTTP_STATUS_OK){
                throw new ApiException("invalid response from last fm: \t" + responseStatus);
            }
            HttpEntity httpEntity = response.getEntity();
            InputStream ist = httpEntity.getContent();
            ByteArrayOutputStream content = new ByteArrayOutputStream();

            int readCount = 0;
            while((readCount = ist.read(buff))!= -1){
                content.write(buff,0,readCount);
            }
            returnValue = new String (content.toByteArray());




        }
        catch( Exception e){
            throw new ApiException("Problem connecting to the server " + e.getMessage(), e);
        }

        return returnValue;

    }



}
