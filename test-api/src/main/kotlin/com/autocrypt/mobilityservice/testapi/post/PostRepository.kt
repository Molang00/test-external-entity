package com.autocrypt.mobilityservice.testapi.post

import com.autocrypt.mobilityservice.testentity.entity.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository: JpaRepository<Post, Long> {
}