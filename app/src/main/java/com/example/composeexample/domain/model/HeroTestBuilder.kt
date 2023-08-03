package com.example.composeexample.domain.model



class HeroTestDataBuilder {
    val id = "test-id"
    var name = ""
    var photoUrl = ""
    var description = ""
    var numElements: Int = 1

    fun withName(name: String): HeroTestDataBuilder {
        this.name = name
        return this
    }

    fun withPhotoUrl(photoUrl: String): HeroTestDataBuilder {
        this.photoUrl = photoUrl
        return this
    }

    fun withDescription(description: String): HeroTestDataBuilder {
        this.description = description
        return this
    }

    fun withNumElements(numElements: Int): HeroTestDataBuilder {
        this.numElements = numElements

        return this
    }

    fun buildList(): List<Hero> {
        val list = mutableListOf<Hero>()

        for(i in 0 until numElements) {
            list.add(
                Hero(
                    id = id,
                    name = name,
                    photo = photoUrl,
                    description = description,
                    favorite = false

                )
            )
        }

        return list.toList()
    }

    fun buildSingle() = Hero(
        id = id,
        name = name,
        photo = photoUrl,
        description = description,
        favorite = false
    )
}