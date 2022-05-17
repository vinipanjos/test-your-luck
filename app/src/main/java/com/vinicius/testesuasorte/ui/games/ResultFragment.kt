package com.vinicius.testesuasorte.ui.games

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import com.vinicius.testesuasorte.databinding.FragmentResultBinding


class ResultFragment : Fragment() {
    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!

    var countWin = 0
    var countLost = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
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
        setFragmentResultListener("lostDice") { key, bundle ->
            countLost = bundle.getInt("bundleKey")
        }
        setFragmentResultListener("winDice") { key, bundle ->
            countWin = bundle.getInt("bundleKey")
        }

        val winRate: Double = (((15-countLost)/30*100).toDouble())

        showResult(winRate)

    }

    private fun showResult(num: Double) {
        if (num >= 70) binding.resultTxt.text = "$num% de acerto ! A sorte está do seu lado, porque não tenta jogar algo mais interessante ! "
        else if (num >= 51 && num < 70) binding.resultTxt.text = "$num% de acerto ! Você está com mais sorte que a maioria, seu palpite seria certeiro em apostas !"
        else if (num >= 30 && num < 51) binding.resultTxt.text = "$num% de acerto ! A sorte está ai mas não abuse, tente jogar algum esporte da mente, como o poker, o quesito sorte é relevante mas a sua decisão é o mais importante!"
        else if (num >= 15 && num <30) binding.resultTxt.text = "$num% de acerto ! A sorte não ta boa para o seu lado, porem acreditar em seu proprio potencial é fundamental, se você acredita que pode dar certo ja é o suficiente, mas não vá despreparado !"
        else if (num < 15) binding.resultTxt.text = "$num% de acerto ! Não dê chance para o azar, quem sabe com alguma ajuda você possa driblar essa fase ! "
    }
}