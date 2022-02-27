package com.myrabohuche.exampractice.ui.fragment.quiz

import android.app.ProgressDialog
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.myrabohuche.exampractice.R
import com.myrabohuche.exampractice.databinding.FragmentQuizBinding
import com.myrabohuche.exampractice.model.Question
import com.myrabohuche.exampractice.model.QuestionBank
import com.myrabohuche.exampractice.utils.htmlToText
import com.myrabohuche.exampractice.utils.minuteToMilliSecond
import com.myrabohuche.exampractice.utils.suffixConversion
import kotlinx.android.synthetic.main.fragment_quiz.*
import kotlinx.io.InputStream
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.builtins.list
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList

class QuizFragment : Fragment() {

    private var mQuestionBank: QuestionBank? = QuestionBank(ArrayList())
    private var mCurrentQuestion: Question? = null

    private var txtQuestionCount: TextView? = null
    private var txtCounter: TextView? = null
    private var colorStateList: ColorStateList? = null
    private var colorStateListCountDown: ColorStateList? = null
    private var countDownTimer: CountDownTimer? = null
    private var onBackPressedTime: Long = 0

    private var timeLeft: Long = 0

    private var mScore: Int = 1
    private var qCounter: Int = 1
    private var mNumberOfQuestions: Int = 0
    private lateinit var url: String

    private var wrongQuestList = arrayListOf<Question>()
    private var numberOfWrong: Int = 0
    private var mProgressBar: ProgressDialog? = null

    private var radioGroup: RadioGroup? = null

    private var subjArgs: String? = null
    private var quesArgs: String? = null
    private var timeArgs: String? = null
    private var schoolArgs: String? = null
    private var suffix: String? = null

    //private var txtView: TextView? = null
    private lateinit var binding: FragmentQuizBinding

    @InternalSerializationApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentQuizBinding.inflate(inflater)

        //txtView = binding.txtView
        subjArgs = QuizFragmentArgs.fromBundle(requireArguments()).subArgs
        quesArgs = QuizFragmentArgs.fromBundle(requireArguments()).quesArgs
        timeArgs = QuizFragmentArgs.fromBundle(requireArguments()).timeArgs
        schoolArgs = QuizFragmentArgs.fromBundle(requireArguments()).schoolArgs
        suffix = QuizFragmentArgs.fromBundle(requireArguments()).suffixArgs

        url = schoolArgs + " " + suffixConversion(suffix!!) + " " + subjArgs
        mNumberOfQuestions = quesArgs!!.toInt()
        timeLeft = minuteToMilliSecond(timeArgs!!)

        mQuestionBank = generateQuestions()
        //mNumberOfQuestions = mQuestionBank!!.mQuestionList.size + mNumberOfQuestions - mQuestionBank!!.mQuestionList.size

        txtQuestionCount = binding.questionCount
        txtCounter = binding.timeCounter
        radioGroup = binding.questionRadioGroup
        colorStateListCountDown = txtCounter!!.textColors

        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false)

        mCurrentQuestion = mQuestionBank!!.question
        displayQuestion(mCurrentQuestion!!)
        startCountDown()
        mProgressBar = ProgressDialog(context)

        binding.submitButton.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER") { view: View ->
            if (mNumberOfQuestions != 1) {
                if (mNumberOfQuestions == 2)
                    submitButton.text = "Submit"
                val checkedId = binding.questionRadioGroup.checkedRadioButtonId
                if (-1 != checkedId) {
                    --mNumberOfQuestions
                    var answerIndex = 0
                    when (checkedId) {
                        R.id.secondAnswerRadioButton -> answerIndex = 1
                        R.id.thirdAnswerRadioButton -> answerIndex = 2
                        R.id.fourthAnswerRadioButton -> answerIndex = 3
                    }
                    // Do nothing if nothing is checked (id == -1)

                    if (answerIndex == mCurrentQuestion!!.answerIndex - 1) {
                        //Toast.makeText(context, "Correct", Toast.LENGTH_SHORT).show()
                        mScore++
                        // Advance to the next question
                    } else {
                        wrongQuestList.add(numberOfWrong, mCurrentQuestion!!)
                        numberOfWrong++
                        //Toast.makeText(context, "Wrong Answer", Toast.LENGTH_SHORT).show()
                    }
                    mCurrentQuestion = mQuestionBank!!.question
                    displayQuestion(mCurrentQuestion!!)

                } else {
                    Toast.makeText(context, "select an answer", Toast.LENGTH_LONG).show()
                }
            } else {
                countDownTimer!!.cancel()
                endGame()
            }
        }

