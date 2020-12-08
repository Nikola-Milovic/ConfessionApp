package com.nikolam.feature_newconfession.presentation

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nikolam.common.di.networkingModule
import com.nikolam.feature_newconfession.R
import com.nikolam.feature_newconfession.databinding.NewConfessionFragmentBinding
import com.nikolam.feature_newconfession.di.newConfessionModule
import org.koin.android.ext.android.inject
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class NewConfessionFragment : Fragment() {

    private val viewModel: NewConfessionViewModel by inject()

    private var _binding: NewConfessionFragmentBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = NewConfessionFragmentBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.confessButton.setOnClickListener {
            viewModel.saveConfession(binding.confessionTextEditText.text.toString())
        }

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        loadKoinModules(listOf(networkingModule, newConfessionModule))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        unloadKoinModules(newConfessionModule)
    }

}