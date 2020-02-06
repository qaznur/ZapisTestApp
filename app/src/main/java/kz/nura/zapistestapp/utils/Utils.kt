package kz.nura.zapistestapp.utils

import android.content.Context
import android.net.ConnectivityManager
import java.util.*

fun getTengeSymbol(): String {
    val kz = Locale("kaz", "KZ")
    val currency = Currency.getInstance(kz)
    return currency.getSymbol(kz)
}

fun isInetOK(context: Context): Boolean {
    val conManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
            as ConnectivityManager
    val ni = conManager.activeNetworkInfo
    return ni != null && ni.isConnected
}

