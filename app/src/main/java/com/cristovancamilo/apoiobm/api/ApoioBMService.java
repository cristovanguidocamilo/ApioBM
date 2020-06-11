package com.cristovancamilo.apoiobm.api;

import com.cristovancamilo.apoiobm.model.Camaras;
import com.cristovancamilo.apoiobm.model.EstoqueBloqueado;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApoioBMService {

    @GET("/datasnap/rest/TSM_BM/EstoqueBloqueado")
    Call<List<EstoqueBloqueado>> recuperarEstoqueBloqueado();

    @GET("/datasnap/rest/TSM_BM/Camaras")
    Call<List<Camaras>> recuperarCamaras();

}
