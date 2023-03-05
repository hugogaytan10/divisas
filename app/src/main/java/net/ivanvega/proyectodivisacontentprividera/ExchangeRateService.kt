package net.ivanvega.proyectodivisacontentprividera

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ExchangeRateService {
    private const val BASE_URL = "https://v6.exchangerate-api.com/v6/3b7559f6631dad88fdcd33ba/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: ExchangeRateApi = retrofit.create(ExchangeRateApi::class.java)
}
