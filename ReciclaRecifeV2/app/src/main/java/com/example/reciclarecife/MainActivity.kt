package com.example.reciclarecife

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.reciclarecife.databinding.ActivityMainBinding
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val edNome = binding.edUsuario
        val btLogin = binding.btLogin

        btLogin.setOnClickListener {
            if(edNome.text.toString()=="Admin") {
                val inten = Intent(this, CadastroActivity::class.java)
                startActivity(inten)
                val database = Firebase.database
                val myRef = database.getReference("teste")

                myRef.setValue("Hello, World!")
            }
            else{
                val inten = Intent(this, ListaActivity::class.java)
                startActivity(inten)

            }
        }

    }
}