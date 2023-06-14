package com.wscauca.retooneapp.ui.home.cartas

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.wscauca.retooneapp.R
import com.wscauca.retooneapp.databinding.ActivityCartasBinding
import com.wscauca.retooneapp.model.local.CartasDb
import com.wscauca.retooneapp.model.local.HistorialDb
import com.wscauca.retooneapp.model.local.db.ManagerHelperDb
import com.wscauca.retooneapp.ui.MainActivity
import com.wscauca.retooneapp.utilidades.preferencias.AppGames
import com.wscauca.retooneapp.utilidades.preferencias.AppGames.Companion.preferencias
import java.util.Random

class CartasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCartasBinding
    private lateinit var managerHelperDb: ManagerHelperDb

    private var cartasAndPoder = mutableListOf(
        CartasDb(60, "https://static.wikia.nocookie.net/espokemon/images/e/e3/Squirtle.png/revision/latest/thumbnail/width/360/height/360?cb=20160309230820"),
        CartasDb(45, "https://static.wikia.nocookie.net/espokemon/images/d/d7/Wartortle.png/revision/latest/thumbnail/width/360/height/360?cb=20140207202657"),
        CartasDb(89, "https://static.wikia.nocookie.net/espokemon/images/4/41/Blastoise.png/revision/latest?cb=20220608115725"),
        CartasDb(92, "https://static.wikia.nocookie.net/espokemon/images/0/0b/Snorlax.png/revision/latest/scale-to-width-down/1200?cb=20160904204605")
    )

    private var cartasAndPoderTwo = mutableListOf(
        CartasDb(60, "https://static.wikia.nocookie.net/espokemon/images/e/e3/Squirtle.png/revision/latest/thumbnail/width/360/height/360?cb=20160309230820"),
        CartasDb(45, "https://static.wikia.nocookie.net/espokemon/images/d/d7/Wartortle.png/revision/latest/thumbnail/width/360/height/360?cb=20140207202657"),
        CartasDb(89, "https://static.wikia.nocookie.net/espokemon/images/4/41/Blastoise.png/revision/latest?cb=20220608115725"),
        CartasDb(92, "https://static.wikia.nocookie.net/espokemon/images/0/0b/Snorlax.png/revision/latest/scale-to-width-down/1200?cb=20160904204605")
    )

    var puntuacion: Int = 0
    var random = Random()
    var miContador: CountDownTimer ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managerHelperDb = ManagerHelperDb(this)

        binding.txtPuntos.text = "${puntuacion} Puntos"

        binding.btnVerdadero.setOnClickListener {
            verificarCartasAndPoder(true)
        }

        binding.btnFalso.setOnClickListener {
            verificarCartasAndPoder(false)
        }

        cargarContador()
        barajarCartas()
    }

    private fun cargarContador() {
        miContador = object : CountDownTimer(30000, 1000){
            override fun onTick(millisUntilFinished: Long) {
                binding.txtTiempo.text = "${millisUntilFinished / 1000} \n Segundos"
            }

            override fun onFinish() {
                miContador?.cancel()
                cargarAlert()
            }

        }.start()
    }


    private fun barajarCartas() {

        var cartasAndPoderRandom = random.nextInt(cartasAndPoder.size)
        var cartasAndPoderFound = cartasAndPoder[cartasAndPoderRandom]

        var cartasAndPoderTwoRandom = random.nextInt(cartasAndPoderTwo.size)
        var cartasAndPoderTwoFound = cartasAndPoderTwo[cartasAndPoderTwoRandom]

        Glide.with(this)
            .load(cartasAndPoderFound.image)
            .into(binding.imgOne)
        binding.txtPoderOne.text = "Poder: ${cartasAndPoderFound.poder} Hp"

        Glide.with(this)
            .load(cartasAndPoderTwoFound.image)
            .into(binding.imgTwo)
        binding.txtPoderTwo.text = "Poder: ${cartasAndPoderTwoFound.poder} Hp"

    }

    private fun verificarCartasAndPoder(respuesta: Boolean) {
        var poderOneFound = binding.txtPoderOne.text.toString()
        var poderTwoFound = binding.txtPoderTwo.text.toString()

        var esCorrecto = poderOneFound == poderTwoFound

        if (esCorrecto && respuesta || !esCorrecto && !respuesta){
            puntuacion++
            binding.txtPuntos.text = "${puntuacion} Puntos"
        }

        barajarCartas()
    }

    private fun cargarAlert(){
        val alert = Dialog(this)

        alert.setContentView(R.layout.dialog_ganador_stropper)

        val txtPuntosObtenidos = alert.findViewById<TextView>(R.id.txtPuntosObtenidos)
        val btnJugarNuevamente = alert.findViewById<Button>(R.id.btnJugarNuevamente)
        val btnGuardar = alert.findViewById<Button>(R.id.btnGuardar)
        val btnIrInicio = alert.findViewById<Button>(R.id.btnIrInicio)

        txtPuntosObtenidos.text = "${puntuacion} \n Puntos Obtenidos"

        btnJugarNuevamente.setOnClickListener {
            puntuacion = 0
            barajarCartas()
            cargarContador()
            alert.dismiss()
        }

        btnGuardar.setOnClickListener {
            val historialVal = HistorialDb(preferencias.getIdUsuario(), puntuacion, "30", "Cartas")
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