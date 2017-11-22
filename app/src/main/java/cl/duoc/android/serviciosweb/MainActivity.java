package cl.duoc.android.serviciosweb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import cliente.custom.Categoria;
import cliente.custom.CategoriaService;
import cliente.github.GithubService;
import cliente.github.Repo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String URL_WS_CUSTOM = "http://192.168.1.38:8080/jersey-1.0/webapi/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void crearCategoria(View view) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_WS_CUSTOM)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CategoriaService service = retrofit.create(CategoriaService.class);
        Call<Categoria> call = service.crearCategoria("Android", "probando la creación desde Android");
        call.enqueue(new Callback<Categoria>() {
            @Override
            public void onResponse(Call<Categoria> call, Response<Categoria> response) {
                Categoria categoria = response.body();
                Log.v("Retrofit", categoria.getId()+"");
                Log.v("Retrofit", categoria.getNombre());
                Log.v("Retrofit", categoria.getDescripcion());
            }

            @Override
            public void onFailure(Call<Categoria> call, Throwable t) {
                t.printStackTrace();
                Log.v("RetrofitERROR", t.getMessage());
            }
        });
    }

    public void custom(View view) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_WS_CUSTOM)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CategoriaService service = retrofit.create(CategoriaService.class);
        Call<List<Categoria>> callLista = service.getCategorias();
        callLista.enqueue(new Callback<List<Categoria>>() {
            @Override
            public void onResponse(Call<List<Categoria>> call, Response<List<Categoria>> response) {
                Log.v("Retrofit", response.toString());
                List<Categoria> lista = response.body();
                for(Categoria c:lista) {
                    Log.v("Retrofit", c.getNombre());
                }
            }

            @Override
            public void onFailure(Call<List<Categoria>> call, Throwable t) {
                t.printStackTrace();
                Log.v("RetrofitERROR", t.getMessage());
            }
        });

    }

    public void github(View view) {
        Toast.makeText(MainActivity.this, "Llamando al WS de Github", Toast.LENGTH_LONG);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GithubService service = retrofit.create(GithubService.class);
        Call<List<Repo>> callLista = service.listRepos("stgoduoc");
        callLista.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                Log.v("Retrofit", response.toString());
                List<Repo> lista = response.body();
                for(Repo r:lista) {
                    Log.v("Retrofit", r.getName());
                    Log.v("Retrofit", r.getHtmlUrl());
                    Toast.makeText(MainActivity.this, r.getHtmlUrl(), Toast.LENGTH_LONG);
                }
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                t.printStackTrace();
                Log.v("RetrofitERROR", t.getMessage());
            }
        });

    }

}
