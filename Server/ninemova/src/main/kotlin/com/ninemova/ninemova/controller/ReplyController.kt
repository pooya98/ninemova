package com.ninemova.ninemova.controller

import com.ninemova.ninemova.dto.Reply
import com.ninemova.ninemova.service.ReplyService
import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/reply")
@RequiredArgsConstructor
@Validated
class ReplyController(private val replyService: ReplyService) {


    @PostMapping("/register")
    fun registerReply(@RequestBody reply: Reply): ResponseEntity<Reply> {
        return ResponseEntity.ok(replyService.createReply(reply))
    }

    @GetMapping("/list/{id}")
    fun getReplies(@PathVariable id: Int): ResponseEntity<List<Reply>> {
        return ResponseEntity.ok(replyService.getRepliesByCommentId(id))
    }

    @DeleteMapping("delete/{id}")
    fun deleteReply(@PathVariable id : Int) : ResponseEntity<Boolean> {
        return ResponseEntity.ok(replyService.deleteReplyById(id))
    }
}