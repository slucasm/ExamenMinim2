package dsa.eetac.upc.edu.examenminim2;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface APIRest {


    @GET("")
    Call<Cities> getCities();



    static APIRest createAPIRest() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        String BASE_URL = "https://do.diba.cat/api/dataset/municipis/format/json/pag-ini/1/pag-fi/11";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(APIRest.class);
    }
}
