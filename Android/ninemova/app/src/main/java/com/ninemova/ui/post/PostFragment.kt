package com.ninemova.ui.post

import androidx.navigation.fragment.navArgs
import com.ninemova.R
import com.ninemova.databinding.FragmentPostBinding
import com.ninemova.ui.base.BaseFragment

class PostFragment : BaseFragment<FragmentPostBinding>(R.layout.fragment_post) {


    private val args: PostFragmentArgs by navArgs()

    override fun initView() {
        with(binding) {
            comment = args.comment
        }
    }

}