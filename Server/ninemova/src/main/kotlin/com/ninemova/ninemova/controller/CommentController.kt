package com.ninemova.ninemova.controller

import com.ninemova.ninemova.dto.Comment
import com.ninemova.ninemova.service.CommentService
import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
@Slf4j
class CommentController(private val commentService: CommentService) {


    @PostMapping("/register")
    fun signUp(@RequestBody comment: Comment): ResponseEntity<Comment> {
        return ResponseEntity.ok(commentService.createComment(comment))
    }

    @GetMapping("/list")
    fun getComments(): ResponseEntity<List<Comment>> {
        return ResponseEntity.ok(commentService.getComments())
    }
}