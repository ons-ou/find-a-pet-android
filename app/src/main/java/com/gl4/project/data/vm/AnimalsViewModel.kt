package com.gl4.project.data.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gl4.project.data.entity.Animal
import com.gl4.project.data.entity.AnimalTypes
import com.gl4.project.data.entity.Type
import com.gl4.project.data.repositories.AnimalsRepository
import com.gl4.project.data.utilities.ResourceState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

import kotlinx.coroutines.launch

class AnimalsViewModel : ViewModel() {
    private val repository: AnimalsRepository = AnimalsRepository()
    private val _animals = MutableStateFlow<ResourceState<List<Animal>>>(ResourceState.Loading())
    val animalsResponse: StateFlow<ResourceState<List<Animal>>> get() = _animals

    private val _animal = MutableStateFlow<ResourceState<Animal>>(ResourceState.Loading())
    private val _type = MutableStateFlow<ResourceState<Type>>(ResourceState.Loading())
    val animal: StateFlow<ResourceState<Animal>> get() = _animal
   val typeSelected: StateFlow<ResourceState<Type>> get() = _type

    private val _types = MutableStateFlow<ResourceState<AnimalTypes>>(ResourceState.Loading())
    val types: StateFlow<ResourceState<AnimalTypes>> get() = _types

    private var page = 1
    private var type : String? = null

    init {
        getAnimals()
        getTypes()
    }

    fun getAnimalCall(id: String){
        when(animal.value){
            is ResourceState.Success -> {
                Log.d("Api", "here!!")
                val a = (animal.value as ResourceState.Success<Animal>)
                if (a.data.id !== id){
                    getAnimal(id)
                }
            }
            is ResourceState.Loading -> {
                getAnimal(id)
            }

            else -> {}
        }
    }

    fun changeType(breed: String? = null) {
        page = 1
        type = breed
        var t = "all"
        getAnimals()
        type?.let { getType(it) }

    }

    fun getAnimal(id: String) {
        viewModelScope.launch {
            repository.getAnimal(id).collect {
                    response -> _animal.value = response
            }
        }
    }
    fun getType(type: String) {
        viewModelScope.launch {
            repository.getTypeInfo(type).collect {
                    response -> _type.value = response
            }
        }
    }
    fun getTypes() {
        viewModelScope.launch {
            repository.getTypes().collect {
                response -> _types.value = response
            }
        }
    }

    fun getAnimals() {
        val queryMap = mutableMapOf<String, String>()

        if(type != null) queryMap["type"] = type!!
        queryMap["page"] = page.toString()

        viewModelScope.launch {
            repository.getAnimals(queryMap).collect { response ->
                when (response) {
                    is ResourceState.Success -> {
                        val newAnimals = response.data.animals
                        val currentAnimals = if (page == 1) {
                            newAnimals
                        } else {
                            (_animals.value as? ResourceState.Success)?.data?.plus(newAnimals)
                                ?: newAnimals
                        }
                        _animals.value = ResourceState.Success(currentAnimals)
                        page++
                    }
                    is ResourceState.Error -> {
                        _animals.value = ResourceState.Error(response.error)
                    }
                    is ResourceState.Loading -> {
                        if (page == 1) {
                            _animals.value = ResourceState.Loading()
                        }
                    }
                }
            }
        }
    }


}
