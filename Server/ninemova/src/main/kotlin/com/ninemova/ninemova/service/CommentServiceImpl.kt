package com.ninemova.ninemova.service

import com.ninemova.ninemova.dto.Comment
import com.ninemova.ninemova.repository.CommentRepository
import lombok.AllArgsConstructor
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrElse

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

    override fun updateComment(id: Int, comment: Comment): Comment? {
        val result = commentRepository.findById(id).getOrElse {
            return null
        }
        return commentRepository.save(
            result.copy(
                score = comment.score,
                content = comment.content
            )
        )
    }

    override fun deleteComment(id: Int): Boolean {
        if (commentRepository.existsById(id).not()) {
            return false
        }
        commentRepository.deleteById(id)
        return true
    }
}
