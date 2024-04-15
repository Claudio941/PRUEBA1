package com.example.restaurant

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.restaurant.modelo.Cazuela
import com.example.restaurant.modelo.Pastel
import com.example.restaurant.modelo.TotalPagar

class MainActivity : AppCompatActivity() {
    private var etvalorCa: EditText? = null
    private var etvalorPA: EditText? = null
    private var tvTotalcaz: TextView? = null
    private var tvTotalpas: TextView? = null
    private var tvtotalcom: TextView? = null
    private var tvtotalProp: TextView? = null
    private var tvPagar: TextView? = null
    private var swpropina: Switch? = null

    private val cazuela = Cazuela()
    private val pastel = Pastel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        etvalorCa = findViewById<EditText>(R.id.etvalorCa)
        etvalorPA = findViewById<EditText>(R.id.etvalorPA)
        tvTotalcaz = findViewById<TextView>(R.id.tvTotalcaz)
        tvTotalpas = findViewById<TextView>(R.id.tvTotalpas)
        tvtotalcom = findViewById<TextView>(R.id.tvtotalcom)
        tvtotalProp = findViewById<TextView>(R.id.tvtotalProp)
        tvPagar = findViewById<TextView>(R.id.tvPagar)
        swpropina = findViewById<Switch>(R.id.swpropina)

        etvalorCa?.addTextChangedListener(textWatcher)
        etvalorPA?.addTextChangedListener(textWatcher)
    }

    private val textWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable?) {
            TotalPagar(swpropina?.isChecked == true)
        }
    }

    private fun TotalPagar(incluirPropina: Boolean) {
        var cant1 = etvalorCa?.text.toString().toIntOrNull() ?: 0
        cazuela.agregarCantidad(cant1)
        var cant2 = etvalorPA?.text.toString().toIntOrNull() ?: 0
        pastel.agregarCantidad(cant2)

        tvTotalcaz?.text = " ${cazuela.calcularTotal()}"
        tvTotalpas?.text = "${pastel.calcularTotal()}"
        tvtotalcom?.text = "${(cazuela.calcularTotal() + pastel.calcularTotal())}"
        tvtotalProp?.text =
            if (incluirPropina) "${((cazuela.calcularTotal() + pastel.calcularTotal()) * 0.10).toInt()}" else "0"
        tvPagar?.text =
            if (incluirPropina) "${((cazuela.calcularTotal() + pastel.calcularTotal()) * 1.10).toInt()}" else "${(cazuela.calcularTotal() + pastel.calcularTotal())}"
    }
}