package cl.santotomas.iniciosesion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import cl.santotomas.iniciosesion.Interface.ApiService;
import cl.santotomas.iniciosesion.Util.SpecialtyAdapter;
import cl.santotomas.iniciosesion.modelo.Specialty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("LOGLISTA", "Abriendo Activity Lista");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        getSpecialties();
    }

    private void getSpecialties(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://cita.dyi.cl/api/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        ApiService apiService = retrofit.create(ApiService.class);
        Call<List<Specialty>> call = apiService.getSpecialties();
        call.enqueue(new Callback<List<Specialty>>() {
            @Override
            public void onResponse(Call<List<Specialty>> call, Response<List<Specialty>> response) {
                if( !response.isSuccessful() ){
                    Toast.makeText(ListaActivity.this, "Codigo de Error: "+response.code(), Toast.LENGTH_LONG).show();
                    return;
                }
                ListView especialidad_lista_item = findViewById(R.id.especialidad_lista_item);
                List<Specialty> specialties = response.body();
                SpecialtyAdapter specialtyAdapter = new SpecialtyAdapter(ListaActivity.this, R.layout.especialidad_lista_item, specialties);
                especialidad_lista_item.setAdapter(specialtyAdapter);
            }

            @Override
            public void onFailure(Call<List<Specialty>> call, Throwable t) {
                Toast.makeText(ListaActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}