package com.nikolam.feature_feed.presentation

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nikolam.feature_feed.R
import com.nikolam.feature_feed.databinding.FeedFragmentBinding
import com.nikolam.feature_feed.di.feedModule
import org.koin.android.ext.android.inject
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import timber.log.Timber

class FeedFragment : Fragment() {

    private val viewModel: FeedViewModel by inject()

    private var _binding: FeedFragmentBinding? = null

    private val binding get() = _binding!!

//    private val stateObserver = Observer<NewConfessionViewModel.ViewState> {
//
//    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FeedFragmentBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.bottomNav.menu.setGroupCheckable(0, false, true)

        binding.bottomNav.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.new_bar_item -> {
                    viewModel.navigateToNewConfession()
                    true
                }
                else -> true
            }
        }

        //viewModel.stateLiveData.observe(viewLifecycleOwner ,stateObserver)
        return view
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        loadKoinModules(feedModule)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        unloadKoinModules(feedModule)
    }
}