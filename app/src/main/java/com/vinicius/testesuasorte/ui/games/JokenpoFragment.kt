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
import androidx.fragment.app.setFragmentResultListener
import com.vinicius.testesuasorte.R
import com.vinicius.testesuasorte.databinding.FragmentJokenpoBinding

class JokenpoFragment : Fragment(R.layout.fragment_jokenpo) {
    //private lateinit var binding: FragmentJokenpoBinding

    private var _binding: FragmentJokenpoBinding? = null
    private val binding get() = _binding!!

    private var jokenpoImages = mutableListOf(
        R.drawable.scissors_jokenpo,
        R.drawable.rock_jokenpo,
        R.drawable.paper_jokenpo
    )

    var countWin = 0
    var countLost = 0
    var countDraw = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentJokenpoBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        _binding = FragmentJokenpoBinding.inflate(layoutInflater)
        var jokenpoSelect: Int
        setFragmentResultListener("lostEvenAndOdds") { key, bundle ->
            countLost = bundle.getInt("bundleKey")
        }
        setFragmentResultListener("winEvenAndOdds") { key, bundle ->
            countWin = bundle.getInt("bundleKey")
        }
        binding.winTv.text = "$countWin"
        binding.lostTv.text = "$countLost"


        binding.startJokenpoBtn.setOnClickListener {
            if (binding.rockBtn.isChecked || binding.paperBtn.isChecked || binding.scissorBtn.isChecked){
                if (!valideStart()){
                    Toast.makeText(requireContext(), "Você pode selecionar somente uma opção !", Toast.LENGTH_SHORT).show()
                } else if (countWin == 10 || countLost == 10){
                    val resultLost = countLost
                    val resultWin = countWin
                    setFragmentResult("lostJokenpo", bundleOf("bundleKey" to resultLost))
                    setFragmentResult("winJokenpo", bundleOf("bundleKey" to resultWin))
                    val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
                    val fragmentTransaction = fragmentManager.beginTransaction()
                    binding.startJokenpoBtn.text = "Proximo jogo!"
                    fragmentTransaction.replace(R.id.flFragment, DiceFragment()).commit()
                }else{
                    jokenpoSelect = drawJokenpo()
                    binding.numberSortIv.setImageDrawable(getDrawable(requireContext(), jokenpoImages[jokenpoSelect]))
                    binding.resultTv.text = verifyWinner(jokenpoSelect)
                }
            }
            binding.winTv.text = "$countWin"
            binding.drawTv.text = "$countDraw"
            binding.lostTv.text = "$countLost"
        }
    }



    fun valideStart(): Boolean{
        if ((binding.rockBtn.isChecked && binding.paperBtn.isChecked) ||
            (binding.paperBtn.isChecked && binding.scissorBtn.isChecked) ||
            (binding.rockBtn.isChecked && binding.scissorBtn.isChecked) ||
            (binding.rockBtn.isChecked && binding.paperBtn.isChecked && binding.scissorBtn.isChecked) ||
            (!binding.rockBtn.isChecked && !binding.paperBtn.isChecked && !binding.scissorBtn.isChecked)){
            return false
        }
        return true
    }

    fun drawJokenpo(): Int {
        return (0 until jokenpoImages.size).random()
    }

    fun verifyWinner(jokenpo : Int) : String{
        if (jokenpo == 0 && binding.rockBtn.isChecked ) {
            countWin++
            return "Você ganhou !"
        }
        else if (jokenpo == 0 && binding.paperBtn.isChecked ){
            countLost++
            return "Você perdeu !"
        }
        else if (jokenpo == 0 && binding.scissorBtn.isChecked ) {
            countDraw++
            return "Você empatou !"
        }

        else if (jokenpo == 1 && binding.rockBtn.isChecked ){
            countDraw++
            return "Você empatou !"
        }
        else if (jokenpo == 1 && binding.paperBtn.isChecked ){
            countWin++
            return "Você ganhou !"
        }
        else if (jokenpo == 1 && binding.scissorBtn.isChecked ){
            countLost++
            return "Você perdeu !"
        }

        else if (jokenpo == 2 && binding.rockBtn.isChecked ){
            countLost++
            return "Você perdeu !"
        }
        else if (jokenpo == 2 && binding.paperBtn.isChecked ){
            countDraw++
            return "Você empatou !"
        }
        else
            countWin++
            return "Você ganhou !"
    }
}