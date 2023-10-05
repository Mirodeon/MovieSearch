package com.mirodeon.moviesearch.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mirodeon.moviesearch.R
import com.mirodeon.moviesearch.activity.MainActivity
import com.mirodeon.moviesearch.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {
    private var binding: FragmentSearchBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setInputSearch()
    }

    override fun onResume() {
        super.onResume()
        showContent()
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    private fun setInputSearch() {
        binding?.searchInputLayout?.setEndIconOnClickListener {
            showContent()
        }
    }

    private fun showContent() {
        val input = binding?.searchEditText?.text?.toString()
        if (!input.isNullOrEmpty()) {
            (activity as MainActivity).show(ContentSearchFragment(input), R.id.containerContent)
        } else {
            (activity as MainActivity).show(EmptySearchFragment(), R.id.containerContent)
        }
    }

}