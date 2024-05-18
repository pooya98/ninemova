package com.ninemova.ninemova.dto

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id


@Entity
class User(
    @Id @GeneratedValue var id: Int? = null,
    @Column(nullable = false, unique = false)
    val userName: String,
    @Column(nullable = false, unique = false)
    val passWord: String,
    @Column
    val nickName: String,
    @Column
    val profileImageUrl: String? = null,
)
