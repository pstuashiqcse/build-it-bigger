package com.mcc.buildit.utility;

import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.mcc.buildit.service.jokeGce.JokeGce;


import java.io.IOException;

public class RemoteJokeTask extends AsyncTask<Void, Void, String>{

    private static JokeGce jokeGce = null;

    private ResponseListener responseListener;

    public void setResponseListener(ResponseListener responseListener) {
        this.responseListener = responseListener;
    }

    @Override
    protected String doInBackground(Void... voids) {

        if(jokeGce == null) {
            JokeGce.Builder builder = new JokeGce.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });

            jokeGce = builder.build();
        }

        try {
            return jokeGce.todaysJoke().execute().getJoke();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        if(responseListener != null) {
            responseListener.onResponse(s);
        }
    }

    public interface ResponseListener {
        public void onResponse(String response);
    }
}
