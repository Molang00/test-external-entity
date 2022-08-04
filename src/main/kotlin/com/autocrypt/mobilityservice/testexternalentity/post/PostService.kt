package com.autocrypt.mobilityservice.testexternalentity.post

import com.autocrypt.mobilityservice.testentity.entity.Post
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PostService {
    @Autowired
    lateinit var postRepository: PostRepository

    fun getPost(id: Long): Post? {
        return postRepository.findById(id).get()
    }

    fun savePost(post: Post): Post {
        return postRepository.save(post)
    }
}