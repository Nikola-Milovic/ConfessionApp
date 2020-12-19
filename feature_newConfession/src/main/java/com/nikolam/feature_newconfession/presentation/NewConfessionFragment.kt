package com.nikolam.feature_newconfession.presentation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.nikolam.common.extensions.hideKeyboard
import com.nikolam.feature_newconfession.databinding.NewConfessionFragmentBinding
import com.nikolam.feature_newconfession.di.newConfessionModule
import org.koin.android.ext.android.inject
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class NewConfessionFragment : Fragment() {

    private val viewModel: NewConfessionViewModel by inject()

    private var _binding: NewConfessionFragmentBinding? = null

    private val binding get() = _binding!!

    private val stateObserver = Observer<NewConfessionViewModel.ViewState> {

        if(it.isError){
            binding.progressBar.visibility = View.INVISIBLE
            Toast.makeText(requireContext(), "Error saving the confession, please try again later", Toast.LENGTH_SHORT).show()
        } else if(it.isSuccess){
            binding.progressBar.visibility = View.INVISIBLE
            activity?.supportFragmentManager?.popBackStackImmediate()
            viewModel.navigateToConfessionDetailScreen(it.id)
        } else if(it.isLoading){
            binding.progressBar.visibility = View.VISIBLE
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = NewConfessionFragmentBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.goBackButton.setOnClickListener {
            hideKeyboard()
            activity?.supportFragmentManager?.popBackStackImmediate()
        }

        binding.confessButton.setOnClickListener {
            viewModel.saveConfession(binding.confessionTextEditText.text.toString())
        }

        viewModel.stateLiveData.observe(viewLifecycleOwner ,stateObserver)
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        loadKoinModules(listOf(newConfessionModule))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        unloadKoinModules(newConfessionModule)
    }
}