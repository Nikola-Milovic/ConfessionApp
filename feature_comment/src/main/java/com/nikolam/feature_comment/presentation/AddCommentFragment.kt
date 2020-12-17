package com.nikolam.feature_comment.presentation

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikolam.feature_comment.R
import com.nikolam.feature_comment.databinding.AddCommentFragmentBinding
import com.nikolam.feature_comment.di.addCommentModule
import org.koin.android.ext.android.inject
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import timber.log.Timber

class AddCommentFragment : Fragment() {


    private val viewModel: AddCommentViewModel by inject()

    private var _binding: AddCommentFragmentBinding? = null

    private val binding get() = _binding!!

    private var id : String = ""


    private val stateObserver = Observer<AddCommentViewModel.ViewState> {
        if(it.isSuccess){
            activity?.supportFragmentManager?.popBackStackImmediate()
        } else if(it.isError){
            Toast.makeText(context, "Couldn't post your comment, try again later", Toast.LENGTH_LONG).show()
        } else {

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = AddCommentFragmentBinding.inflate(inflater, container, false)
        val view = binding.root

        arguments?.let {
            id = it.getString("id")!!
           // viewModel.getConfession(id ?: "")
        }

        binding.closeButton.setOnClickListener {
            activity?.supportFragmentManager?.popBackStackImmediate()
        }

        binding.sendButton.setOnClickListener {
            viewModel.postComment(binding.commentEditText.text.toString(), id)
        }

        viewModel.stateLiveData.observe(viewLifecycleOwner ,stateObserver)
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        loadKoinModules(listOf(addCommentModule))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        unloadKoinModules(addCommentModule)
    }

}