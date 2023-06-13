package com.wscauca.retooneapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wscauca.retooneapp.R
import com.wscauca.retooneapp.databinding.ActivityMainBinding
import com.wscauca.retooneapp.ui.home.adivinando.AdivinandoActivity
import com.wscauca.retooneapp.ui.home.cartas.CartasActivity
import com.wscauca.retooneapp.ui.home.historial.HistorialActivity
import com.wscauca.retooneapp.ui.home.login.login.LoginActivity
import com.wscauca.retooneapp.ui.home.stropper.StropperActivity
import com.wscauca.retooneapp.utilidades.preferencias.AppGames.Companion.preferencias

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtUsuario.text = preferencias.getUsuario()
        binding.cerrarSesion.setOnClickListener {
            preferencias.wripe()
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.irAdivinando.setOnClickListener {
            startActivity(Intent(this, AdivinandoActivity::class.java))
        }

        binding.irStropper.setOnClickListener {
            startActivity(Intent(this, StropperActivity::class.java))
        }

        binding.irCartas.setOnClickListener {
            startActivity(Intent(this, CartasActivity::class.java))
        }

        binding.irHistorial.setOnClickListener {
            startActivity(Intent(this, HistorialActivity::class.java))
        }


    }

}