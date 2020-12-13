package com.nikolam.feature_confession.presentation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.nikolam.feature_confession.databinding.ConfessionFragmentBinding
import com.nikolam.feature_confession.di.confessionModule
import org.koin.android.ext.android.inject
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class ConfessionFragment : Fragment() {

    private val viewModel: ConfessionViewModel by inject()

    private var _binding: ConfessionFragmentBinding? = null

    private val binding get() = _binding!!

    private val stateObserver = Observer<ConfessionViewModel.ViewState> {

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = ConfessionFragmentBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel.stateLiveData.observe(viewLifecycleOwner ,stateObserver)
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        loadKoinModules(listOf(confessionModule))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        unloadKoinModules(confessionModule)
    }

}