package com.wscauca.retooneapp.utilidades.preferencias

import android.content.Context

class Preferencias(context: Context) {

    var storage = context.getSharedPreferences("GameAppData", 0)

    fun saveRecuerdame(recuerdame: Boolean){
        storage.edit().putBoolean("Recuerdame", recuerdame).apply()
    }

    fun getRecuerdame(): Boolean{
        return storage.getBoolean("Recuerdame", false)
    }

    fun saveIdUsuario(id: Int){
        storage.edit().putInt("IdUsuario", id).apply()
    }

    fun getIdUsuario(): Int{
        return storage.getInt("IdUsuario", 0)
    }

    fun saveUsuario(usuario: String){
        storage.edit().putString("Usuario", usuario).apply()
    }

    fun getUsuario(): String?{
        return storage.getString("Usuario", null)
    }

    fun wripe(){
        storage.edit().clear().apply()
    }

}