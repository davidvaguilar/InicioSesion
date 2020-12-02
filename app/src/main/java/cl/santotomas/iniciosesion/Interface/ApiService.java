package cl.santotomas.iniciosesion.Interface;

import java.util.List;

import cl.santotomas.iniciosesion.modelo.Specialty;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("specialties")
    Call<List<Specialty>> getSpecialties();


}
