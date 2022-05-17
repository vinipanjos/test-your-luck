package com.vinicius.testesuasorte.ui.games

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import com.vinicius.testesuasorte.R
import com.vinicius.testesuasorte.databinding.FragmentDiceBinding
import com.vinicius.testesuasorte.databinding.FragmentJokenpoBinding

class DiceFragment : Fragment() {
    private var _binding: FragmentDiceBinding? = null
    private val binding get() = _binding!!

    private var diceImages = mutableListOf(
        R.drawable.dice1,
        R.drawable.dice2,
        R.drawable.dice3,
        R.drawable.dice4,
        R.drawable.dice5,
        R.drawable.dice6,
    )
    var countWin = 0
    var countLost = 0
    var countDraw = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDiceBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //_binding = FragmentJokenpoBinding.inflate(layoutInflater)
        setFragmentResultListener("lostJokenpo") { key, bundle ->
            countLost = bundle.getInt("bundleKey")
        }
        setFragmentResultListener("winJokenpo") { key, bundle ->
            countWin = bundle.getInt("bundleKey")
        }
        binding.winTv.text = "$countWin"
        binding.lostTv.text = "$countLost"

        binding.startDiceBtn.setOnClickListener {
            if (countWin == 15 || countLost == 15) {
                val resultLost = countLost
                val resultWin = countWin
                setFragmentResult("lostDice", bundleOf("bundleKey" to resultLost))
                setFragmentResult("winDice", bundleOf("bundleKey" to resultWin))
                val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                binding.startDiceBtn.text = "Obter o resultado!"
                fragmentTransaction.replace(R.id.flFragment, ResultFragment()).commit()
            } else {
                showResult()
                binding.winTv.text = "$countWin"
                binding.drawTv.text = "$countDraw"
                binding.lostTv.text = "$countLost"
            }
        }
    }

    fun drawDices(): Int {
        return (0 until diceImages.size).random()
    }

    fun showResult() {
        val myDiceSort = drawDices()
        val enemyDiceSort = drawDices()
        val myDiceResult = diceImages[myDiceSort]
        val enemyDiceResult = diceImages[enemyDiceSort]
        binding.myDiceSortIv.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                myDiceResult
            )
        )
        binding.enemyDiceSortIv.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                enemyDiceResult
            )
        )

        if (myDiceSort > enemyDiceSort) {
            binding.resultTv.text = "Você ganhou !"
            countWin++
        } else if (myDiceSort == enemyDiceSort) {
            binding.resultTv.text = "Você empatou !"
            countDraw++
        } else {
            binding.resultTv.text = "Você perdeu !"
            countLost++
        }
    }
}