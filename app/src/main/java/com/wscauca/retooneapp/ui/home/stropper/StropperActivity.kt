package com.wscauca.retooneapp.ui.home.stropper

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import com.wscauca.retooneapp.R
import com.wscauca.retooneapp.databinding.ActivityStropperBinding
import com.wscauca.retooneapp.model.local.HistorialDb
import com.wscauca.retooneapp.model.local.db.ManagerHelperDb
import com.wscauca.retooneapp.ui.MainActivity
import com.wscauca.retooneapp.utilidades.preferencias.AppGames.Companion.preferencias
import java.util.Random

class StropperActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStropperBinding
    private lateinit var managerHelperDb: ManagerHelperDb

    var colores = mutableListOf("Amarillo", "Azul", "Rojo", "Negro", "Verde")
    var textColores = mutableListOf(
        Color.YELLOW,
        Color.BLUE,
        Color.RED,
        Color.BLACK,
        Color.GREEN
    )

    var miContador : CountDownTimer ?= null
    var tiempo : Any = 0
    var puntos: Int = 0
    var random = Random()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStropperBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managerHelperDb = ManagerHelperDb(this)

        binding.txtPuntos.text = "${puntos} Puntos"

        binding.btnVerdadero.setOnClickListener {
            verificarColorAndText(true)
        }

        binding.btnFalso.setOnClickListener {
            verificarColorAndText(false)
        }

        cargarColoresAndText()
        cargarTiempo()
    }

    private fun verificarColorAndText(respuesta: Boolean) {
        var colorFound = binding.txtColorAndText.text.toString()
        var colorAndTextFound = binding.txtColorAndText.currentTextColor

        var esCorrecto = (colorFound == getColorName(colorAndTextFound))
        if(esCorrecto && respuesta || !esCorrecto && !respuesta){
            puntos++
            binding.txtPuntos.text = "${puntos} Puntos"
        }
        cargarColoresAndText()
    }

    private fun getColorName(color: Int): String {
        return when(color){

            Color.YELLOW -> "Amarillo"
            Color.BLUE -> "Azul"
            Color.RED -> "Rojo"
            Color.BLACK -> "Negro"
            Color.GREEN -> ""

            else -> "Verde"
        }
    }

    private fun cargarTiempo() {
        miContador = object : CountDownTimer(30000, 1000){
            override fun onTick(millisUntilFinished: Long) {
                tiempo = millisUntilFinished / 1000
                binding.txtTiempo.text = "${tiempo} \n Segundos"
            }

            override fun onFinish() {
                cargarAlertFinal()
            }
        }.start()
    }

    private fun cargarColoresAndText() {

        var colorRandom = random.nextInt(colores.size)
        var colorFound = colores[colorRandom]

        var textColorRandom = random.nextInt(textColores.size)
        var textColorFound = textColores[textColorRandom]

        binding.txtColorAndText.text = colorFound
        binding.txtColorAndText.setTextColor(textColorFound)

        var colorSelect = colores.removeAt(colorRandom)
        colores.add(colorSelect)

    }

    private fun cargarAlertFinal(){
        var alert = Dialog(this@StropperActivity)

        alert.setContentView(R.layout.dialog_ganador_stropper)

        var txtPuntosObtenidos = alert.findViewById<TextView>(R.id.txtPuntosObtenidos)
        var btnJugarNuevamente = alert.findViewById<Button>(R.id.btnJugarNuevamente)
        var btnGuardar = alert.findViewById<Button>(R.id.btnGuardar)
        var btnIrInicio = alert.findViewById<Button>(R.id.btnIrInicio)

        txtPuntosObtenidos.text = "${puntos} \n Puntos Obtenidos"

        btnJugarNuevamente.setOnClickListener {
            puntos = 0
            cargarColoresAndText()
            cargarTiempo()
            alert.dismiss()
        }

        btnGuardar.setOnClickListener {
            val historialVal = HistorialDb(preferencias.getIdUsuario(), puntos, "30", "Stropper")
            val resul = managerHelperDb.registrarHistorial(historialVal)
            if(resul > 0){
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }

        btnIrInicio.setOnClickListener {
            alert.dismiss()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        alert.setCancelable(false)
        alert.show()
    }

}