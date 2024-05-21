package com.ninemova.ninemova.service

import com.ninemova.ninemova.dto.Reply
import com.ninemova.ninemova.repository.ReplyRepository
import lombok.AllArgsConstructor
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service


@Service
@RequiredArgsConstructor
@AllArgsConstructor
class ReplyServiceImpl(private val replyRepository: ReplyRepository) : ReplyService {

    override fun createReply(reply: Reply): Reply = replyRepository.save(reply)

    override fun getRepliesByCommentId(id: Int): List<Reply> = replyRepository.findAllByCommentId(id)

    override fun deleteReplyById(id: Int): Boolean {
        if (replyRepository.existsById(id).not()) {
            return false
        }
        replyRepository.deleteById(id)
        return true
    }
}