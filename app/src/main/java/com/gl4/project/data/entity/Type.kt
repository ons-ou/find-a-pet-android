package com.gl4.project.data.entity

data class Type(
    val name: String,
    val _links: Links?,
    val coats: List<String>?,
    val colors: List<String>?,
    val genders: List<String>?,

)