//        binding.backButton.setOnClickListener {
//
//            if (mNumberOfQuestions  != 0) {
//
//                mCurrentQuestion = mQuestionBank!!.questionBack
//                displayQuestion(mCurrentQuestion!!)
//                mNumberOfQuestions++
//
//            }else{
//                backButton.text = "End"
//                Toast.makeText(context,"No previous questions", Toast.LENGTH_LONG).show()
//            }
//        }

        //txtView!!.text = "$subjArgs \n $quesArgs \n $timeArgs \n $schoolArgs"

        requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val builder = AlertDialog.Builder(context!!)

                builder.setTitle("Warning!")
                    .setMessage("Do you really want to terminate quiz? ")
                    .setPositiveButton("YES") { _, _ ->
                        countDownTimer!!.cancel()
                        requireView().findNavController().navigateUp()
                        remove()

                    }
                    .setNegativeButton("No"){_,_->
                        isEnabled=true
                    }
                    .setCancelable(false)
                    .create()
                    .show()
                return
            }

        })

        return binding.root
    }

    private fun endGame() {
        val builder = AlertDialog.Builder(requireContext())

        builder.setTitle("submitted!")
            .setMessage("Proceed for Results and Corrections")
            .setPositiveButton("OK") { _, _ ->
                val action = QuizFragmentDirections.actionQuizFragmentToResultFragment(
                    wrongQuestList.toTypedArray(),
                    qCounter,
                    mScore,
                    numberOfWrong
                )
                findNavController().navigate(action)
                //onDestroyView()
            }
            .setCancelable(false)
            .create()
            .show()
    }
    private fun endBack() {
        val builder = AlertDialog.Builder(requireContext())

        builder.setTitle("Questions Ended!")
            .setMessage("Can not go back")
            .setPositiveButton("OK") { _, _ ->

                //findNavController().navigate(action)
                //onDestroyView()
            }
            .setCancelable(false)
            .create()
            .show()
    }

    @InternalSerializationApi
    private fun generateQuestions(): QuestionBank? {
        val questionList: List<Question>
        try {
            val inputStream: InputStream = requireContext().assets.open(url + ".json")
            val jsonFile = inputStream.bufferedReader().use { it.readText() }
            val json = Json(JsonConfiguration.Stable)
            questionList = json.parse(Question.serializer().list, jsonFile).shuffled().subList(0, mNumberOfQuestions)
            //questionList.size = questionList + mNumberOfQuestions - mNumberOfQuestions
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return QuestionBank(questionList)
    }

    private fun displayQuestion(question: Question) {
        qCounter++
        radioGroup!!.clearCheck()
        //binding.questionText.latex = htmlToText(question.question)
        binding.questionText.text = question.question
        binding.radio1.text = question.options[0]
        binding.radio2.text = question.options[1]
        binding.radio3.text = question.options[2]
        binding.radio4.text = question.options[3]
        //binding.secondAnswerRadioButton.text = question.options[1]
        //binding.thirdAnswerRadioButton.text = question.options[2]
        //binding.fourthAnswerRadioButton.text = question.options[3]
        txtQuestionCount!!.text = "Quest: $mNumberOfQuestions"
        //txtQuestionCount!!.text = "Quest: $mNumberOfQuestions/${mQuestionBank!!.mQuestionList.size}"

    }
//    private fun displayPreviousQuestion(question: Question) {
//        --qCounter
//        radioGroup!!.clearCheck()
//        //binding.questionText.latex = htmlToText(question.question)
//        binding.questionText.text = question.question
//        binding.radio1.text = question.options[0]
//        binding.radio2.text = question.options[1]
//        binding.radio3.text = question.options[2]
//        binding.radio4.text = question.options[3]
//        //binding.secondAnswerRadioButton.text = question.options[1]
//        //binding.thirdAnswerRadioButton.text = question.options[2]
//        //binding.fourthAnswerRadioButton.text = question.options[3]
//        txtQuestionCount!!.text = "Quest: $mNumberOfQuestions"
//        //txtQuestionCount!!.text = "Quest: $mNumberOfQuestions/${mQuestionBank!!.mQuestionList.size}"
//
//    }

    private fun startCountDown() {
        countDownTimer = object : CountDownTimer(timeLeft, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeft = millisUntilFinished
                updateCountDown()
            }

            override fun onFinish() {
                timeLeft = 0
                countDownTimer!!.cancel()
                val action = QuizFragmentDirections.actionQuizFragmentToResultFragment(
                    wrongQuestList.toTypedArray(),
                    qCounter,
                    mScore,
                    numberOfWrong
                )
                findNavController().navigate(action)
            }
        }.start()
    }
    private fun updateCountDown() {
        val sec = (timeLeft / 1000).toInt() % 60
        val min = (timeLeft / 1000).toInt() / 60 % 60

        val timeFormat = String.format(Locale.getDefault(), "%02d:%02d", min, sec)
        txtCounter!!.setText("Time: " + timeFormat)

        if (timeLeft < 10000) {
            txtCounter!!.setTextColor(Color.RED)
        } else {
            txtCounter!!.setTextColor(colorStateListCountDown)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (countDownTimer != null) {
            countDownTimer!!.cancel()
        }
    }

    companion object {
        private var COUNTDOWN_TIMER: Long = 1800000
    }

}