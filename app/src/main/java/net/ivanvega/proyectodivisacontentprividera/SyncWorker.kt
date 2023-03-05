package net.ivanvega.proyectodivisacontentprividera

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import java.util.*

class SyncWorker(private val context: Context, params: WorkerParameters) : Worker(context, params) {

    companion object {
        private const val TAG = "SyncWorker"
    }

    override fun doWork(): Result {
        Log.i(TAG, "Sincronización iniciada")

        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://api.exchangeratesapi.io/latest?base=USD")
            .build()

        val response = try {
            client.newCall(request).execute()
        } catch (e: IOException) {
            Log.e(TAG, "Error de red", e)
            return Result.failure()
        }

        if (!response.isSuccessful) {
            Log.e(TAG, "Respuesta no exitosa")
            return Result.failure()
        }

        val gson = GsonBuilder().create()
        val body = response.body?.string()
        val result = gson.fromJson(body, ExchangeRateResponse::class.java)

        val timestamp = Date().time / 1000
        val contentValues = ContentValues().apply {
            put("timestamp", timestamp)
            result.rates?.let {
                put("USD", it.USD ?: 0.0)
                put("EUR", it.EUR ?: 0.0)
                put("JPY", it.JPY ?: 0.0)
                put("GBP", it.GBP ?: 0.0)
                put("AUD", it.AUD ?: 0.0)
                put("CAD", it.CAD ?: 0.0)
                put("CHF", it.CHF ?: 0.0)
                put("CNY", it.CNY ?: 0.0)
                put("HKD", it.HKD ?: 0.0)
            }
        }



        val uri: Uri = Uri.parse("content://net.ivanvega.proyectodivisacontentprovider.provider/monedas")
        val insertCount = context.contentResolver.insert(uri, contentValues)

        Log.i(TAG, "Sincronización completada. $insertCount registros insertados")

        return Result.success()
    }
}

