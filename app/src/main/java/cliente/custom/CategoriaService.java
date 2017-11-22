package cliente.custom;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CategoriaService {

    @GET("categoria")
    Call<List<Categoria>> getCategorias();

    @FormUrlEncoded
    @POST("categoria")
    Call<Categoria> crearCategoria(@Field("nombre") String nombre, @Field("descripcion") String descripcion);


}
