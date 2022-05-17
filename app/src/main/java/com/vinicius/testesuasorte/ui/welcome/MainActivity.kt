package com.vinicius.testesuasorte.ui.welcome

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.vinicius.testesuasorte.databinding.ActivityMainBinding
import com.vinicius.testesuasorte.ui.games.GamesActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStartGame.setOnClickListener {
            val intent = Intent(this, GamesActivity::class.java)
            startActivity(intent)
        }

        binding.cutTimeBtn.setOnClickListener {
            //TODO configurar o que vai aparecer para pular o tempo
            Toast.makeText(this, "Precisa de configuração !", Toast.LENGTH_LONG).show()
        }

    }
}