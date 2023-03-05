package net.ivanvega.proyectodivisacontentprividera

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ExchangeRateApi {
    @GET("api/v7/convert?q={query}&compact=ultra&apiKey={apiKey}")
    suspend fun getExchangeRate(
        @Path("query") query: String,
        @Path("apiKey") apiKey: String
    ): ExchangeRateResponse

    companion object {
        private const val BASE_URL = "https://free.currconv.com/"

        fun create(): ExchangeRateApi {
            val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }
            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ExchangeRateApi::class.java)
        }
    }
}
