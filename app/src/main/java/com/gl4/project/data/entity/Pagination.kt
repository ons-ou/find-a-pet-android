package com.gl4.project.data.entity

data class Pagination(
    val count_per_page: Int,
    val current_page: Int,
    val total_count: Int,
    val total_pages: Int
)