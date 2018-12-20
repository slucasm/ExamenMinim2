package dsa.eetac.upc.edu.examenminim2;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Adapter recycler;
    private RecyclerView recyclerView;

    //private APIRest apiRest;

    private DibaAPI dibaAPI;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recycler = new Adapter(this);
        recyclerView.setAdapter(recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        dibaAPI = DibaAPI.createDibaAPI();

        Intent intent = getIntent();


        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Waiting for the server");
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();

        getCities();

    }


    private void getCities(){
        Call<Cities> citiesCall = dibaAPI.getCities(1,11);
        citiesCall.enqueue(new Callback<Cities>() {
            @Override
            public void onResponse(Call<Cities> call, Response<Cities> response) {
                if (response.isSuccessful()){
                    Cities cities = response.body();

                    List<Element> elementList = cities.getElements();

                    if(elementList.size() != 0){
                        recycler.addElements(elementList);
                    }

                    progressDialog.hide();

                }else{
                    progressDialog.hide();

                    //Show the alert dialog
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);

                    alertDialogBuilder
                            .setTitle("Error")
                            .setMessage(response.message())
                            .setCancelable(false)
                            .setPositiveButton("OK", (dialog, which) -> finish());

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }
            }

            @Override
            public void onFailure(Call<Cities> call, Throwable t) {
                progressDialog.hide();

                //Show the alert dialog
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);

                alertDialogBuilder
                        .setTitle("Error")
                        .setMessage(t.getMessage())
                        .setCancelable(false)
                        .setPositiveButton("OK", (dialog, which) -> finish());

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
    }
}
