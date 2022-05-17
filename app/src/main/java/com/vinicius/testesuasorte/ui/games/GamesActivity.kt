package com.vinicius.testesuasorte.ui.games

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.vinicius.testesuasorte.R
import com.vinicius.testesuasorte.databinding.ActivityGamesBinding
import com.vinicius.testesuasorte.ui.SlotMachineFragment

class GamesActivity : AppCompatActivity() {

    val binding by lazy { ActivityGamesBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // binding.fragmentsRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        //findFragments()

        val evenOrOdds = EvenOrOddsFragment()

        val slotMachine = SlotMachineFragment()
        val jokenpo = JokenpoFragment()
        val dice = DiceFragment()
//
//        binding.slotmachineBtn.setOnClickListener {
//            setFragment(slotMachine)
//        }

        setFragment(evenOrOdds)

        //        binding.jokenpoBtn.setOnClickListener {
//            setFragment(jokenpo)
//        }
//        binding.diceBtn.setOnClickListener {
//            setFragment(dice)
//        }
//    }
    }

    fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment)
            commit()
        }
    }
//    private fun findFragments() {
//        val dataSet = ArrayList<Fragment>()
//        dataSet.add(EvenOrOddsFragment())
//        dataSet.add(SlotMachineFragment())
//        dataSet.add(DiceFragment())
//        dataSet.add(JokenpoFragment())
//        //binding.fragmentsRv.adapter = FragmentAdapter(dataSet)
//    }

}
