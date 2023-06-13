package com.wscauca.retooneapp.model.local

data class HistorialDb (
    var idUsuario: Int,
    var puntaje: Int,
    var tiempo: String,
    var juego: String,
)

data class HistorialDbConsulta (
    var id: Int,
    var idUsuario: Int,
    var puntaje: Int,
    var tiempo: String,
    var juego: String,
)