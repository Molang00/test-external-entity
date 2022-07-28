package com.autocrypt.mobilityservice.testapi.post

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PostService {
    @Autowired
    lateinit var postRepository: PostRepository

    fun getPost(id: Long): Object? {
        return postRepository.findById(id).get()
    }
}