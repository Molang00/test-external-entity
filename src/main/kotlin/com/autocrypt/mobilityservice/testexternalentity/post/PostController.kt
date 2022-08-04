package com.autocrypt.mobilityservice.testexternalentity.post

import com.autocrypt.mobilityservice.testentity.entity.Post
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/post")
class PostController {
    @Autowired
    lateinit var postService: PostService

    @GetMapping("/ping")
    fun ping(): String {
        return "pong"
    }
    @GetMapping("/test")
    fun test(): String {
        return "Hello"
    }

    @GetMapping("/{id}")
    fun getPostById(
        @PathVariable
        id: Long
    ): Post? {
        return postService.getPost(id)
    }

    @PostMapping("")
    fun savePost(
        @RequestBody
        post: Post
    ): Post {
        return postService.savePost(post)
    }
}