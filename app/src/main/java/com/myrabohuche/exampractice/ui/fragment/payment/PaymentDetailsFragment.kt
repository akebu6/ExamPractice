package com.myrabohuche.exampractice.ui.fragment.payment

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.myrabohuche.exampractice.R
import com.myrabohuche.exampractice.databinding.FragmentPaymentDetailsBinding
import com.myrabohuche.exampractice.utils.PreferenceHelper
import com.myrabohuche.exampractice.utils.PreferenceHelper.get
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.community_fragment.*
import kotlinx.android.synthetic.main.fragment_payment_details.*

class PaymentDetailsFragment : Fragment() {

    private lateinit var binding: FragmentPaymentDetailsBinding
    private var txtPhone: TextView? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPaymentDetailsBinding.inflate(layoutInflater)

        addEventHandlers()
        binding.liveNewsText.isSelected=true

        requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (isEnabled){
                    findNavController().navigateUp()
                    isEnabled=true
                }
            }
        })
//        val onBackPressedCallback = object : OnBackPressedCallback(true) {
//            override fun handleOnBackPressed() {
//                //some logic that needs to be run before fragment is destroyed
//                findNavController().navigateUp()
//            }
//        }
//        requireActivity().onBackPressedDispatcher.addCallback(
//            onBackPressedCallback
//        )

        return binding.root
    }

    private fun addEventHandlers() {
        binding.phoneCall.setOnClickListener { goToCall(getString(R.string.link_phone))}
    }

    private fun goToCall(url: String){
        val uriUrl = Uri.parse("tel:" + url)
        val call = Intent(Intent.ACTION_DIAL, uriUrl)
        call.data = uriUrl
        startActivity(call)
    }
//    override fun onStart() {
//
//        val prefs: SharedPreferences = PreferenceHelper.defaultPrefs(requireContext())
//        //val isUserActivated = prefs["2", "no"]
//        val isUserDetailsStored = prefs["1", "wrongproductkey"]
//
//        if (isUserDetailsStored.equals("wrongproductkey")) {
//            findNavController().navigate(R.id.action_paymentDetailsFragment_to_authenticationFragment)
//
//        }else {
//            Toast.makeText(context, "", Toast.LENGTH_LONG).show()
//            //findNavController().popBackStack()
//        }
//        super.onStart()
//    }
}