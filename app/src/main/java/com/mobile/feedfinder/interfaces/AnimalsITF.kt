package com.mobile.feedfinder.interfaces

import com.mobile.feedfinder.model.Animals

interface AnimalsITF {

    fun createAnimal(animals: Animals)
    fun listAnimal(animals: Animals)
}