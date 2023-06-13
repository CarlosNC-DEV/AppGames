package com.wscauca.retooneapp.model

class Constantes {
    companion object {
        val DB_NAME: String = "JugeosApp"
        val DB_VERSION: Int = 1

        val TBL_USUARIOS: String = "Usuarios"
        val ID_USUARIOS: String = "id"
        val NOMBRES_USUARIO: String = "nombre"
        val APELLIDOS_USUARIO: String = "apellido"
        val CORREO_USUARIO: String = "correo"
        val PASSWORD_USUARIO: String = "password"



        val CREATE_TBL_USUARIOS: String = "CREATE TABLE $TBL_USUARIOS ( " +
                "$ID_USUARIOS INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$NOMBRES_USUARIO TEXT, " +
                "$APELLIDOS_USUARIO TEXT, " +
                "$CORREO_USUARIO TEXT, " +
                "$PASSWORD_USUARIO TEXT)"

        val QUERY_USUARIOS: String = "SELECT * FROM $TBL_USUARIOS"

        val TBL_HISTORIAL: String = "Historial"
        val ID_HISTORIAL: String = "id"
        val ID_USUARIO_HISTORIAL: String = "idUsuario"
        val PUNTAJE_HISTORIAL: String = "puntaje"
        val TIEMPO_HISTORIAL: String = "tiempo"
        val JUEGO_HISTORIAL: String = "juego"

        val CREATE_TBL_HISTORIAL: String = "CREATE TABLE $TBL_HISTORIAL ( " +
                "$ID_HISTORIAL INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$ID_USUARIO_HISTORIAL INTEGER, " +
                "$PUNTAJE_HISTORIAL TEXT, " +
                "$TIEMPO_HISTORIAL TEXT, " +
                "$JUEGO_HISTORIAL TEXT)"

        val QUERY_HISTORIAL: String = "SELECT * FROM $TBL_HISTORIAL"


    }
}