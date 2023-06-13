package com.wscauca.retooneapp.utilidades.recyclers

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.wscauca.retooneapp.databinding.ItemsHistorialBinding
import com.wscauca.retooneapp.model.local.HistorialDbConsulta

class HistorialHolder(view: View) : ViewHolder(view) {

    val binding = ItemsHistorialBinding.bind(view)

    fun render(historialDbConsulta: HistorialDbConsulta){
        binding.txtPuntaje.text = "Puntaje: ${historialDbConsulta.puntaje}"
        binding.txtTiempo.text = "Tiempo: ${historialDbConsulta.tiempo}s"
        binding.txtJuego.text = "Juego: ${historialDbConsulta.juego}"
    }

}