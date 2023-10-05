package com.mirodeon.moviesearch.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.coroutineScope
import com.mirodeon.moviesearch.R
import com.mirodeon.moviesearch.activity.MainActivity
import com.mirodeon.moviesearch.databinding.FragmentSearchBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {
    private var binding: FragmentSearchBinding? = null
    private var jobSearch: Job? = null

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
        setAfterTextChanged()
    }

    override fun onResume() {
        super.onResume()
        jobSearch?.cancel()
        showContent()
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    private fun setInputSearch() {
        binding?.searchInputLayout?.setEndIconOnClickListener {
            jobSearch?.cancel()
            showContent()
        }
        binding?.searchEditText?.onSubmit {
            jobSearch?.cancel()
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

    private fun setAfterTextChanged() {
        binding?.searchEditText?.doAfterTextChanged {
            jobSearch?.cancel()
            jobSearch = lifecycle.coroutineScope.launch {
                delay(2000)
                showContent()
            }
        }
    }

    private fun EditText.onSubmit(handler: () -> Unit) {
        setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                handler()
            }
            true
        }
    }

}