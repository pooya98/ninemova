package com.ninemova.ninemova.repository

import com.ninemova.ninemova.dto.Reply
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


@Repository
interface ReplyRepository : CrudRepository<Reply, Int> {
    fun findAllByCommentId(id:Int): List<Reply>
}