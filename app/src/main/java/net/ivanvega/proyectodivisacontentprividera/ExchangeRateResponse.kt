package net.ivanvega.proyectodivisacontentprividera

data class ExchangeRateResponse(
    val base: String,
    val date: String,
    val rates: ExchangeRate
)
