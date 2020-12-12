package com.nikolam.feature_feed.presentation

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nikolam.feature_feed.R
import com.nikolam.feature_feed.databinding.FeedFragmentBinding
import com.nikolam.feature_feed.di.feedModule
import org.koin.android.ext.android.inject
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import timber.log.Timber

class FeedFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener, AdapterView.OnItemSelectedListener{

    val sortByArray = listOf("newest", "oldest","popular", "mostliked")

    private var sortBy = "newest"

    private val viewModel: FeedViewModel by inject()

    private var _binding: FeedFragmentBinding? = null

    private val binding get() = _binding!!

    private lateinit var adapter : FeedAdapter

    private val stateObserver = Observer<FeedViewModel.ViewState> {
        if(it.isSuccess){
            adapter.newData(it.confessions)
            binding.swipeRefresh.isRefreshing = false
        } else if(it.isLoading){
            binding.swipeRefresh.isRefreshing = true
        } else if (it.isError){
            binding.swipeRefresh.isRefreshing = false
        }
    }

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

        adapter = FeedAdapter(this)

        binding.swipeRefresh.setOnRefreshListener(this)

        binding.feedRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.feedRecyclerView.addItemDecoration(
                DividerItemDecoration(
                        requireContext(),
                        DividerItemDecoration.VERTICAL
                )
        )

        binding.feedRecyclerView.adapter = adapter


        viewModel.stateLiveData.observe(viewLifecycleOwner ,stateObserver)
        return view
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        loadKoinModules(feedModule)
        viewModel.getConfessions("")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        unloadKoinModules(feedModule)
    }

    override fun onRefresh() {
        viewModel.getConfessions("")
        Timber.d("Refresh")
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        sortBy = sortByArray[position]
        Timber.d(sortBy)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        //
    }
}