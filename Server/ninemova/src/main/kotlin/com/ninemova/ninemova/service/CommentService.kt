package com.ninemova.ninemova.service

import com.ninemova.ninemova.dto.Comment

interface CommentService {

    fun createComment(comment: Comment): Comment

    fun getComments(): List<Comment>

    fun updateComment(id: Int, comment: Comment): Comment?

    fun deleteComment(id: Int): Boolean
}