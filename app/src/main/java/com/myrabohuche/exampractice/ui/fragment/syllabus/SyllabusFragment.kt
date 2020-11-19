package com.myrabohuche.exampractice.ui.fragment.syllabus

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.myrabohuche.exampractice.R

class SyllabusFragment : Fragment() {

    companion object {
        fun newInstance() = SyllabusFragment()
    }

    private lateinit var viewModel: SyllabusViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.syllabus_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SyllabusViewModel::class.java)
        // TODO: Use the ViewModel
    }

}