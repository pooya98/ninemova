package com.ninemova.ninemova.service

import com.ninemova.ninemova.dto.Reply

interface ReplyService {

    fun createReply(reply: Reply): Reply
    fun getRepliesByCommentId(id:Int): List<Reply>
    fun deleteReplyById(id: Int): Boolean
}