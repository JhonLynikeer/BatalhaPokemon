package com.trkgames.batalhapokemon

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.trkgames.batalhapokemon.databinding.ActivityPokemonStatusBinding
import com.trkgames.batalhapokemon.databinding.ActivitySelectPokemonBinding

class PokemonStatus : AppCompatActivity() {

    private lateinit var binding: ActivityPokemonStatusBinding
    private lateinit var images: ImageView
    var num = 0

    override fun onCreate(savedInstanceState: Bundle?) {



        super.onCreate(savedInstanceState)

        binding = ActivityPokemonStatusBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val dados: Bundle? = intent.extras
        val treinerName = dados?.getString("name")
        val image = dados?.getString("id")

        var id = image

        if (id == "1"){
            bonba()
        }


       // veri()

       // bonba()

        //binding.buttonYes.setOnClickListener {
        //    binding.imagePokemoStatus.setImageResource(R.drawable.image_bulbasaur)
        //}


        binding.buttonNo.setOnClickListener {
            val intent = Intent(this, SelectPokemon::class.java)
            startActivity(intent)
        }
    }



    fun veri(){

        if (num == 1){
            bonba()
        }


    }

  fun bonba(){
      binding.imagePokemoStatus.setImageResource(R.drawable.image_bulbasaur)
  }



}