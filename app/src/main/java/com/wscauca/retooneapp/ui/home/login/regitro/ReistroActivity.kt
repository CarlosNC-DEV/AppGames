package com.wscauca.retooneapp.ui.home.login.regitro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.wscauca.retooneapp.R
import com.wscauca.retooneapp.databinding.ActivityReistroBinding
import com.wscauca.retooneapp.model.local.UsuariosDb
import com.wscauca.retooneapp.model.local.db.ManagerHelperDb
import com.wscauca.retooneapp.ui.home.login.login.LoginActivity

class ReistroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReistroBinding
    private lateinit var managerHelperDb: ManagerHelperDb

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managerHelperDb = ManagerHelperDb(this)


        binding.btnregistrarUsuarios.setOnClickListener {
            registrarUsuario()
        }


    }

    private fun registrarUsuario() {
        var nombres = binding.edtNombre.text.toString()
        var apellidos = binding.edtApellidos.text.toString()
        var correo = binding.edtCorreo.text.toString()
        var password = binding.edtPassword.text.toString()
        if(nombres.isNotEmpty() && apellidos.isNotEmpty() && correo.isNotEmpty() && password.isNotEmpty()){
            val usuarioVal = UsuariosDb(nombres, apellidos, correo, password)
            val resul = managerHelperDb.registrarUsuarios(usuarioVal)
            if(resul > 0){
                startActivity(Intent(this, LoginActivity::class.java))
                binding.edtNombre.text.clear()
                binding.edtApellidos.text.clear()
                binding.edtCorreo.text.clear()
                binding.edtPassword.text.clear()
            }else{
                Snackbar.make(binding.root, "Error al tratar de registrarte", Snackbar.LENGTH_SHORT).show()
            }
        }else{
            Snackbar.make(binding.root, "Todos los datos son reuqeridos", Snackbar.LENGTH_SHORT).show()
        }
    }
}