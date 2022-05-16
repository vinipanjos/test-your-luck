package com.vinicius.testesuasorte.ui.games

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
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
//evento de clique aqui
        binding.startDiceBtn.setOnClickListener {
            showResult()
            binding.winTv.text = "$countWin"
            binding.drawTv.text = "$countDraw"
            binding.lostTv.text = "$countLost"
        }
    }

    fun drawDices(): Int {
        return (0 until diceImages.size).random()
    }

    fun showResult() {
        var myDiceSort = drawDices()
        var enemyDiceSort = drawDices()
        var myDiceResult = diceImages[myDiceSort]
        var enemyDiceResult = diceImages[enemyDiceSort]
        binding.myDiceSortIv.setImageDrawable(ContextCompat.getDrawable(requireContext(), myDiceResult))
        binding.enemyDiceSortIv.setImageDrawable(ContextCompat.getDrawable(requireContext(), enemyDiceResult))

        if (myDiceSort > enemyDiceSort) {
            binding.resultTv.text = "Você ganhou !"
            countWin++
        } else if (myDiceSort == enemyDiceSort){
            binding.resultTv.text = "Você empatou !"
            countDraw++
        } else{
            binding.resultTv.text = "Você perdeu !"
            countLost++
        }
    }
}