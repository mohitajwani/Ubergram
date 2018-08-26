package com.mohitajwani.ubergram.network.api;

import android.util.Log;

import com.mohitajwani.ubergram.Ubergram;
import com.mohitajwani.ubergram.interfaces.CurrentActivityListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Mohit on 25/08/18.
 */
public abstract class Api {

    private static final String TAG = Api.class.getSimpleName();

    private static final int TIMEOUT = 60000;

    static final String MAIN_BASE_URL = "https://api.flickr.com/";
    private static final String CHARSET_NAME = "UTF-8";
    private static final String API_KEY = "3e7cc266ae2b0e0d78e279ce8e361736";

    static final String GET_SEARCH_IMAGES_URL = "services/rest/?method=flickr.photos.search&api_key=" +
            API_KEY + "&format=json&nojsoncallback=1&safe_search=1";

    protected Ubergram ubergram;
    private String postParams = "";

    private HttpURLConnection conn;
    private boolean isConnectionClosedbyUser;

    public enum RequestMethod {
        GET, POST, PUT, DELETE;
    }

    Api(Ubergram ubergramInstance) {
        this.ubergram = ubergramInstance;
    }

    public enum ContentType {
        MIME_APPLICATION_X_WWW_FORM_URLENCODED, APPLICATION_JSON;

        public String toString() {
            switch (this) {
                case MIME_APPLICATION_X_WWW_FORM_URLENCODED:
                    return "application/x-www-form-urlencoded;charset=UTF-8";
                case APPLICATION_JSON:
                    return "application/json";
            }
            return "application/x-www-form-urlencoded;charset=UTF-8";
        }
    }

    ;

    private String uri;

    protected String getUri() {
        return uri;
    }

    void setUri(String uri) {
        this.uri = uri;
    }

    Object execute(RequestMethod requestMethod, ContentType contentType, String postData) throws IOException, NoSuchAlgorithmException {
        if (postData != null) {
            postParams = postData;
        }

        JSONObject jsonObject;
        URL url = new URL(uri);
        //Log.i(TAG,"++URI is as follows: " + uri);

        System.setProperty("http.keepAlive", "false");
        conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(TIMEOUT);
        conn.setReadTimeout(TIMEOUT);
        conn.setRequestMethod(requestMethod.name());
        conn.setRequestProperty("Charset", CHARSET_NAME);
        try {
            if (requestMethod == RequestMethod.POST) {
                Log.d(TAG, "REQUEST = " + uri + postParams);
                byte[] data = postParams.getBytes();
                conn.setRequestProperty("Content-Type", contentType.toString());
                conn.setRequestProperty("Content-Length", "" + Integer.toString(data.length));
                conn.setUseCaches(false);
                conn.setDoOutput(true);

                final DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
                wr.write(data);
                wr.flush();
                wr.close();
            }

            InputStream in = new BufferedInputStream(conn.getInputStream());

            String result = readStream(in).trim();

            Log.d(TAG, "REQUEST ++result = " + result);
            //Added this case to convert JSON Array to Object.

            Object object;
            try {
                if (result.startsWith("{")) {
                    jsonObject = new JSONObject("" + result);
                    object = jsonObject;

                } else {
                    object = result;

                }
            } catch (JSONException e) {
                return result;
            }
            return object;

        } catch (SocketException | SocketTimeoutException | UnknownHostException e) {
            if (!isConnectionClosedbyUser) {
                CurrentActivityListener currentActivityListener = ubergram.getCurrentActivityListener();
                if (currentActivityListener != null) {
                    currentActivityListener.onConnectionFailure(uri);
                    //Log.d(TAG,"Connection Failure Exception");
                }
            }
            return new JSONObject();

        } finally {
            conn.disconnect();
        }
    }

    private String readStream(InputStream in) throws IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                builder.append(line + "\n");
            }

        } catch (SocketException | SocketTimeoutException e) {
            throw e;

        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return builder.toString();
    }

    public void stop() {
        isConnectionClosedbyUser = true;
        conn.disconnect();
    }
}
