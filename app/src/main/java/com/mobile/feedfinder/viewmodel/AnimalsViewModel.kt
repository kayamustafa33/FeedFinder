package com.mobile.feedfinder.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mobile.feedfinder.interfaces.AnimalsITF
import com.mobile.feedfinder.model.Animals


class AnimalsViewModel : ViewModel() , AnimalsITF {
    val animals = MutableLiveData<Animals>()

    override fun createAnimal(animals: Animals) {

    }

    override fun listAnimal(animals: Animals) {

    }

}