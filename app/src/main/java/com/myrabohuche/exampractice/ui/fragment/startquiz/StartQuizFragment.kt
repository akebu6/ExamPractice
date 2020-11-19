package com.myrabohuche.exampractice.ui.fragment.startquiz

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.myrabohuche.exampractice.R
import com.myrabohuche.exampractice.databinding.FragmentStartQuizBinding
import com.myrabohuche.exampractice.databinding.LoginDialogBinding
import com.myrabohuche.exampractice.model.Exam
import com.myrabohuche.exampractice.ui.activity.MainActivity
import com.myrabohuche.exampractice.utils.PreferenceHelper
import com.myrabohuche.exampractice.utils.PreferenceHelper.get

class StartQuizFragment : Fragment() {

    private val mainActivity: MainActivity
        get() {
            return activity as? MainActivity ?: throw IllegalStateException("Not attached!")
        }

    private lateinit var binding: FragmentStartQuizBinding
    private lateinit var loginDialogBinding: LoginDialogBinding
    private lateinit var exam: Exam
    private var valueSub: String = ""
    private var valueQues: String = ""
    private var valueTime: String = ""
    private var valueSchool: String = ""
    private var valueSuffix: String = ""

    lateinit var subOption: Spinner
    lateinit var subResult: TextView
    private var subTextSee: TextView? = null

    lateinit var noQuesOption: Spinner
    lateinit var noQuesResult: TextView
    private var noQuesTextSee: TextView? = null

    lateinit var timeOption: Spinner
    lateinit var timeResult: TextView
    private var titleText: TextView? = null

    lateinit var schoolOption: Spinner
    lateinit var schoolResult: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val prefs: SharedPreferences = PreferenceHelper.defaultPrefs(requireContext())
        val isUserActivated = prefs["2", "no"]

        // Inflate the layout for this fragment
        loginDialogBinding = LoginDialogBinding.inflate(inflater, container, false)
        //moveToQuiz()

        binding = FragmentStartQuizBinding.inflate(inflater, container, false)


        titleText = binding.txtExamTitle

        subResult = binding.subjectTvResult
        subOption = binding.subjectOption
        //subOption = binding.subjectOption

        noQuesOption = binding.noQuesOption
        noQuesResult = binding.noQuesTvResult
        //noQuesTextSee = binding.subjectOption

        timeOption = binding.timeOption
        timeResult = binding.timeTvResult

        schoolOption = binding.schoolOption
        schoolResult = binding.schoolTvResult

        exam = StartQuizFragmentArgs.fromBundle(requireArguments()).startArgs!!
        valueSuffix = exam.suffix

        titleText!!.text = "Welcome to ${valueSuffix} CBT Practice"

        binding.buttonId.setOnClickListener {
            if (isUserActivated.equals("yes")) {
                val action = StartQuizFragmentDirections.actionStartQuizFragmentToQuizFragment(valueSub,valueQues,valueTime,valueSchool, valueSuffix)
                findNavController().navigate(action)

            }else{
                mainActivity.onLogin(loginDialogBinding.root)
            }
        }

        subOption.adapter = ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, exam.subject)
        subOption.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                subResult.text = "Please Select an Option"
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val action1 = parent!!.getItemAtPosition(position)
                valueSub = action1.toString()

            }
        }

        noQuesOption.adapter = ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, exam.numQues)
        noQuesOption.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                noQuesResult.text = "Please Select an Option"

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val action1 = parent!!.getItemAtPosition(position)
                valueQues = action1.toString()
            }
        }

        timeOption.adapter = ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, exam.time)

        timeOption.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                subResult.text = "Please Select an Option"

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val action1 = parent!!.getItemAtPosition(position)

                valueTime = action1.toString()

            }
        }
        schoolOption.adapter = ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, exam.school)
        schoolOption.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                schoolResult.text = "Please Select an Option"

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val action1 = parent!!.getItemAtPosition(position)

                valueSchool = action1.toString()
            }
        }

        return binding.root
    }

}