package com.myrabohuche.exampractice.ui.fragment.dialog

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.myrabohuche.exampractice.R
import com.myrabohuche.exampractice.databinding.LoginDialogBinding
import com.myrabohuche.exampractice.ui.activity.MainActivity
import com.myrabohuche.exampractice.utils.PreferenceHelper
import com.myrabohuche.exampractice.utils.PreferenceHelper.get
import com.myrabohuche.exampractice.utils.PreferenceHelper.set

class LoginDialog: DialogFragment() {


    private val mainActivity: MainActivity
        get() {
            return activity as? MainActivity ?: throw IllegalStateException("Not attached!")
        }

    private lateinit var loginDialogBinding: LoginDialogBinding

    ///global variables
    private var pkNum: String? = null
    //private var password: String? = null
    private var pk: TextInputEditText? = null
    private var isUserActivated: String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        getDialog()!!.getWindow()?.setBackgroundDrawableResource(R.drawable.round_corner)

        loginDialogBinding = LoginDialogBinding.inflate(inflater, container, false)

        pk = loginDialogBinding.productKey

        loginDialogBinding.imageView.setOnClickListener {
            dialog!!.hide()
            //Toast.makeText(context,"Cancel Button Clicked", Toast.LENGTH_LONG).show()
        }
        loginDialogBinding.activationButton.setOnClickListener {
            //Toast.makeText(context,"Activation Button Clicked", Toast.LENGTH_LONG).show()
            makeSubmission()
        }

        loginDialogBinding.requestButton.setOnClickListener {
            findNavController().navigate(R.id.action_startQuizFragment_to_authenticationFragment)
            dialog!!.hide()

        }
        return loginDialogBinding.root
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.40).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    private fun makeSubmission() {
        pkNum = (pk!!.text.toString())
        val prefs: SharedPreferences = PreferenceHelper.defaultPrefs(requireContext())
        val pk = prefs["1", "wrongproductkey"]
        Toast.makeText(context,"$pk", Toast.LENGTH_LONG).show()

        if (getValidInput()) {
            loginUser(pkNum!!, pk!!)
        }
    }

    private fun getValidInput(): Boolean {

        if (pk!!.text.isNullOrEmpty()) {
            Toast.makeText(context,"Enter Product Key", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }

    fun loginUser(prefInput: String, enteredInput: String) {

        if (prefInput.equals(enteredInput)) {

            val isActivated = "yes"
            val prefs: SharedPreferences = PreferenceHelper.defaultPrefs(requireContext())
            isUserActivated = isActivated.replace("","")
            prefs["2"] = isUserActivated
            Toast.makeText(context,"Application now activated", Toast.LENGTH_LONG).show()
            dialog!!.hide()
            findNavController().navigateUp()
        }else{
            Toast.makeText(context,"Invalid Product Key", Toast.LENGTH_LONG).show()
        }
    }
}