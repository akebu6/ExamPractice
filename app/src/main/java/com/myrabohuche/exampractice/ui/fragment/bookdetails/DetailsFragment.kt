package com.myrabohuche.exampractice.ui.fragment.bookdetails

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.myrabohuche.exampractice.R
import com.myrabohuche.exampractice.databinding.FragmentDetailsBinding
import com.myrabohuche.exampractice.model.Book
import com.myrabohuche.exampractice.model.Chapter
import com.myrabohuche.exampractice.utils.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.io.InputStream
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import java.io.IOException

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding

    private lateinit var option: Spinner
    private lateinit var result: TextView
    private var book: Book? = null

    private lateinit var url: String

    @InternalSerializationApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(inflater)

        option = binding.spOption
        result = binding.tvResult
        book = DetailsFragmentArgs.fromBundle(requireArguments()).booksArgs
        binding.txtBookTitle.text = book!!.title

        option.adapter = ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, book!!.pages.map { it.chapIndex })
        option.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                result.text = "Select an Option"

            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                url = parent!!.getItemAtPosition(position) as String
                display(generateBooksByChap())
            }
        }
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

    @InternalSerializationApi
    private fun generateBooksByChap(): Chapter? {
        val chapter: Chapter
        try {
            val inputStream: InputStream = requireContext().assets.open(url + ".json")
            val jsonFile = inputStream.bufferedReader().use { it.readText() }
            val json = Json(JsonConfiguration.Stable)
            chapter = json.parse(Chapter.serializer(), jsonFile)
        }catch (e: IOException){
            e.printStackTrace()
            return null
        }
        return chapter
    }
    private fun display(chapter: Chapter?) {
        binding.txtBody.text = chapter!!.text
        binding.txtChapTitle.text = chapter.chapIndex

    }
}