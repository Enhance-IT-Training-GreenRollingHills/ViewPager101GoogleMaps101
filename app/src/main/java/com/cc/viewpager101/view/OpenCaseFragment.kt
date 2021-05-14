package com.cc.viewpager101.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.cc.viewpager101.viewmodel.OpenCaseListViewModel
import com.cc.viewpager101.databinding.OpenCaseFragmentLayoutBinding
import com.cc.viewpager101.model.Case
import com.cc.viewpager101.util.LogToConsole
import com.cc.viewpager101.view.adapters.OpenCaseListAdapter
import kotlinx.coroutines.launch

class OpenCaseFragment : Fragment(), OpenCaseListAdapter.ClickDelegate {

    lateinit var binding : OpenCaseFragmentLayoutBinding

    private val viewModel by viewModels<OpenCaseListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = OpenCaseFragmentLayoutBinding.inflate(inflater, container, false)
        return binding.root
        //return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val linearLayoutManager = LinearLayoutManager(context)
        binding.recyclerView.layoutManager = linearLayoutManager

        val adapter = OpenCaseListAdapter(this)
        binding.recyclerView.adapter = adapter

        viewModel.getOpenCases().observe(viewLifecycleOwner, Observer { list ->
            LogToConsole.log("open list : $list")
            adapter.update(list)
        })


    }

    override fun switchAction(data:Case) {
        lifecycleScope.launch {
            viewModel.updateCase(data)

        }
    }
}