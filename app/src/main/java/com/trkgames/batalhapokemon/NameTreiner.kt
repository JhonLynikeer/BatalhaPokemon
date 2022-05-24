package com.trkgames.batalhapokemon

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.trkgames.batalhapokemon.databinding.ActivityMainBinding
import com.trkgames.batalhapokemon.databinding.ActivityNameTreinerBinding

class NameTreiner : AppCompatActivity() {

    private lateinit var binding: ActivityNameTreinerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNameTreinerBinding.inflate(layoutInflater)
            val view = binding.root
                setContentView(view)

        binding.buttonContinue.setOnClickListener {

                if (verificar() == false) {
                    val intent = Intent(this
                        , SelectPokemon::class.java)
                    saveName()
                    startActivity(intent)
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

        if (binding.nameInput.text.isEmpty()){
            erroErik.show()
            erro = true
        }
        return erro
    }

    fun saveName(){
        val nometreinador: String = binding.nameInput.text.toString()
        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply {
            putString("String_key", nometreinador)
        }.apply()

    }

    override fun onBackPressed() {
        val erroErik = Toast.makeText(this,
            "Sistema anti-erik ATIVADO, so da pra voltar pela navegaçao de botoes", Toast.LENGTH_SHORT)
        erroErik.show()
    }

}
