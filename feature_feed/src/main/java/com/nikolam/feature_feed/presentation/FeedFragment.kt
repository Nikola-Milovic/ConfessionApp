package com.nikolam.feature_feed.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.nikolam.feature_feed.R
import com.nikolam.feature_feed.databinding.FeedFragmentBinding
import com.nikolam.feature_feed.di.feedModule
import org.koin.android.ext.android.inject
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import timber.log.Timber

class FeedFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener,
    AdapterView.OnItemSelectedListener, ConfessionClickListener {

    val sortByArray = listOf("newest", "oldest", "mostcommented")

    //TODO add shared preference or something so that the sortBy stays consistent
    private var sortBy = "newest"

    private val viewModel: FeedViewModel by inject()

    private var _binding: FeedFragmentBinding? = null

    private val binding get() = _binding!!

    private lateinit var adapter: FeedAdapter

    private val stateObserver = Observer<FeedViewModel.ViewState> {
        if (it.isSuccess) {
            adapter.newData(it.confessions)
            binding.swipeRefresh.isRefreshing = false
        } else if (it.isLoading) {
            binding.swipeRefresh.isRefreshing = true
        } else if (it.isError) {
            binding.swipeRefresh.isRefreshing = false
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FeedFragmentBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.bottomNav.menu.setGroupCheckable(0, false, true)

        binding.bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.new_bar_item -> {
                    viewModel.navigateToNewConfession()
                    true
                }
                else ->{
                    viewModel.navigateToUnderConstruction()
                    true
                }
            }
        }

        adapter = FeedAdapter(this, this)

        binding.swipeRefresh.setOnRefreshListener(this)

        binding.feedRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.feedRecyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )

        binding.feedRecyclerView.adapter = adapter


        viewModel.stateLiveData.observe(viewLifecycleOwner, stateObserver)

        viewModel.getConfessions(sortBy)

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        loadKoinModules(feedModule)
    }

    override fun onDetach() {
        super.onDetach()
        activity?.actionBar?.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        unloadKoinModules(feedModule)
    }

    override fun onRefresh() {
        viewModel.getConfessions(sortBy)
    }

    override fun onClick(id: String) {
        viewModel.navigateToConfessionDetailScreen(id)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (sortByArray[position] != sortBy) {
            sortBy = sortByArray[position]
            viewModel.getConfessions(sortBy)
        }
        adapter.setSortBy(position)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        //
    }
}