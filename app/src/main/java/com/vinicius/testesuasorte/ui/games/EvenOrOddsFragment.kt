package com.vinicius.testesuasorte.ui.games

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getDrawable
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.setFragmentResult
import com.vinicius.testesuasorte.R
import com.vinicius.testesuasorte.databinding.FragmentEvenOrOddsBinding

class EvenOrOddsFragment : Fragment(R.layout.fragment_even_or_odds) {
    //private lateinit var binding: FragmentEvenOrOddsBinding
    private var _binding: FragmentEvenOrOddsBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var numberImages = mutableListOf(
        R.drawable.numbertwo,
        R.drawable.numberone
    )
    var countWin = 0
    var countLost = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEvenOrOddsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
      //  binding = FragmentEvenOrOddsBinding.inflate(layoutInflater)

        binding.startEvenoroddsBtn.setOnClickListener {
            if (binding.numberTil.editText?.text?.isEmpty() == true) {
                Toast.makeText(requireContext(), " Preencher com o numero escolhido !", Toast.LENGTH_SHORT)
                    .show()
            }else if(countWin == 5 || countLost == 5){
                val resultLost = countLost
                val resultWin = countWin
                setFragmentResult("lostEvenAndOdds", bundleOf("bundleKey" to resultLost))
                setFragmentResult("winEvenAndOdds", bundleOf("bundleKey" to resultWin))
                val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                binding.startEvenoroddsBtn.text = "Proximo jogo!"
                fragmentTransaction.replace(R.id.flFragment, JokenpoFragment()).commit()
            } else {
                showResult()
                binding.winTv.text = "$countWin"
                binding.lostTv.text = "$countLost"
            }
        }
    }

    fun drawNumbers(): Int {
        return (0 until numberImages.size).random()
    }

    fun verifyEvenOrOdds(num1: Int, num2: Int): Boolean {
        if ((num1 + num2) % 2 == 0) {
            return true
        }
        return false
    }

    fun showResult() {
        var numberSort = drawNumbers()
        var numberResult = numberImages[numberSort]
        val numberValueS = binding.numberTil.editText?.text.toString()
        val numberValue = numberValueS.toInt()
        binding.numberSortIv.setImageDrawable(getDrawable(requireContext(), numberResult))
        if (binding.evenBtn.isChecked) {
            if (verifyEvenOrOdds(numberValue, numberSort)) {
                binding.resultTv.text = "Você ganhou !"
                countWin++
            } else {
                binding.resultTv.text = "Você perdeu !"
                countLost++
            }
        } else if (binding.oddBtn.isChecked) {
            if (!verifyEvenOrOdds(numberValue, numberSort)) {
                binding.resultTv.text = "Você ganhou !"
                countWin++
            } else {
                binding.resultTv.text = "Você perdeu !"
                countLost++
            }
        }
    }
}
