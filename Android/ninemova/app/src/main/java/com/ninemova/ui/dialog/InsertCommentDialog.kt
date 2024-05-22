package com.ninemova.ui.dialog

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.ninemova.Network.utils.RepositoryUtils
import com.ninemova.Network.request.server.CommentRequest
import com.ninemova.R
import com.ninemova.databinding.DialogInsertCommentBinding
import com.ninemova.ui.util.ErrorMessage
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class InsertCommentDialog : DialogFragment() {

    private var _binding: DialogInsertCommentBinding? = null
    private val binding get() = _binding!!
    private val args: InsertCommentDialogArgs by navArgs()
    private val commentRepository = RepositoryUtils.commentRepository
    private val localDataStoreRepository = RepositoryUtils.localDataStoreRepository

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        isCancelable = false
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = DialogInsertCommentBinding.inflate(inflater, container, false)
        with(binding) {
            movie = args.movie
            btnCancel.setOnClickListener {
                dialog?.dismiss()
            }
            btnRegister.setOnClickListener {
                createComment()
            }
        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        resizeDialog()
    }

    private fun createComment() {
        lifecycleScope.launch {
            commentRepository.createComment(
                CommentRequest(
                    userId = localDataStoreRepository.getUserId(),
                    movieId = args.movie.id,
                    score = binding.movieRatingBar.rating.toDouble(),
                    content = binding.etMovieContent.text.toString(),
                ),
            ).collectLatest { comment ->
                if (comment == null) {
                    Snackbar.make(requireView(), ErrorMessage.REGISTER_COMMENT_ERROR_MESSAGE, Snackbar.LENGTH_LONG).show()
                } else{
                    findNavController().navigate(R.id.action_insert_comment_dialog_to_community)
                }
            }
        }
    }

    private fun resizeDialog() {
        val params: ViewGroup.LayoutParams? = dialog?.window?.attributes
        val displayMetrics = requireActivity().resources.displayMetrics
        val deviceWidth = displayMetrics.widthPixels
        val deviceHeight = displayMetrics.heightPixels
        params?.width = (deviceWidth * 0.9).toInt()
        params?.height = (deviceHeight * 0.9).toInt()
        dialog?.window?.attributes = params as WindowManager.LayoutParams
    }

    override fun onDismiss(dialog: DialogInterface) {
        _binding = null
        super.onDismiss(dialog)
    }
}
