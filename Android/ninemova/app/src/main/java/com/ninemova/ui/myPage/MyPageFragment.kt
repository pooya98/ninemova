package com.ninemova.ui.myPage

import androidx.fragment.app.viewModels
import com.ninemova.R
import com.ninemova.databinding.FragmentMyPageBinding
import com.ninemova.ui.base.BaseFragment

class MyPageFragment : BaseFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {

    private val myPageViewModel: MyPageViewModel by viewModels()

    override fun initView() {
        with(binding) {
            viewModel = myPageViewModel
        }
        observerFavoriteMovies()
    }

    private fun observerFavoriteMovies() {
        myPageViewModel.favoriteMovies.observe(viewLifecycleOwner) { favoriteMovies ->
            if (favoriteMovies.isBlank()) {
                showMessage(getString(R.string.favorite_movies_empty_message))
            } else {
                with(myPageViewModel) {
                    fetchActorResponse()
                    fetchGenreResponse()
                    fetchUserTagResponse()
                    fetchKeywordResponse()
                }
            }
        }
    }
}
