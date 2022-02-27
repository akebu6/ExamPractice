package com.myrabohuche.exampractice.ui.fragment.home

import android.os.Bundle
import android.view.*
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.myrabohuche.exampractice.R
import com.myrabohuche.exampractice.databinding.HomeFragmentBinding
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: HomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        binding = HomeFragmentBinding.inflate(inflater)


        viewModel.downloadedJamb.observe(viewLifecycleOwner, Observer { new ->
            if (new == null)
                return@Observer
            new.map {
                val action = HomeFragmentDirections.actionHomeFragmentToStartQuizFragment(it)
                jambID.setOnClickListener {
                    findNavController().navigate(action)
                }
            }
        })

        viewModel.downloadedPostutme.observe(viewLifecycleOwner, Observer { new ->
            if (new == null)
                return@Observer
            new.map {
                val action = HomeFragmentDirections.actionHomeFragmentToStartQuizFragment(it)
                postUtme.setOnClickListener {
                    findNavController().navigate(action)
                }
            }
        })

        viewModel.downloadedWaec.observe(viewLifecycleOwner, Observer { new ->
            if (new == null)
                return@Observer
            new.map {
                val action = HomeFragmentDirections.actionHomeFragmentToStartQuizFragment(it)
                waecExam.setOnClickListener {
                    findNavController().navigate(action)
                }
            }
        })

        viewModel.downloadedNeco.observe(viewLifecycleOwner, Observer { new ->
            if (new == null)
                return@Observer
            new.map {
                val action = HomeFragmentDirections.actionHomeFragmentToStartQuizFragment(it)
                necoExam.setOnClickListener {
                    findNavController().navigate(action)
                }
            }
        })

        binding.libraryID.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_libraryFragment)
        }
        binding.communityID.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_syllabusFragment)
        }

        requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                isEnabled=true
                requireActivity().finish()
                //remove()
            }

        })

        return binding.root
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item,findNavController())
                ||super.onOptionsItemSelected(item)
    }
}