package com.gl4.project.data.repositories

import android.util.Log
import com.gl4.project.data.RetrofitHelper
import com.gl4.project.data.entity.Animal
import com.gl4.project.data.entity.AnimalTypes
import com.gl4.project.data.entity.AnimalsResponse
import com.gl4.project.data.utilities.ResourceState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class AnimalsRepository {
    init {
        RetrofitHelper.setAuthInterceptor()
    }

    fun getAnimals(queryMap: Map<String, String>): Flow<ResourceState<AnimalsResponse>> = flow {
        emit(ResourceState.Loading())
        try {
            val response = RetrofitHelper.apiService.getAnimals(queryMap)

            if (response.isSuccessful) {
                val animals = response.body()!!
                emit(ResourceState.Success(animals))
            } else {
                emit(ResourceState.Error("An error have occurred. Try again later. HTTP Error: ${response.code()}"))
            }
        } catch (e: Exception) {
            if (e is HttpException) {
                emit(ResourceState.Error("An error have occurred. Try again later. HTTP Error: ${e.code()}"))
            } else {
                emit(ResourceState.Error("Unable to connect to the network"))
            }
        }
    }

    fun getTypes(): Flow<ResourceState<AnimalTypes>> = flow {
        emit(ResourceState.Loading())
        try {
            val response = RetrofitHelper.apiService.getAnimalTypes()

            if (response.isSuccessful) {
                val types = response.body()!!
                emit(ResourceState.Success(types))
            } else {
                emit(ResourceState.Error("An error have occurred. Try again later. HTTP Error: ${response.code()}"))
            }
        } catch (e: Exception) {
            if (e is HttpException) {
                emit(ResourceState.Error("An error have occurred. Try again later. HTTP Error: ${e.code()}"))
            } else {
                emit(ResourceState.Error("Unable to connect to the network"))
            }
        }
    }
    fun getAnimal(id: String): Flow<ResourceState<Animal>> = flow {
        emit(ResourceState.Loading())
        try {
            val response = RetrofitHelper.apiService.getAnimal(id)

            if (response.isSuccessful) {
                val animal = response.body()!!
                emit(ResourceState.Success(animal.animal))
            } else {
                emit(ResourceState.Error("An error have occurred. Try again later. HTTP Error: ${response.code()}"))
            }
        } catch (e: Exception) {
            if (e is HttpException) {
                emit(ResourceState.Error("An error have occurred. Try again later. HTTP Error: ${e.code()}"))
            } else {
                emit(ResourceState.Error("Unable to connect to the network"))
            }
        }
    }
}

