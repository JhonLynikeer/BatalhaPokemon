package com.trkgames.batalhapokemon

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.trkgames.batalhapokemon.databinding.ActivityBattlePokemonBinding
import java.util.concurrent.TimeUnit

private lateinit var binding: ActivityBattlePokemonBinding

var hpmewtwo = 1000
var danomewtwo = 50
var escudo = 0
var handler = Handler()



val imgs = intArrayOf(
    R.drawable.in_image_caterpie,
    R.drawable.in_image_bulbasaur,
    R.drawable.in_image_charmeleon,
    R.drawable.in_image_pidgeotto,
)





class BattlePokemon : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBattlePokemonBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        val dados: Bundle? = intent.extras
        val image = dados?.get("image").toString().toInt()
        val name = dados?.getString("nome")
        val hp = dados?.getString("hp").toString().toInt()
        var meuhp = hp
        hpmewtwo = 1000
        danomewtwo = 50

        binding.nomePokemon.text = "$name"
        binding.hpPlayer.text = "$meuhp"
        binding.imagePlayer.setImageResource(imgs[image])




            binding.buttonAtk.setOnClickListener {
                ataque()
                vivos()
                handler.postDelayed(Runnable {
                    meuhp -= danomewtwo
                    binding.hpPlayer.text = "$meuhp"
                    atkmewtwo() }, 1000)


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
                    atkmewtwo() }, 1000)


            }

            binding.buttonRun.setOnClickListener {
                fugir()
                val intent = Intent(this, SelectPokemon::class.java)
                startActivity(intent)
                finish()

            }







    }

    fun ataque(){

        val dados: Bundle? = intent.extras
        val hp2 = dados?.getString("dano").toString().toInt()

        var hp = binding.hpBoss
        hp.text = hpmewtwo.toString()
        hpmewtwo -= hp2
        binding.efeitoAtk.setImageResource(R.drawable.image_atk)
        handler.postDelayed(Runnable { removerefeito() }, 500)


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

    fun vivos(){
        if (hpmewtwo < 0 ){
           val intent = Intent(this, Winner::class.java)
            startActivity(intent)
            finish()
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