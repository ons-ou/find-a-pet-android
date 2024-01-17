package com.gl4.project.data.api
import com.gl4.project.data.entity.Animal
import com.gl4.project.data.entity.AnimalResponse
import com.gl4.project.data.entity.AnimalTypes
import com.gl4.project.data.entity.AnimalsResponse
import com.gl4.project.data.entity.Type
import com.gl4.project.data.entity.TypeResponse
import com.gl4.project.data.utilities.AuthBody
import com.gl4.project.data.utilities.Token
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface AnimalsApi {

    @POST("/v2/oauth2/token")
    suspend fun authenticate(@Body auth: AuthBody = AuthBody()): Response<Token>

    @GET("/v2/animals")
    suspend fun getAnimals(@QueryMap query: Map<String, String>, @Query("limit") size: Int = 5): Response<AnimalsResponse>

    @GET("/v2/animals/{id}")
    suspend fun getAnimal(@Path("id") id: String): Response<AnimalResponse>

    @GET("/v2/types")
    suspend fun getAnimalTypes(): Response<AnimalTypes>

    @GET("/v2/types/{type}")
    suspend fun getAnimalTypeInfo(@Path("type") type: String): Response<TypeResponse>



}