package com.myrabohuche.exampractice.ui.fragment.community

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.myrabohuche.exampractice.R
import kotlinx.android.synthetic.main.community_fragment.*

class CommunityFragment : Fragment() {

    private var txtWhatsapp: TextView? = null
    private var urlWhatssap:String? = null

    private var txtEmail: TextView? = null
    private var urlEmail:String? = null

    private var txtPhone: TextView? = null
    private var urlPhone:String? = null

    private var emailBtn:CardView? = null
    private var txtView:TextView? = null
    private var url:String? = null

    companion object {
        fun newInstance() = CommunityFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.community_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        //emailBtn=view.findViewById(R.id.emailID)

        //txtWhatsapp = view.findViewById<TextView>(R.id.tv_whatssap)
        urlWhatssap = "https://api.whatsapp.com/send?phone=2348024088573&text=Hi,%20I%20need%20help."

        txtEmail = view.findViewById(R.id.tv_email)
        //txtEmail!!.setTextColor(Color.BLUE)
        val urlEmail = "bayokku@gmail.com"

        //txtPhone = view.findViewById<TextView>(R.id.tv_phone)
        //urlPhone = "+2348024088573"
        //clickableLinkEmail()
        //clickableLinkWhatssap()
        //clickableLinkPhone()
        addEventHandlers()

        txtEmail!!.setOnClickListener {
            composeEmail("bayokku@gmail.com","Need Help")
        }

        requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (isEnabled){
                    findNavController().navigateUp()
                    isEnabled=true
                }
            }
        })

        //txtView = view.findViewById<TextView>(R.id.link)
        url = "https://api.whatsapp.com/send?phone=2348024088573&text=Hi,%20please%20I%20add%20me%20to%20the%20group%20chat"
        //clickableLink()

    }

    fun composeEmail(addresses:String, subject: String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto: bayokku@gmail.com") // only email apps should handle this
            putExtra(Intent.EXTRA_EMAIL, addresses)
            putExtra(Intent.EXTRA_SUBJECT, subject)
        }
        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(intent)
        }
    }
    private fun addEventHandlers() {
        linkWhatsapp.setOnClickListener { goToUrl(getString(R.string.link_url))}
        linkHelp.setOnClickListener { goToUrl( getString(R.string.link_url2))}
        jamb.setOnClickListener { goToUrl(getString(R.string.link_jamb))}
        postUtme.setOnClickListener { goToUrl(getString(R.string.link_postUTME))}
        waec.setOnClickListener { goToUrl(getString(R.string.link_WAEC))}
        neco.setOnClickListener { goToUrl(getString(R.string.link_NECO))}
    }

    private fun goToUrl(url: String){
        val uriUrl = Uri.parse(url)
        val launchBrowser = Intent(Intent.ACTION_VIEW, uriUrl)
        launchBrowser.addCategory(Intent.CATEGORY_BROWSABLE)
        startActivity(launchBrowser)
    }
}