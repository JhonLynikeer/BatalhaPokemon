package com.trkgames.batalhapokemon

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.trkgames.batalhapokemon.databinding.ActivityBattlePokemonBinding
import java.util.concurrent.TimeUnit

class BattlePokemon : AppCompatActivity() {

    private lateinit var binding: ActivityBattlePokemonBinding

    var hpmewtwo = 1000
    var danomewtwo = 50
    var escudo = 0
    var vivo = 1
    var danoPokemonBase = 0
    var defesaPokemonBase = 0
    var hpPlayer = 0
    var handler = Handler()

    val imgs = intArrayOf(
        R.drawable.in_image_caterpie,
        R.drawable.in_image_bulbasaur,
        R.drawable.in_image_charmeleon,
        R.drawable.in_image_pidgeotto,
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBattlePokemonBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        loadStatus()

        var meuhp = hpPlayer
        hpmewtwo = 1000
        danomewtwo = 50
        vivo = meuhp

            binding.buttonAtk.setOnClickListener {
                ataque()
                vidamewtwo()
                handler.postDelayed(Runnable {
                    meuhp -= danomewtwo
                    binding.hpPlayer.text = "$meuhp"
                    atkmewtwo()
                    vivo = meuhp
                    vidapokemon()
                     }, 1000)
            }

            binding.buttonDef.setOnClickListener {
                defesa()
                handler.postDelayed(Runnable {
                    meuhp -= danomewtwo
                    binding.hpPlayer.text = "$meuhp"
                    atkmewtwo() }, 1000)
            }

            binding.buttonHeal.setOnClickListener {

                heal()

                meuhp += 30
                binding.hpPlayer.text = "$meuhp"
                handler.postDelayed(Runnable {
                    meuhp -= danomewtwo
                    binding.hpPlayer.text = "$meuhp"
                    atkmewtwo()
                    vivo = meuhp
                    vidapokemon()}, 1000)
            }

            binding.buttonRun.setOnClickListener {
                fugir()
                val intent = Intent(this, Loser::class.java)
                startActivity(intent)
                finish()

            }

    }

    fun loadStatus(){

        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val nomePokemon = sharedPreferences.getString("NOME_KEY", null)
        val danoPokemon: Int = sharedPreferences.getInt("DANO_KEY", 0)
        val hpPokemon = sharedPreferences.getInt("HP_KEY", 0)
        val defesaPokemon = sharedPreferences.getInt("DEFESA_KEY", 0)
        val imagePokemon = sharedPreferences.getInt("IMAGE_KEY", 0)

        binding.nomePokemon.text = nomePokemon
        danoPokemonBase = danoPokemon
        binding.hpPlayer.text = hpPokemon.toString()
        hpPlayer = hpPokemon
        defesaPokemonBase = defesaPokemon
        binding.imagePlayer.setImageResource(imgs[imagePokemon])

    }

    fun ataque(){

        var hp = binding.hpBoss
        hp.text = hpmewtwo.toString()
        hpmewtwo -= danoPokemonBase
        binding.efeitoAtk.setImageResource(R.drawable.image_atk)
        handler.postDelayed(Runnable { removerefeito() }, 500)
        vidapokemon()
    }

    fun heal(){
        binding.pokemonEfeito.setImageResource(R.drawable.image_heal)
        handler.postDelayed(Runnable { removerefeito() }, 500)
    }

    fun defesa(){
        danomewtwo = 0
        escudo = 1
        binding.pokemonEfeito.setImageResource(R.drawable.image_def)
    }

    fun fugir(){

        binding.pokemonEfeito.setImageResource(R.drawable.image_run)
        handler.postDelayed(Runnable { removerefeito() }, 1000)

    }

    fun vidamewtwo(){

        if (hpmewtwo <= 0 ){
            hpmewtwo = 0
            binding.hpBoss.text = hpmewtwo.toString()
           val intent = Intent(this, Winner::class.java)
            handler.postDelayed(Runnable {
                startActivity(intent)
                finish()}, 1000)
        }
    }

    fun vidapokemon(){

        if (vivo <= 0 ){
            hpPlayer = 0
            binding.hpPlayer.text = hpPlayer.toString()
            val intent = Intent(this, Loser::class.java)
            handler.postDelayed(Runnable {
                startActivity(intent)
                finish()}, 1000)
        }
    }

    fun atkmewtwo(){

        if (escudo > 0){
            binding.pokemonEfeito.setImageResource(R.drawable.image_atk)
            handler.postDelayed(Runnable { removerefeito() }, 500)
            escudo -= 1

        } else {
            danomewtwo = 50
            binding.pokemonEfeito.setImageResource(R.drawable.image_atk)
            handler.postDelayed(Runnable { removerefeito() }, 500)
        }
        vidapokemon()
    }

    fun removerefeito(){

        if (escudo < 1){
            binding.efeitoAtk.setImageResource(R.drawable.semefeito)
            binding.pokemonEfeito.setImageResource(R.drawable.semefeito)
        }
        if (escudo > 0){
            binding.pokemonEfeito.setImageResource(R.drawable.image_def)
        }
    }

    override fun onBackPressed() {
        val erroErik = Toast.makeText(this,
            "Sistema anti-erik ATIVADO, so da pra voltar pela navegaçao de botoes", Toast.LENGTH_SHORT)
        erroErik.show()
    }

}