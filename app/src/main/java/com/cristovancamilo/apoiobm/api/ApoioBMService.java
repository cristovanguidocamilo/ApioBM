package com.cristovancamilo.apoiobm.api;

import com.cristovancamilo.apoiobm.model.Camaras;
import com.cristovancamilo.apoiobm.model.EscalaAbate;
import com.cristovancamilo.apoiobm.model.AcompanhaAbate;
import com.cristovancamilo.apoiobm.model.EstoqueBloqueado;
import com.cristovancamilo.apoiobm.model.QuantidadesLote;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApoioBMService {

    @GET("/datasnap/rest/TSM_BM/EstoqueBloqueado")
    Call<List<EstoqueBloqueado>> recuperarEstoqueBloqueado();

    @GET("/datasnap/rest/TSM_BM/Camaras")
    Call<List<Camaras>> recuperarCamaras();

    @GET("/datasnap/rest/TSM_BM/Acompanha")
    Call<List<AcompanhaAbate>> recuperarAcompanhaAbate();

    @GET("/datasnap/rest/TSM_BM/EscalaAbate")
    Call<List<EscalaAbate>> recuperarEscalaAbate();

    @GET("/datasnap/rest/TSM_BM/EscalaAbate/{num_lote}")
    Call<List<QuantidadesLote>> recuperarQuantidadesLote(@Path("num_lote") String num_lote);

}
