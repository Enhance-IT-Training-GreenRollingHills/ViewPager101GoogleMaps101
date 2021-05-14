package com.cc.viewpager101.view

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.cc.viewpager101.databinding.ClosedCaseFragmentLayoutBinding
import com.cc.viewpager101.util.LogToConsole
import com.cc.viewpager101.view.adapters.ClosedListAdapter
import com.cc.viewpager101.viewmodel.ClosedListViewModel

class ClosedCaseListFragment : Fragment() {

    private lateinit var binding  :ClosedCaseFragmentLayoutBinding

    private val viewModel by viewModels<ClosedListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ClosedCaseFragmentLayoutBinding.inflate(inflater, container, false)
        return binding.root
        //return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val linearLayoutManager = LinearLayoutManager(context)
        val adapter = ClosedListAdapter()

        binding.recyclerView.layoutManager = linearLayoutManager
        binding.recyclerView.adapter = adapter

        viewModel.getAllClosedCases().observe(viewLifecycleOwner, Observer {
            LogToConsole.log("closed list : $it")
            adapter.update(it)

        })


    }


}