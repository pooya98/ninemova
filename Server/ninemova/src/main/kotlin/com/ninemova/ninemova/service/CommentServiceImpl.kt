package com.ninemova.ninemova.service

import com.ninemova.ninemova.dto.Comment
import com.ninemova.ninemova.repository.CommentRepository
import lombok.AllArgsConstructor
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
@AllArgsConstructor
class CommentServiceImpl(private val commentRepository: CommentRepository) : CommentService {

    override fun createComment(comment: Comment): Comment {
        return commentRepository.save(comment)
    }

    override fun getComments(): List<Comment> {
        return commentRepository.findAllBy()
    }
}