package com.gl4.project.data.entity

data class Animal(
    val _links: LinksX,
    val age: String,
    val attributes: Attributes,
    val breeds: BreedsX,
    val coat: String,
    val colors: Colors,
    val contact: Contact,
    val description: String?,
    val distance: Double,
    val environment: Environment,
    val gender: String,
    val id: String,
    val name: String,
    val organization_id: String,
    val photos: List<Photo>,
    val published_at: String,
    val size: String,
    val species: String,
    val status: String,
    val tags: List<String>,
    val type: String,
    val url: String,
    val videos: List<Video>
)