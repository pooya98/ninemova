package com.ninemova.ui.myPage

import androidx.fragment.app.viewModels
import com.ninemova.R
import com.ninemova.databinding.FragmentMyPageBinding
import com.ninemova.ui.base.BaseFragment
import com.ninemova.ui.util.ErrorMessage

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
                showMessage(ErrorMessage.EMPTY_FAVORITE_MOVIES_MESSAGe)
            } else {
                with(myPageViewModel) {
                    fetchActorResponse()
                    fetchGenreResponse()
                    fetchUserTagResponse()
                    fetchKeywordResponse()
                }
            }
        }
        myPageViewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            if (errorMessage != null) {
                showMessage(errorMessage)
            }
        }
    }
}
