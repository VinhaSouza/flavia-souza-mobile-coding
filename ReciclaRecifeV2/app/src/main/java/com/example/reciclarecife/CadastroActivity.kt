package com.example.reciclarecife

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.reciclarecife.databinding.ActivityCadastroVagaBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CadastroActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCadastroVagaBinding
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroVagaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val edName = binding.edNome
        val edEmail = binding.edEmail
        val edTel = binding.edTel
        val btCadatrar = binding.button

        dbRef = FirebaseDatabase.getInstance().getReference("Membro")

        btCadatrar.setOnClickListener{
            val empName = edName.text.toString()
            val empEmail = edEmail.text.toString()
            val empTele = edTel.text.toString()

            if(empName.isEmpty()){
                edName.error = "Por favor insira um nome"
            }
            if(empEmail.isEmpty()){
                edEmail.error = "Por favor insira um e-mail"
            }
            if(empTele.isEmpty()){
                edTel.error = "Por favor insira um número de telefone"
            }

            val empId = dbRef.push().key!!

            val empregador = userModelo(empId, empName, empEmail, empTele)

            dbRef.child(empId).setValue(empregador)
                .addOnCompleteListener{
                    Toast.makeText(this, "Cadastro realizado", Toast.LENGTH_SHORT).show()

                    edName.text.clear()
                    edEmail.text.clear()
                    edTel.text.clear()

                }.addOnFailureListener{err ->
                    Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_SHORT).show()
                }
        }

    }


}