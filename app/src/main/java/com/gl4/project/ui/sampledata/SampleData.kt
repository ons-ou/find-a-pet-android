package com.gl4.project.ui.sampledata

import com.gl4.project.data.entity.Address
import com.gl4.project.data.entity.Animal
import com.gl4.project.data.entity.Attributes
import com.gl4.project.data.entity.BreedsX
import com.gl4.project.data.entity.Colors
import com.gl4.project.data.entity.Contact
import com.gl4.project.data.entity.Environment
import com.gl4.project.data.entity.LinksX
import com.gl4.project.data.entity.Organization
import com.gl4.project.data.entity.Photo
import com.gl4.project.data.entity.SelfX
import com.gl4.project.data.entity.TypeX


fun SampleAnimal() = Animal(
    _links = LinksX(
        self = SelfX(href = "/v2/animals/120"),
        type = TypeX(href = "/v2/types/dog"),
        organization = Organization(href = "/v2/organizations/nj333")
    ),
    age = "Young",
    attributes = Attributes(
        spayed_neutered = false,
        house_trained = true,
        declawed = false,
        special_needs = true,
        shots_current = false
    ),
    breeds = BreedsX(
        primary = "Akita",
        secondary = null,
        mixed = false,
        unknown = false
    ),
    coat = "Black",
    colors = Colors(
        primary = "Black",
        secondary = "White",
        tertiary = false
    ),
    description = "Spot is an amazing dog",
    distance = 0.0,
    environment = Environment(
        children = false,
        dogs = false,
        cats = false
    ),
    gender = "Male",
    id = "120",
    name = "Spot",
    organization_id = "NJ333",
    photos = listOf(
        Photo(
            small = "https://photos.petfinder.com/photos/pets/42706540/1/?bust=1546042081&width=100",
            medium = "https://photos.petfinder.com/photos/pets/42706540/1/?bust=1546042081&width=300",
            large = "https://photos.petfinder.com/photos/pets/42706540/1/?bust=1546042081&width=600",
            full = "https://photos.petfinder.com/photos/pets/42706540/1/?bust=1546042081"
        )
    ),
    published_at = "2018-12-22T20:31:32+0000",
    size = "Medium",
    species = "Dog",
    status = "adoptable",
    tags = listOf("Cute", "Intelligent", "Large", "Playful", "Happy", "Affectionate"),
    type = "Dog",
    url = "https://www.petfinder.com/dog/spot-120/nj/jersey-city/nj333-petfinder-test-account/?referrer_id=d7e3700b-2e07-11e9-b3f3-0800275f82b1",
    videos = listOf(),
    contact = Contact(Address("","", "Tunis", "Tunisia", "200", "TN"), "email", "")
)

