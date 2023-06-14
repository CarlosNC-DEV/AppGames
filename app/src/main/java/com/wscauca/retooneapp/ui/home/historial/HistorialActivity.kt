package com.wscauca.retooneapp.ui.home.historial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.wscauca.retooneapp.R
import com.wscauca.retooneapp.databinding.ActivityHistorialBinding
import com.wscauca.retooneapp.model.local.db.ManagerHelperDb
import com.wscauca.retooneapp.utilidades.preferencias.AppGames.Companion.preferencias
import com.wscauca.retooneapp.utilidades.recyclers.HistorialAdapter

class HistorialActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistorialBinding
    private lateinit var managerHelperDb: ManagerHelperDb

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistorialBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managerHelperDb = ManagerHelperDb(this)

        val resulAdivinando = managerHelperDb.historialFound(preferencias.getIdUsuario(), "Adivinando")
        val rcvHistorialAdivinando = binding.rcvHistorialAdivinando
        rcvHistorialAdivinando.layoutManager =  LinearLayoutManager(this)
        rcvHistorialAdivinando.adapter  = HistorialAdapter(resulAdivinando)


        val resulStropper = managerHelperDb.historialFound(preferencias.getIdUsuario(), "Stropper")
        val rcvHistorialStropper = binding.rcvHistorialStropper
        rcvHistorialStropper.layoutManager =  LinearLayoutManager(this)
        rcvHistorialStropper.adapter  = HistorialAdapter(resulStropper)

        val resulCartas = managerHelperDb.historialFound(preferencias.getIdUsuario(), "Cartas")
        val rcvHistorialCartas = binding.rcvHistorialCartas
        rcvHistorialCartas.layoutManager =  LinearLayoutManager(this)
        rcvHistorialCartas.adapter  = HistorialAdapter(resulCartas)



    }
}