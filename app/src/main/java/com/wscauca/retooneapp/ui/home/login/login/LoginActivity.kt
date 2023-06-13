package com.wscauca.retooneapp.ui.home.login.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.wscauca.retooneapp.R
import com.wscauca.retooneapp.databinding.ActivityLoginBinding
import com.wscauca.retooneapp.model.local.db.ManagerHelperDb
import com.wscauca.retooneapp.ui.MainActivity
import com.wscauca.retooneapp.ui.home.login.regitro.ReistroActivity
import com.wscauca.retooneapp.utilidades.preferencias.AppGames.Companion.preferencias

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var managerHelperDb: ManagerHelperDb

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(preferencias.getRecuerdame() == true){
            startActivity(Intent(this, MainActivity::class.java))
        }

        managerHelperDb = ManagerHelperDb(this)

        binding.txtIrRegistro.setOnClickListener {
            startActivity(Intent(this, ReistroActivity::class.java))
        }

        binding.btnIniciarSesion.setOnClickListener {
            var correo = binding.edtCorreo.text.toString()
            var password = binding.edtPassword.text.toString()

            if(correo.isNotEmpty() && password.isNotEmpty()){
                val resul = managerHelperDb.inciarSesionUsuario(correo, password)
                if(resul != null){
                    preferencias.saveIdUsuario(resul.id)
                    preferencias.saveUsuario(resul.nombres)
                    preferencias.saveRecuerdame(binding.checkRecuerdame.isChecked)
                    startActivity(Intent(this, MainActivity::class.java))
                }else{
                    Snackbar.make(binding.root, "Credenciales Incorrectas", Snackbar.LENGTH_SHORT).show()
                }
            }else{
                Snackbar.make(binding.root, "Todos los datos son requeridos", Snackbar.LENGTH_SHORT).show()
            }

        }



    }
}