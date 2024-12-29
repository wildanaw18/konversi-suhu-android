package com.example.konversisuhu

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var inputSuhu: EditText
    private lateinit var spinnerInput: Spinner
    private lateinit var btnKonversi: Button
    private lateinit var txtHasil: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inisialisasi komponen
        inputSuhu = findViewById(R.id.inputSuhu)
        spinnerInput = findViewById(R.id.spinnerInput)
        btnKonversi = findViewById(R.id.btnKonversi)
        txtHasil = findViewById(R.id.txtHasil)

        // Data Spinner untuk satuan suhu
        val satuanSuhu = arrayOf("Celsius (C)", "Fahrenheit (F)", "Reamur (R)", "Kelvin (K)")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, satuanSuhu)
        spinnerInput.adapter = adapter

        // Event tombol konversi
        btnKonversi.setOnClickListener {
            konversiSuhu()
        }
    }

    private fun konversiSuhu() {
        val suhuInput = inputSuhu.text.toString()
        if (suhuInput.isEmpty()) {
            Toast.makeText(this, "Masukkan suhu terlebih dahulu", Toast.LENGTH_SHORT).show()
            return
        }

        val suhu = suhuInput.toDouble()
        val satuanAsal = spinnerInput.selectedItem.toString()

        val hasilKonversi = when (satuanAsal) {
            "Celsius (C)" -> """
                Fahrenheit: ${celsiusToFahrenheit(suhu)}
                Reamur: ${celsiusToReamur(suhu)}
                Kelvin: ${celsiusToKelvin(suhu)}
            """.trimIndent()
            "Fahrenheit (F)" -> """
                Celsius: ${fahrenheitToCelsius(suhu)}
                Reamur: ${fahrenheitToReamur(suhu)}
                Kelvin: ${fahrenheitToKelvin(suhu)}
            """.trimIndent()
            "Reamur (R)" -> """
                Celsius: ${reamurToCelsius(suhu)}
                Fahrenheit: ${reamurToFahrenheit(suhu)}
                Kelvin: ${reamurToKelvin(suhu)}
            """.trimIndent()
            "Kelvin (K)" -> """
                Celsius: ${kelvinToCelsius(suhu)}
                Fahrenheit: ${kelvinToFahrenheit(suhu)}
                Reamur: ${kelvinToReamur(suhu)}
            """.trimIndent()
            else -> "Error"
        }

        txtHasil.text = "Hasil Konversi:\n$hasilKonversi"
    }

    // Fungsi konversi dari Celsius
    private fun celsiusToFahrenheit(c: Double) = (c * 9 / 5) + 32
    private fun celsiusToReamur(c: Double) = c * 4 / 5
    private fun celsiusToKelvin(c: Double) = c + 273.15

    // Fungsi konversi dari Fahrenheit
    private fun fahrenheitToCelsius(f: Double) = (f - 32) * 5 / 9
    private fun fahrenheitToReamur(f: Double) = (f - 32) * 4 / 9
    private fun fahrenheitToKelvin(f: Double) = (f - 32) * 5 / 9 + 273.15

    // Fungsi konversi dari Reamur
    private fun reamurToCelsius(r: Double) = r * 5 / 4
    private fun reamurToFahrenheit(r: Double) = (r * 9 / 4) + 32
    private fun reamurToKelvin(r: Double) = (r * 5 / 4) + 273.15

    // Fungsi konversi dari Kelvin
    private fun kelvinToCelsius(k: Double) = k - 273.15
    private fun kelvinToFahrenheit(k: Double) = (k - 273.15) * 9 / 5 + 32
    private fun kelvinToReamur(k: Double) = (k - 273.15) * 4 / 5
}
