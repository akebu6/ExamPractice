package com.myrabohuche.exampractice.ui.fragment.auth

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.observe
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.myrabohuche.exampractice.R
import com.myrabohuche.exampractice.databinding.ConfirmationDialogBinding
import com.myrabohuche.exampractice.databinding.FailureDialogBinding
import com.myrabohuche.exampractice.databinding.FragmentAuthenticationBinding
import com.myrabohuche.exampractice.databinding.SuccessDialogBinding
import com.myrabohuche.exampractice.datasource.network.LoadingStatus
import com.myrabohuche.exampractice.ui.activity.MainActivity
import com.myrabohuche.exampractice.utils.PreferenceHelper
import com.myrabohuche.exampractice.utils.PreferenceHelper.set
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AuthenticationFragment : DialogFragment() {


    private val mainActivity: MainActivity
        get() {
            return activity as? MainActivity ?: throw IllegalStateException("Not attached!")
        }

    @Inject lateinit var viewModel: AuthenticationViewModel
    private lateinit var binding: FragmentAuthenticationBinding
    private lateinit var confirmationDialogBinding: ConfirmationDialogBinding
    private lateinit var successDialogBinding: SuccessDialogBinding
    private lateinit var failureDialogBinding: FailureDialogBinding
    private var usersProductKey: String = ""

    private var surName: TextInputEditText? = null
    private var otherNames: TextInputEditText? = null
    private var phoneNumb: TextInputEditText? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = FragmentAuthenticationBinding.inflate(inflater, container, false)

        surName = binding.surnameNameField
        otherNames = binding.otherNamesField
        phoneNumb = binding.phoneNameField

        binding.registerButton.setOnClickListener {

            confirmationDialogBinding =
                ConfirmationDialogBinding.inflate(inflater, container, false)
            val dialog =
                MaterialAlertDialogBuilder(requireContext())
                    .setView(confirmationDialogBinding.root)
                    .setCancelable(false)
                    //getDialog()!!.getWindow()?.setBackgroundDrawableResource(R.drawable.round_corner)
                    .show()
            dialog.window!!.setBackgroundDrawableResource(R.drawable.round_corner)
            val width = (resources.displayMetrics.widthPixels * 0.65).toInt()
            val height = (resources.displayMetrics.heightPixels * 0.30).toInt()
            dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
            confirmationDialogBinding.imageView.setOnClickListener {
                dismissDialog(dialog)
            }
            confirmationDialogBinding.button.setOnClickListener {
                makeSubmission()
                dismissDialog(dialog)
            }
        }

        viewModel.loadingStatus.observe(viewLifecycleOwner) {
            when (it) {
                is LoadingStatus.Loading -> mainActivity.showLoading(it.message)
                is LoadingStatus.Success -> mainActivity.dismissLoading()
                is LoadingStatus.Error -> mainActivity.dismissLoading()
            }
        }
        viewModel.success.observe(viewLifecycleOwner) {
            successDialogBinding = SuccessDialogBinding.inflate(inflater, container, false)
            failureDialogBinding = FailureDialogBinding.inflate(inflater, container, false)
            when (it) {
                true -> {
                    showDialog(successDialogBinding.root)
                    //viewModel.resetSuccessValue()
                    Toast.makeText(context,"$usersProductKey", Toast.LENGTH_LONG).show()
                }
                false -> {
                    showDialog(failureDialogBinding.root)
                    Toast.makeText(context,"$usersProductKey", Toast.LENGTH_LONG).show()
                    //viewModel.resetSuccessValue()
                }
            }
        }
        return binding.root
    }

    private fun showDialog(view: View) {
        MaterialAlertDialogBuilder(requireContext()).setView(view)
            .setCancelable(true)
            .show()
    }

    private fun dismissDialog(dialog: androidx.appcompat.app.AlertDialog) {
        dialog.dismiss()
    }

    private fun makeSubmission() {
        if (getValidInput()) {
            viewModel.makeSubmission(
                surName!!.text.toString(),
                otherNames!!.text.toString(),
                phoneNumb!!.text.toString(),
                usersProductKey
            )
        }
    }

    private fun getValidInput(): Boolean {
        if (binding.surnameNameField.text.isNullOrEmpty() || binding.otherNamesField.text.isNullOrEmpty() || binding.phoneNameField.text.isNullOrEmpty()) {
            Toast.makeText(context,"Enter all information", Toast.LENGTH_LONG).show()
            return false
        }
        val pk= listOf("9","F","Q","K","3","L","X","5","3","2").shuffled().joinToString(separator = "",postfix = "")
        val prefs: SharedPreferences = PreferenceHelper.defaultPrefs(requireContext())

        usersProductKey = pk.replace("","")
        prefs["1"] = usersProductKey
        return true
    }

}