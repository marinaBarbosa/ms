package com.example.marin.ms_pb_17.Currency;

/**
 * Created by marina on 24/11/2017.
 */

import retrofit2.Call;
import retrofit2.http.GET;

public interface CurrencyService {
    @GET("latest")
    Call<CurrencyParse> loadCurrencyExchange();
}