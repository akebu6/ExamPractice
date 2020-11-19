package com.myrabohuche.exampractice.ui.fragment.quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.myrabohuche.exampractice.databinding.FragmentQuizBinding

class QuizFragment : Fragment() {

    private var subjArgs: String? = null
    private var quesArgs: String? = null
    private var timeArgs: String? = null
    private var schoolArgs: String? = null
    private var txtView: TextView? = null
    private lateinit var binding: FragmentQuizBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentQuizBinding.inflate(inflater)

        txtView = binding.txtView
        subjArgs = QuizFragmentArgs.fromBundle(requireArguments()).subArgs
        quesArgs = QuizFragmentArgs.fromBundle(requireArguments()).quesArgs
        timeArgs = QuizFragmentArgs.fromBundle(requireArguments()).timeArgs
        schoolArgs = QuizFragmentArgs.fromBundle(requireArguments()).schoolArgs


        txtView!!.text = "$subjArgs \n $quesArgs \n $timeArgs \n $schoolArgs"

        return binding.root
    }

}