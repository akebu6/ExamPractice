package com.myrabohuche.exampractice.ui.fragment.correction

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.RadioGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.myrabohuche.exampractice.R
import com.myrabohuche.exampractice.model.Question
import com.myrabohuche.exampractice.model.QuestionBank
import kotlinx.android.synthetic.main.fragment_correction.*
/**
 * A simple [Fragment] subclass.
 */
class CorrectionFragment : Fragment() {

    private var mQuestionBank: QuestionBank? = QuestionBank(ArrayList())
    private var mCurrentQuestion: Question? = null

    private var wrongQuests = arrayOf<Question>()

    private var m_parts = arrayListOf<String>()
    private var listView: ListView? = null
    private var mNumberOfQuestions: Int = 0
    private var radioGroup: RadioGroup? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_correction, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        wrongQuests = CorrectionFragmentArgs.fromBundle(requireArguments()).wrongAnsArgInWrong!!
        radioGroup=questionRadioGroup

        mQuestionBank = generateQuestions()
        mCurrentQuestion = mQuestionBank!!.question
        mNumberOfQuestions=mQuestionBank!!.mQuestionList.size

        mCurrentQuestion = mQuestionBank!!.question
        displayQuestion(mCurrentQuestion!!)


//        requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
//            override fun handleOnBackPressed() {
//                if (isEnabled){
//
//                    findNavController().navigateUp()
//                    isEnabled=true
//
//                }
//            }
//        })

        submitButton.setOnClickListener{
            --mNumberOfQuestions
            if (mNumberOfQuestions != 0){
                if (mNumberOfQuestions == 1)
                    submitButton.text="Finish"
                Handler().postDelayed(Runnable {
                    activity?.let {
                        mCurrentQuestion = mQuestionBank!!.question
                        displayQuestion(mCurrentQuestion!!)
                    }
                },1000)

            }else{
                val builder = AlertDialog.Builder(requireContext())

                builder.setTitle("Finish!")
                    .setMessage("Keep studying")
                    .setPositiveButton("BACK") { _, _ ->

                        findNavController().navigateUp()
                    }
                    .setCancelable(false)
                    .create()
                    .show()
            }
        }
    }
    private fun generateQuestions(): QuestionBank? {
        val questionList: List<Question> = wrongQuests.toList()
        return QuestionBank(questionList)
    }

    private fun displayQuestion(question: Question) {

        questionText.text = question.question
        radio1.text = question.options[0]
        radio2.text = question.options[1]
        radio3.text = question.options[2]
        radio4.text = question.options[3]
        questionRightAnns.text="Ans = "+mCurrentQuestion!!.options[mCurrentQuestion!!.answerIndex - 1]

    }
}