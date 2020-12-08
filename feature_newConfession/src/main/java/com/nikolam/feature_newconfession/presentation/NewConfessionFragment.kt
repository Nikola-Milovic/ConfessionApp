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
import org.koin.core.context.loadKoinModules

class NewConfessionFragment : Fragment() {

    companion object {
        fun newInstance() = NewConfessionFragment()
    }

    private lateinit var viewModel: NewConfessionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.new_confession_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NewConfessionViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        loadKoinModules(networkingModule)
    }

}