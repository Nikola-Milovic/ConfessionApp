package com.nikolam.feature_confession.presentation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikolam.feature_confession.databinding.ConfessionFragmentBinding
import com.nikolam.feature_confession.di.confessionModule
import org.koin.android.ext.android.inject
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import timber.log.Timber

class ConfessionFragment : Fragment() {

    private val viewModel: ConfessionViewModel by inject()

    private var _binding: ConfessionFragmentBinding? = null

    private val binding get() = _binding!!

    private lateinit var commentAdapter : CommentAdapter

    private var id : String? = ""

    private val stateObserver = Observer<ConfessionViewModel.ViewState> {
        if(it.isSuccess){
            binding.textTextView.text = it.confession?.text
            binding.dateTextView.text = it.confession?.date
        }
        if(it.comments.isNotEmpty()){
            commentAdapter.newData(it.comments)
            Timber.i(it.comments.toString())
        } else {

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = ConfessionFragmentBinding.inflate(inflater, container, false)
        val view = binding.root

        arguments?.let {
            id = it.getString("id")
            viewModel.getConfession(id ?: "")
        }

        commentAdapter = CommentAdapter()

        binding.commentsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.commentsRecyclerView.addItemDecoration(
                DividerItemDecoration(
                        requireContext(),
                        DividerItemDecoration.VERTICAL
                )
        )

        binding.goBackButton.setOnClickListener {
            activity?.supportFragmentManager?.popBackStackImmediate()
        }

        binding.addCommentTextView.setOnClickListener {
            viewModel.navigateToAddCommentFragment(id ?: "")
        }

        binding.commentsRecyclerView.adapter = commentAdapter

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