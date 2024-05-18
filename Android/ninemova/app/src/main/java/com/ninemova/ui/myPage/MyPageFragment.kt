package com.ninemova.ui.myPage

import android.graphics.Color
import com.ninemova.R
import com.ninemova.databinding.FragmentMyPageBinding
import com.ninemova.ui.base.BaseFragment
import org.eazegraph.lib.charts.PieChart
import org.eazegraph.lib.models.PieModel


class MyPageFragment : BaseFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {


    override fun initView() {

        val piechart_keyword: PieChart = binding.piechartKeyword

        piechart_keyword.addPieSlice(PieModel("R", 30F, Color.parseColor("#FFA726")))
        piechart_keyword.addPieSlice(PieModel("Python", 40F, Color.parseColor("#66BB6A")))
        piechart_keyword.addPieSlice(PieModel("C++", 30F, Color.parseColor("#EF5350")))

        val piechart_genre: PieChart = binding.piechartGenre
        piechart_genre.addPieSlice(PieModel("R", 30F, Color.parseColor("#FFA726")))
        piechart_genre.addPieSlice(PieModel("Python", 40F, Color.parseColor("#66BB6A")))
        piechart_genre.addPieSlice(PieModel("C++", 30F, Color.parseColor("#EF5350")))

        val piechart_actor: PieChart = binding.piechartActor
        piechart_actor.addPieSlice(PieModel("R", 30F, Color.parseColor("#FFA726")))
        piechart_actor.addPieSlice(PieModel("Python", 40F, Color.parseColor("#66BB6A")))
        piechart_actor.addPieSlice(PieModel("C++", 30F, Color.parseColor("#EF5350")))

        piechart_keyword.startAnimation();
        piechart_genre.startAnimation();
        piechart_actor.startAnimation();
    }
}
