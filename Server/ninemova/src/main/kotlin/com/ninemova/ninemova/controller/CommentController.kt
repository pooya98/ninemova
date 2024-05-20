package com.ninemova.ninemova.controller

import com.ninemova.ninemova.dto.Comment
import com.ninemova.ninemova.service.CommentService
import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
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
    fun createComment(@RequestBody comment: Comment): ResponseEntity<Comment> {
        return ResponseEntity.ok(commentService.createComment(comment))
    }

    @GetMapping("/list")
    fun getComments(): ResponseEntity<List<Comment>> {
        return ResponseEntity.ok(commentService.getComments())
    }

    @PutMapping("/update/{id}")
    fun updateComment(@PathVariable id: Int, @RequestBody comment: Comment): ResponseEntity<Comment?> {
        val result = commentService.updateComment(id, comment)
            ?: return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null)
        return ResponseEntity.ok(result)
    }

    @DeleteMapping("/delete/{id}")
    fun updateComment(@PathVariable id: Int): ResponseEntity<Boolean> {
        val result = commentService.deleteComment(id)
        if (result.not()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result)
        }
        return ResponseEntity.ok(result)
    }
}