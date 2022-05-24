package com.trkgames.batalhapokemon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.trkgames.batalhapokemon.databinding.ActivitySelectPokemonBinding
import com.trkgames.batalhapokemon.databinding.ActivityTutorialBinding

class Tutorial : AppCompatActivity() {

    private lateinit var binding: ActivityTutorialBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityTutorialBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.buttonCont.setOnClickListener {
            val intent = Intent(this, BattlePokemon::class.java)
            startActivity(intent)
            finish()
        }
    }
}