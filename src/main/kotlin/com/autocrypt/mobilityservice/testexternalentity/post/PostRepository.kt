package com.autocrypt.mobilityservice.testexternalentity.post

import com.autocrypt.mobilityservice.testentity.entity.Post
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PostRepository: JpaRepository<Post, Long> {
}