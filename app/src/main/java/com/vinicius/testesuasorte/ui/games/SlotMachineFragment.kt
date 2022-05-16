package com.vinicius.testesuasorte.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getDrawable
import androidx.fragment.app.Fragment
import com.vinicius.testesuasorte.R
import com.vinicius.testesuasorte.databinding.FragmentEvenOrOddsBinding
import com.vinicius.testesuasorte.databinding.FragmentJokenpoBinding
import com.vinicius.testesuasorte.databinding.FragmentSlotMachineBinding

class SlotMachineFragment : Fragment(R.layout.fragment_slot_machine) {
//    private lateinit var binding: FragmentSlotMachineBinding

    private var _binding: FragmentSlotMachineBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSlotMachineBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding = FragmentSlotMachineBinding.inflate(layoutInflater)

        val slotMachine: SlotMachine = SlotMachine()
        var count = 0

        binding.startBtn.setOnClickListener {
            if (binding.betValueTil.editText?.text?.isEmpty() == true) {
                Toast.makeText(requireContext(), " Preencher valor da aposta !", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val betValueS = binding.betValueTil.editText?.text.toString()
                val betValue = betValueS.toInt()

                count++
                binding.numberMovesTv.text = "$count jogadas."

                val slotResult = slotMachine.draw()
                binding.slot1.setImageDrawable(getDrawable(requireContext(), slotResult.first))
                binding.slot2.setImageDrawable(getDrawable(requireContext(), slotResult.second))
                binding.slot3.setImageDrawable(getDrawable(requireContext(), slotResult.third))

                binding.resultTxt.text = slotMachine.analyseResult(slotResult, betValue)

                binding.balanceTv.text = "${slotMachine.totalValue},00 reais"
            }
        }
    }
}

class SlotMachine {

    private var slotImages = mutableListOf<Int>(
        R.drawable.banana,
        R.drawable.bar,
        R.drawable.cherry,
        R.drawable.diamond,
        R.drawable.dolar,
        R.drawable.grape,
        R.drawable.lemon,
        R.drawable.seven
    )

    var totalValue = 0

    fun drawSlots(): Int {
        val output: Int = (0 until slotImages.size).random()
        return slotImages[output]
    }


    fun draw(): Triple<Int, Int, Int> {
        val slot1: Int = drawSlots()
        val slot2: Int = drawSlots()
        val slot3: Int = drawSlots()
        var output: Triple<Int, Int, Int> = Triple(slot1, slot2, slot3)

        return output
    }

    @SuppressLint("SetTextI18n")
    fun analyseResult(result: Triple<Int, Int, Int>, betValue: Int): String {

        if (result.first == result.second && result.first == result.third) {

            totalValue += betValue * 100

            return "Jackpot ! Ganhou R${betValue * 100},00"
        }
        if (result.first == result.second || result.first == result.third || result.second == result.third) {

            totalValue += betValue * 10

            return "Legal ! Ganhou R${betValue * 10},00"
        }

        totalValue -= betValue

        return "Não foi dessa vez ! Continue tentando"
    }
}