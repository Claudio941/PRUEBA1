package com.example.restaurant.modelo

import android.os.Parcel
import android.os.Parcelable

class Cazuela(var cantidad: Int = 0) {
    fun agregarCantidad(cant: Int) {
        cantidad += cant
    }

    fun calcularTotal(): Int {
        return cantidad * 10000
    }
}

class Pastel(var cantidad: Int = 0) {
    fun agregarCantidad(cant: Int) {
        cantidad += cant
    }

    fun calcularTotal(): Int {
        return cantidad * 12000
    }
}

class TotalPagar(val cazuela: Cazuela, val pastel: Pastel) {
    fun calcularTotalSinPropina(): Int {
        return cazuela.calcularTotal() + pastel.calcularTotal()
    }

    fun calcularTotalConPropina(): Int {
        val totalSinPropina = calcularTotalSinPropina()
        val propina = totalSinPropina * 0.10
        return totalSinPropina + propina.toInt()
    }
}


