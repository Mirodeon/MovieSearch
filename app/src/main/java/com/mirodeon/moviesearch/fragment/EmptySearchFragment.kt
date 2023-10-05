package com.mirodeon.moviesearch.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mirodeon.moviesearch.R
import com.mirodeon.moviesearch.databinding.FragmentEmptySearchBinding

class EmptySearchFragment : Fragment() {
    private var binding: FragmentEmptySearchBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEmptySearchBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}