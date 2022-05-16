package com.vinicius.testesuasorte.ui.welcome

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.vinicius.testesuasorte.R
import com.vinicius.testesuasorte.databinding.ActivityMainBinding
import com.vinicius.testesuasorte.ui.games.EvenOrOddsFragment
import com.vinicius.testesuasorte.ui.games.JokenpoFragment
import com.vinicius.testesuasorte.ui.SlotMachineFragment
import com.vinicius.testesuasorte.ui.games.DiceFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val evenOrOdds = EvenOrOddsFragment()
        val slotMachine = SlotMachineFragment()
        val jokenpo = JokenpoFragment()
        val dice = DiceFragment()


        binding.slotmachineBtn.setOnClickListener {
            setFragment(slotMachine)
        }
        binding.evenoroddsBtn.setOnClickListener {
            setFragment(evenOrOdds)
        }
        binding.jokenpoBtn.setOnClickListener {
            setFragment(jokenpo)
        }
        binding.diceBtn.setOnClickListener {
            setFragment(dice)
        }

    }
    fun setFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment)
            commit()
        }
    }
}