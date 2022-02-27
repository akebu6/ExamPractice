package com.myrabohuche.exampractice.ui.fragment.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.myrabohuche.exampractice.databinding.FragmentResultBinding
import com.myrabohuche.exampractice.model.Question
import java.text.DecimalFormat

/**
 * A simple [Fragment] subclass.
 */
class ResultFragment : Fragment() {

    //private var wrongQuestList = String()
    private var wrongQuestQues = arrayOf<Question>()
    private var txtResultID: Button? = null
    private var txtResultDetails: TextView? = null
    private var txtPercentage: TextView? = null
    private var totalAns: Int = 0
    private var correctAns: Int = 0
    private var wrongAns: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentResultBinding.inflate(inflater)

        wrongQuestQues = ResultFragmentArgs.fromBundle(requireArguments()).wrongAnsArgInResult!!
        totalAns = ResultFragmentArgs.fromBundle(requireArguments()).totalAnswered - 1
        correctAns = ResultFragmentArgs.fromBundle(requireArguments()).correctAnswered
        wrongAns = ResultFragmentArgs.fromBundle(requireArguments()).wrongAnswered


        txtResultID=binding.correctionButton
        txtResultDetails=binding.txtCorrectAns
        txtPercentage=binding.tvPerc

        if (wrongQuestQues.size==0){
            txtResultID!!.visibility=View.GONE
        }else{
            txtResultID!!.visibility=View.VISIBLE
            txtResultID!!.setOnClickListener {
                val action = ResultFragmentDirections.actionResultFragmentToCorrectionFragment(wrongQuestQues)
                findNavController().navigate(action)
            }
        }

        txtResultDetails!!.text="Answered: $totalAns\nCorrect: $correctAns\nWrong: $wrongAns"
        val df = DecimalFormat("##.###")
        val percentage = 100.toDouble() / totalAns
        val ans:Double = correctAns*percentage
        txtPercentage!!.text=(""+df.format(ans)+"%")

        requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (isEnabled){
                    findNavController().navigateUp()
                    isEnabled=true

                }
            }
        })
        return binding.root
    }
}
