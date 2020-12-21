package com.nikolam.underconstruction

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.nikolam.underconstruction.databinding.FragmentUnderConstructionBinding

class UnderConstructionFragment : Fragment() {

    private var _binding: FragmentUnderConstructionBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUnderConstructionBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.goBackButton.setOnClickListener {
            activity?.supportFragmentManager?.popBackStackImmediate()
        }
        return view
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}