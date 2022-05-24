package com.trkgames.batalhapokemon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.trkgames.batalhapokemon.databinding.ActivityLoserBinding
import com.trkgames.batalhapokemon.databinding.ActivityWinnerBinding

class Loser : AppCompatActivity() {

    private lateinit var binding: ActivityLoserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoserBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)




        binding.buttonOutro.setOnClickListener {
            val intent = Intent(this, SelectPokemon::class.java)
            startActivity(intent)
            finish()
        }

        binding.buttonHomeLoser.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

        }








    }

    override fun onBackPressed() {
        val erroErik = Toast.makeText(this,
            "Sistema anti-erik ATIVADO, so da pra voltar pela navegaçao de botoes", Toast.LENGTH_SHORT)
        erroErik.show()
    }

}