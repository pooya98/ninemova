package com.ninemova.ninemova.service

import com.ninemova.ninemova.dto.Comment
import com.ninemova.ninemova.response.CommentResponse

interface CommentService {

    fun createComment(comment: Comment): Comment

    fun getComments(): List<CommentResponse>

    fun updateComment(id: Int, comment: Comment): Comment?

    fun deleteComment(id: Int): Boolean
}
