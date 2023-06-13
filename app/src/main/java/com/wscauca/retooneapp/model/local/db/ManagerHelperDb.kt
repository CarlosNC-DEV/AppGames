package com.wscauca.retooneapp.model.local.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.wscauca.retooneapp.model.Constantes
import com.wscauca.retooneapp.model.local.HistorialDb
import com.wscauca.retooneapp.model.local.HistorialDbConsulta
import com.wscauca.retooneapp.model.local.UsuariosDb
import com.wscauca.retooneapp.model.local.UsuariosDbConsulta

class ManagerHelperDb(context: Context) {
    private var helperDb: HelperDb
    var db : SQLiteDatabase ?= null

    init {
        helperDb = HelperDb(context)
    }

    fun openBdWr(){
        db = helperDb.writableDatabase
    }

    fun openBdRd(){
        db = helperDb.readableDatabase
    }

    fun closeDb(){
        if(db != null){
            db?.close()
        }
    }

    fun registrarUsuarios(usuariosDb: UsuariosDb) : Long{
        openBdWr()
        val values = ContentValues()
        values.put(Constantes.NOMBRES_USUARIO, usuariosDb.nombres)
        values.put(Constantes.APELLIDOS_USUARIO, usuariosDb.apellidos)
        values.put(Constantes.CORREO_USUARIO, usuariosDb.correo)
        values.put(Constantes.PASSWORD_USUARIO, usuariosDb.password)
        val resul = db?.insert(Constantes.TBL_USUARIOS, null, values)
        return resul!!
    }

    fun inciarSesionUsuario(correo: String, password:String): UsuariosDbConsulta? {
        openBdWr()
        var usuarioVal : UsuariosDbConsulta ?= null
        val query = "${Constantes.QUERY_USUARIOS} WHERE ${Constantes.CORREO_USUARIO}='$correo' AND ${Constantes.PASSWORD_USUARIO}='$password'"
        val resul = db?.rawQuery(query, null)
        if (resul != null && resul.moveToFirst()){
            usuarioVal = UsuariosDbConsulta(
                resul.getInt(0),
                resul.getString(1),
                resul.getString(2),
                resul.getString(3),
                resul.getString(4),
            )
        }
        return usuarioVal
    }

    fun registrarHistorial(historialDb: HistorialDb) : Long{
        openBdWr()
        val values = ContentValues()
        values.put(Constantes.ID_USUARIO_HISTORIAL, historialDb.idUsuario)
        values.put(Constantes.PUNTAJE_HISTORIAL, historialDb.puntaje)
        values.put(Constantes.TIEMPO_HISTORIAL, historialDb.tiempo)
        values.put(Constantes.JUEGO_HISTORIAL, historialDb.juego)
        val resul = db?.insert(Constantes.TBL_HISTORIAL, null, values)
        return resul!!
    }

    fun historialFound(idUsario: Int, juego:String): ArrayList<HistorialDbConsulta> {
        openBdWr()
        var historialVal : ArrayList<HistorialDbConsulta> = ArrayList()
        val query = "${Constantes.QUERY_HISTORIAL} WHERE ${Constantes.ID_USUARIO_HISTORIAL}='$idUsario' AND ${Constantes.JUEGO_HISTORIAL}='$juego'"
        val resul = db?.rawQuery(query, null)
        if (resul!!.moveToFirst()){
            do {
                var historial = HistorialDbConsulta(
                    resul.getInt(0),
                    resul.getInt(1),
                    resul.getInt(2),
                    resul.getString(3),
                    resul.getString(4),
                )
                historialVal.add(historial)
            }while (resul.moveToNext())

        }
        return historialVal
    }

}