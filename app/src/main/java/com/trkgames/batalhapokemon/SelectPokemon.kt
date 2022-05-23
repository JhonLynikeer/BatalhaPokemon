package com.trkgames.batalhapokemon

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
    lateinit var img: ImageView
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

    val dano = arrayListOf<String>(
        "20",
        "30",
        "50",
        "40",
        )

    val defesa = arrayListOf<String>(
        "30",
        "35",
        "40",
        "50",
    )

    val hp = arrayListOf<String>(
        "250",
        "320",
        "300",
        "350",
    )

    var x = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySelectPokemonBinding.inflate(layoutInflater)
            val view = binding.root
                setContentView(view)

        val dados: Bundle? = intent.extras
        val treinerName = dados?.getString("name")


        binding.textName.setTextColor(Color.parseColor("#DF0101"))
        binding.textName.text = "$treinerName, você deve escolher seu pokémon para batalha:"


        binding.buttonSetaNext.setOnClickListener {
            proximo(it)

        }

        binding.buttonSetaBack.setOnClickListener {
            anterior(it)
        }

        binding.buttonYes.setOnClickListener {
            //val intent = Intent(this, BattlePokemon::class.java)
            //intent.putExtra("pokemon", "${x}")


            val dialog = AlertDialog.Builder(this).
            setTitle("Tem certeza?").
            setMessage("O chefao pode acabar com você muito rapido!")
                .setPositiveButton("Sim") { dialog, _ ->
                    val intent = Intent(this, BattlePokemon::class.java)
                    intent.putExtra("image", "${x}")
                    intent.putExtra("nome", "${nome[x]}")
                    intent.putExtra("hp", "${hp[x]}")
                    intent.putExtra("dano", "${dano[x]}")

                    startActivity(intent)
                    finish()

                    dialog.dismiss()
                }
                .setNegativeButton("Não") { dialog, _ ->
                    dialog.dismiss()
                }
            dialog.show()


            //startActivity(intent)
            //finish()
        }


        binding.buttonBackSelect.setOnClickListener {
            val intent = Intent(this, NameTreiner::class.java)
            startActivity(intent)
            finish()
        }






    }

    fun anterior(view: View){
        if (x > 0){
            x -= 1
            binding.imagePokemoSelect.setImageResource(imgs[x])
            binding.ataquePokemonStatus.setText(dano[x])
            binding.defesaPokemonStatus.setText(defesa[x])
            binding.hpPokemonStatus.setText(hp[x])
            binding.namePokemonStatus.setText(nome[x])
        }
    }

    fun proximo(view: View){
        if (x < imgs.size-1){
            x += 1
            binding.imagePokemoSelect.setImageResource(imgs[x])
            binding.ataquePokemonStatus.setText(dano[x])
            binding.defesaPokemonStatus.setText(defesa[x])
            binding.hpPokemonStatus.setText(hp[x])
            binding.namePokemonStatus.setText(nome[x])
        }
    }


    override fun onBackPressed() {
        val erroErik = Toast.makeText(this,
            "Sistema anti-erik ATIVADO, so da pra voltar pela navegaçao de botoes", Toast.LENGTH_SHORT)
        erroErik.show()
    }
}