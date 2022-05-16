package com.trkgames.batalhapokemon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.trkgames.batalhapokemon.databinding.ActivityMainBinding
import com.trkgames.batalhapokemon.databinding.ActivityNameTreinerBinding

class NameTreiner : AppCompatActivity() {

    private lateinit var binding: ActivityNameTreinerBinding
    private lateinit var nametrainer: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNameTreinerBinding.inflate(layoutInflater)
            val view = binding.root
                setContentView(view)

        binding.buttonContinue.setOnClickListener {

            nametrainer = binding.nameInput


                if (verificar() == false) {
                    val intent = Intent(this, SelectPokemon::class.java)
                    intent.putExtra("name", "${nametrainer.text}")
                    startActivity(intent)
                    finish()

                }
        }

        binding.buttonBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                    finish()
        }

    }

    fun verificar():Boolean {
        val erroErik = Toast.makeText(this,
            "Sistema anti-erik ATIVADO, digite por favor seu nome", Toast.LENGTH_SHORT)
        var erro = false

        if (nametrainer.text.isEmpty()){
            erroErik.show()
            erro = true
        }

        return erro
    }


    override fun onBackPressed() {

    }

}
