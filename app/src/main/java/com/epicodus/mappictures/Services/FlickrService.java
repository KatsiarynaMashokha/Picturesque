package com.epicodus.mappictures.Services;

import com.epicodus.mappictures.Constants;
import com.epicodus.mappictures.models.Picture;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by katsiarynamashokha on 11/8/17.
 */

public class FlickrService {
    public static void findPictures(String lat, String lon, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.FLICKR_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.FLICKR_API_KEY_PARAMETER, Constants.FLICKR_API_KEY)
                .addQueryParameter(Constants.FLICKR_LAT_PARAMETER, lat)
                .addQueryParameter(Constants.FLICKR_LON_PARAMETER, lon)
                .addQueryParameter(Constants.FLICKR_FORMAT_PARAMTER, Constants.FLICKR_FORMAT_VALUE)
                .addQueryParameter(Constants.FLICKR_NO_JSON_PARAMETER, Constants.FLICKR_NO_JSON_VALUE)
                .addQueryParameter(Constants.FLICKR_EXTRAS_PARAMETER, Constants.FLICKR_EXTRAS_VALUE);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Picture> processPictures(Response response) {
        ArrayList<Picture> pictures = new ArrayList<>();
        try {
            String jsonData = response.body().string();
            JSONObject flickrJson = new JSONObject(jsonData);
            JSONObject photosObject = flickrJson.getJSONObject("photos");
            JSONArray photoArray = photosObject.getJSONArray("photo");
            for (int i = 0; i < photoArray.length(); i++) {
                JSONObject currentPhoto = photoArray.getJSONObject(i);
                String title = currentPhoto.getString("title");
                if(!currentPhoto.has("url_s")) {
                    continue;
                }

                String url = currentPhoto.getString("url_s");
                    Picture picture = new Picture(url, title);
                    pictures.add(picture);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return pictures;
    }




}
