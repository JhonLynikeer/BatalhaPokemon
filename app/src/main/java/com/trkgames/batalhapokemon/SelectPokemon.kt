package com.trkgames.batalhapokemon

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.graphics.toColor
import androidx.core.graphics.toColorInt
import com.trkgames.batalhapokemon.databinding.ActivityNameTreinerBinding
import com.trkgames.batalhapokemon.databinding.ActivitySelectPokemonBinding
import kotlin.properties.Delegates

class SelectPokemon : AppCompatActivity() {

    private lateinit var binding: ActivitySelectPokemonBinding

    val imgs = intArrayOf(
        R.drawable.image_caterpie,
        R.drawable.image_bulbasaur,
        R.drawable.image_charmeleon,
        R.drawable.image_pidgeotto,
    )
    val nome = arrayListOf<String>(
        "Caterpie",
        "Bulbasaur",
        "Charmeleon",
        "Pidgeotto",
    )
    val dano = arrayListOf<Int>(
        20,
        30,
        50,
        40,
        )
    val defesa = arrayListOf<Int>(
        5,
        10,
        15,
        20,
    )
    val hp = arrayListOf<Int>(
        500,
        320,
        300,
        350,
    )
    var x = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySelectPokemonBinding.inflate(layoutInflater)
            val view = binding.root
                setContentView(view)

        loaddata()

        binding.textName.setTextColor(Color.parseColor("#DF0101"))

        binding.buttonSetaNext.setOnClickListener {
            proximo(it)
        }

        binding.buttonSetaBack.setOnClickListener {
            anterior(it)
        }

        binding.buttonYes.setOnClickListener {

            val dialog = AlertDialog.Builder(this).
            setTitle("Gostaria de um Tutorial?").
            setMessage("Se voce nunca jogou, seria bom aprender um pouco.")
                .setPositiveButton("Sim") { dialog, _ ->
                    val intent = Intent(this, Tutorial::class.java)
                    saveStatus()
                    startActivity(intent)
                    finish()

                    dialog.dismiss()
                }
                .setNegativeButton("Não") { dialog, _ ->
                    val intent = Intent(this, BattlePokemon::class.java)
                    saveStatus()
                    startActivity(intent)
                    finish()
                    dialog.dismiss()
                }
            dialog.show()

        }

        binding.buttonBackSelect.setOnClickListener {
            val intent = Intent(this, NameTreiner::class.java)
            startActivity(intent)
            finish()
        }

    }

    fun saveStatus(){

        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply {
            putString("NOME_KEY", nome[x])
            putInt("DANO_KEY", dano[x])
            putInt("HP_KEY", hp[x])
            putInt("DEFESA_KEY", defesa[x])
            putInt("IMAGE_KEY", x)

        }.apply()

    }

    fun loaddata(){
        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val savedString = sharedPreferences.getString("String_key", null)
        binding.textName.text = savedString + ", você deve escolher seu pokémon para batalha:"

    }

    fun anterior(view: View){
        if (x > 0){
            x -= 1
            binding.imagePokemoSelect.setImageResource(imgs[x])
            binding.ataquePokemonStatus.setText(dano[x].toString())
            binding.defesaPokemonStatus.setText(defesa[x].toString())
            binding.hpPokemonStatus.setText(hp[x].toString())
            binding.namePokemonStatus.setText(nome[x])
        }
    }

    fun proximo(view: View){
        if (x < imgs.size-1){
            x += 1
            binding.imagePokemoSelect.setImageResource(imgs[x])
            binding.ataquePokemonStatus.setText(dano[x].toString())
            binding.defesaPokemonStatus.setText(defesa[x].toString())
            binding.hpPokemonStatus.setText(hp[x].toString())
            binding.namePokemonStatus.setText(nome[x])
        }
    }

    override fun onBackPressed() {
        val erroErik = Toast.makeText(this,
            "Sistema anti-erik ATIVADO, so da pra voltar pela navegaçao de botoes", Toast.LENGTH_SHORT)
        erroErik.show()
    }
}