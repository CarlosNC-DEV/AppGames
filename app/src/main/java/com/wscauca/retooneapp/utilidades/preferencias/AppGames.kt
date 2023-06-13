package com.wscauca.retooneapp.utilidades.preferencias

import android.app.Application

class AppGames : Application() {
    companion object{
        lateinit var preferencias: Preferencias
    }

    override fun onCreate() {
        super.onCreate()
        preferencias = Preferencias(applicationContext)
    }
}