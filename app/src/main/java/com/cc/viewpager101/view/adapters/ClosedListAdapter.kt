package com.cc.viewpager101.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cc.viewpager101.databinding.ClosedViewHolderBinding
import com.cc.viewpager101.model.Case
import com.cc.viewpager101.util.LogToConsole
import com.google.android.gms.maps.model.LatLng

class ClosedListAdapter : RecyclerView.Adapter<ClosedListAdapter.ClosedListViewHolder>() {

    private var dataList = emptyList<Case>()

    /*private var dataList = listOf(
        Case("missing pen", 1, LatLng(20.0, 30.0), false),
        Case("missing cat", 2, LatLng(40.0, 50.0), false),
        Case("missing lamp", 3, LatLng(60.0, 70.0), false),
        Case("missing paper", 4, LatLng(80.0, 90.0), false)
    )*/
    class ClosedListViewHolder(binding: ClosedViewHolderBinding) : RecyclerView.ViewHolder(binding.root) {
        val closedCaseName = binding.closedCaseName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClosedListViewHolder {
        val binding = ClosedViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ClosedListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ClosedListViewHolder, position: Int) {
        val data = dataList[position]
        LogToConsole.log("closed data in onBind : $data")
        holder.closedCaseName.text = data.caseTitle


    }

    fun update (list:List<Case>) {
        LogToConsole.log("closed list update : $list")
        this.dataList = list
        notifyDataSetChanged()
    }

}