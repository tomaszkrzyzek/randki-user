package me.krzyzek.tomaszkrzyzek.randkiuser;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class GetFromDatabase extends AsyncTask<String, String, JSONArray> {

    static final String IMIE = "Imie";
    static final String NAZWISKO = "Nazwisko";
    static final String MIASTO = "Miasto";
    static final String WIEK = "Wiek";
    static final String PLEC = "Plec";
    static final String WZROST = "Wzrost";
    static final String ORIENTACJA = "Orientacja";
    static final String ZAWOD = "Zawod";
    static final String WYZNANIE = "Wyznanie";
    static final String BUDOWA_CIALA = "Budowa_Ciala";
    static final String PO_CO = "PoCo";
    static final String STAN_CYWILNY = "Stan_Cywilny";
    static final String PREFERENCJE = "Preferencje";
    static final String ALKOHOL = "Alkohol";

    static InputStream is = null;

    static JSONObject jObj = null;
    static JSONArray jsonArray = null;
    static String json = "";
    static String url;

    // constructor
    public GetFromDatabase() {

    }
    @Override
    protected JSONArray doInBackground(String... params) {


        url = "http://krzyzek.me/get.php";

        // Making HTTP request
        try {
            // defaultHttpClient
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet httpPost = new HttpGet(url);

            HttpResponse getResponse = httpClient.execute(httpPost);
            final int statusCode = getResponse.getStatusLine().getStatusCode();

            if (statusCode != HttpStatus.SC_OK) {
                Log.w(getClass().getSimpleName(),
                        "Error " + statusCode + " for URL " + url);
                return null;
            }

            HttpEntity getResponseEntity = getResponse.getEntity();

            //HttpResponse httpResponse = httpClient.execute(httpPost);
            //HttpEntity httpEntity = httpResponse.getEntity();
            is = getResponseEntity.getContent();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            Log.d("IO", e.getMessage().toString());
            e.printStackTrace();

        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            json = sb.toString();
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }

        // try parse the string to a JSON object
        try {
            jsonArray = new JSONArray(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        // return JSON String
        return jsonArray;


    }
    protected void onPostExecute(JSONArray jsonArray)
    {
        if(jsonArray != null){
            MainActivity.peopleArray = new ArrayList<String>();
            MainActivity.detailsArray = new ArrayList<Osoba>();

            for(int i = 0; i < jsonArray.length(); i++){
                try {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Osoba osoba = new Osoba(jsonObject.getString(IMIE), jsonObject.getString(NAZWISKO), jsonObject.getString(MIASTO),
                            jsonObject.getInt(WIEK), jsonObject.getString(PLEC), jsonObject.getInt(WZROST),
                            jsonObject.getString(ORIENTACJA), jsonObject.getString(ZAWOD), jsonObject.getString(WYZNANIE),
                            jsonObject.getString(BUDOWA_CIALA), jsonObject.getString(PO_CO), jsonObject.getString(STAN_CYWILNY),
                            jsonObject.getString(PREFERENCJE), jsonObject.getString(ALKOHOL));
                    String imie = jsonObject.getString(IMIE);
                    String nazwisko = jsonObject.getString(NAZWISKO);

                    if(MainActivity.selectAll == false && MainActivity.minWiek <= osoba.wiek && MainActivity.maxWiek >= osoba.wiek &&
                            MainActivity.orientacja.equals(osoba.orientacja) && MainActivity.miasto.equals(osoba.miasto) &&
                            MainActivity.plec.equals(osoba.plec)){

                        MainActivity.detailsArray.add(osoba);
                        MainActivity.peopleArray.add(imie + " " + nazwisko);
                    }
                    else if(MainActivity.selectAll == true){
                        MainActivity.detailsArray.add(osoba);
                        MainActivity.peopleArray.add(imie + " " + nazwisko);
                    }


//                    System.out.println(imie + " " + nazwisko);
                    MainActivity.adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}