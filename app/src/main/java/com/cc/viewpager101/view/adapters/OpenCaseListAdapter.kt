package com.cc.viewpager101.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Switch
import androidx.recyclerview.widget.RecyclerView
import com.cc.viewpager101.databinding.OpenViewHolderBinding
import com.cc.viewpager101.model.Case
import com.cc.viewpager101.util.LogToConsole

class OpenCaseListAdapter(clickDelegate: ClickDelegate) :

    RecyclerView.Adapter<OpenCaseListAdapter.OpenCaseViewHolder>() {

    private var dataList = emptyList<Case>()

    private var delegate : ClickDelegate? = clickDelegate

    interface ClickDelegate {
        fun switchAction(data:Case)
    }

    class OpenCaseViewHolder(binding: OpenViewHolderBinding) : RecyclerView.ViewHolder(binding.root) {
        val openCaseName = binding.openCaseName
        val switch = binding.openCaseSwitch
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OpenCaseViewHolder {
        val binding = OpenViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)


        return OpenCaseViewHolder(
            binding
        )

    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: OpenCaseViewHolder, position: Int) {

        val data = dataList[position]

        holder.openCaseName.text = data.caseTitle
        holder.switch.setOnCheckedChangeListener(null)
        holder.switch.isChecked = data.solved

        holder.switch.setOnCheckedChangeListener { buttonView, isChecked ->
            data.solved = isChecked
            LogToConsole.log("data : $data")
            delegate?.switchAction(data)

        }
    }

    fun update (list:List<Case>) {
        this.dataList = list
        notifyDataSetChanged()
    }
}