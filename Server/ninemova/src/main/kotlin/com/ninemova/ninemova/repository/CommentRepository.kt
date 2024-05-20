package com.ninemova.ninemova.repository

import com.ninemova.ninemova.dto.Comment
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


@Repository
interface CommentRepository : CrudRepository<Comment, Int> {

    fun findAllBy(): List<Comment>
}