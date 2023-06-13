package com.wscauca.retooneapp.ui.home.adivinando

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Button
import com.wscauca.retooneapp.R
import com.wscauca.retooneapp.databinding.ActivityAdivinandoBinding
import com.wscauca.retooneapp.model.local.HistorialDb
import com.wscauca.retooneapp.model.local.db.ManagerHelperDb
import com.wscauca.retooneapp.ui.MainActivity
import com.wscauca.retooneapp.utilidades.preferencias.AppGames.Companion.preferencias
import java.util.Random

class AdivinandoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdivinandoBinding
    private lateinit var managerHelperDb: ManagerHelperDb

    var intentos: Int = 5
    var miContador : CountDownTimer ?= null
    var miNumRandom : Int = 0
    var random = Random()
    var tiempo : Any = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdivinandoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managerHelperDb = ManagerHelperDb(this)

        binding.txtIntentos.text = "Tienes ${intentos} intentos"

        binding.btnLanzarNum.setOnClickListener {
            var edtNumSelect = binding.edtNumSelect.text.toString()
            if(edtNumSelect.isNotEmpty()){
                if (edtNumSelect.toInt() == miNumRandom){
                    miContador?.cancel()
                    cargarDailogGanador()
                }else{
                    if (intentos == 1){
                        miContador?.cancel()
                        cargarDailogPerdedor()
                    }else{
                        intentos--
                        binding.txtIntentos.text = "Tienes ${intentos} intentos"
                        binding.edtNumSelect.text?.clear()
                    }
                }
            }else{
                binding.tlyNumSelect.error = "Campo Requerido"
            }
        }


        cargarTiempo()
        cargarNumRandom()
    }


    private fun cargarNumRandom() {
        miNumRandom = random.nextInt(100) + 1
        Log.e("TAG", "cargarNumRandom: $miNumRandom", )
    }

    private fun cargarTiempo() {
        miContador = object : CountDownTimer(30000, 1000){
            override fun onTick(millisUntilFinished: Long) {
                tiempo = millisUntilFinished / 1000
                binding.textTiempo.text = "${tiempo} \n Segundos"
            }

            override fun onFinish() {
                miContador?.cancel()
                cargarDailogPerdedor()
            }
        }.start()
    }

    private fun cargarDailogGanador() {
        val alert = Dialog(this@AdivinandoActivity)
        alert.setContentView(R.layout.dialog_ganador)
        var btnJugarNuevamente = alert.findViewById<Button>(R.id.btnJugarNuevamente)
        var btnGuardar = alert.findViewById<Button>(R.id.btnGuardar)
        var btnIrInicio = alert.findViewById<Button>(R.id.btnIrInicio)

        btnJugarNuevamente.setOnClickListener {
            intentos = 5
            binding.txtIntentos.text = "Tienes ${intentos} intentos"
            miNumRandom= 0
            alert.dismiss()
            cargarTiempo()
            cargarNumRandom()
        }

        btnGuardar.setOnClickListener {
            val historialVal = HistorialDb(preferencias.getIdUsuario(), 100, tiempo.toString(), "Adivinando")
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

    private fun cargarDailogPerdedor() {
        val alert = Dialog(this@AdivinandoActivity)
        alert.setContentView(R.layout.dialog_perdor)
        var btnJugarNuevamente = alert.findViewById<Button>(R.id.btnJugarNuevamente)
        var btnIrInicio = alert.findViewById<Button>(R.id.btnIrInicio)

        btnJugarNuevamente.setOnClickListener {
            intentos = 5
            binding.txtIntentos.text = "Tienes ${intentos} intentos"
            miNumRandom= 0
            alert.dismiss()
            cargarTiempo()
            cargarNumRandom()
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