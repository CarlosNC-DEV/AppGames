package com.wscauca.retooneapp.model.local

data class UsuariosDb (
    var nombres: String,
    var apellidos: String,
    var correo: String,
    var password: String
)

data class UsuariosDbConsulta (
    var id: Int,
    var nombres: String,
    var apellidos: String,
    var correo: String,
    var password: String
)