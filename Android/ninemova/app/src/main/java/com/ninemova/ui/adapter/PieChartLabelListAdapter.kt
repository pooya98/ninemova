package com.ninemova.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.ninemova.R
import com.ninemova.databinding.ItemPiechartLabelBinding
import com.ninemova.domain.data.PieChartItem

class PieChartLabelListAdapter(context: Context, private val pieChartItems: List<PieChartItem>) :
    ArrayAdapter<PieChartItem>(context, 0, pieChartItems) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding: ItemPiechartLabelBinding = if (convertView == null) {
            DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.item_piechart_label,
                parent,
                false
            )
        } else {
            DataBindingUtil.getBinding(convertView)!!
        }

        binding.pieChartItem = getItem(position)
        binding.executePendingBindings()
        return binding.root
    }
}
