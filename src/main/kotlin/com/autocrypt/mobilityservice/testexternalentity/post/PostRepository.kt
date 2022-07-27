package com.autocrypt.mobilityservice.testexternalentity.post

import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository: JpaRepository<Object, Long> {
}