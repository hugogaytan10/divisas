package net.ivanvega.proyectodivisacontentprividera
data class ExchangeRate(
    val base: String?,
    val date: String?,
    var USD: Double? = null,
    var EUR: Double? = null,
    var JPY: Double? = null,
    var GBP: Double? = null,
    var AUD: Double? = null,
    var CAD: Double? = null,
    var CHF: Double? = null,
    var CNY: Double? = null,
    var HKD: Double? = null
)
